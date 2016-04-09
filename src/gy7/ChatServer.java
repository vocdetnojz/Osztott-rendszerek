package gy7;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {

    private static final int PORT=12345;
    private ServerSocket server;
    private boolean serverAlive = true;

    private Map<String, PrintWriter> clients = new HashMap<String, PrintWriter>();
    
    public int getPort() {
        return PORT;
    }
    
    ChatServer() {

        try {
            server = new ServerSocket(PORT);
            System.out.println("A chat server elindult.");
        } catch (IOException e) {
            System.err.println("Hiba a chat server inditasanal.");
            e.printStackTrace();
        }
        try {
            server.setSoTimeout(30000);
        } catch (SocketException e) {
            System.out.println("Socket Exception");
            serverAlive = false;
            e.printStackTrace();
        }
    }
        
    public void handleClients() {
        while (serverAlive) {
            try {
                new ClientHandler(server.accept()).start();
            } catch (IOException e) {
                System.err.println("A szerver leáll, letelt a 30mp.");
                serverAlive = false;
            }
        }
    }
        
    private synchronized boolean addClient(String name, PrintWriter pw) {
        if (clients.get(name) != null) return false;
        clients.put(name, pw);
        send(name, "csatlakozott.");
        return true;
    }
        
    private synchronized void removeClient(String name) {
        clients.remove(name);
    }
        
    private synchronized void send(String name, String message) {
        System.out.println(name + ": " + message);
        for (String n : clients.keySet()) {
            if (! n.equals(name)) {
                clients.get(n).println(name + ": " + message);
            }
        }
    }
        
    class ClientHandler extends Thread {
                
        PrintWriter pw;
        BufferedReader br;
        String name;
                
        ClientHandler(Socket s) {
            try {
                pw = new PrintWriter(s.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (IOException e) {
                System.err.println("Inicializalasi problema egy kliensnel. Nev: " + name);
            }
        }
                
        @Override
        public void run() {
            String message = null;
            try {
                boolean ok = false;
                while (! ok) {
                    name = br.readLine();
                    if (name == null) return;
                    ok = addClient(name, pw);
                    if (ok) pw.println("ok"); else pw.println("nok");
                }
                while (! "exit".equals(message)) {
                    message = br.readLine();
                    if (message == null) break;
                    send(name, message);
                }
            } catch (IOException e) {
                System.err.println("Kommunikacios problema egy kliensnel. Nev: " + name);
            } finally {
                if (name != null) send(name, "kilepett.");
                removeClient(name);
                try {pw.close(); br.close();} catch (IOException e) {}
            }
        }
    }
        
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        if (server != null) server.handleClients();
    }
        
}
