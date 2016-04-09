/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fevtabb
 */
public class GameServer{
    private static final int PORT = 32123;
    private ServerSocket serverSocket;
    private boolean serverAlive = true;
    private static int TIMEOUT_LENGTH = 300000;

    private Map<String, PrintWriter> clients = new HashMap<String, PrintWriter>();
        
    public GameServer() throws IOException {
        System.out.println("A szerver elindult...");
        this.serverSocket = new ServerSocket(PORT);
        try {
            serverSocket.setSoTimeout(TIMEOUT_LENGTH);
        } catch (SocketException e) {
//            System.out.println("Socket Exception: letelt a " + TIMEOUT_LENGTH/1000 + "mp, a szerver leáll a már megkezdett játékok vége után.");
//            serverAlive = false;
        }
    // put this into a thread
        // get two players
        
        // create a log file - <player1>_<player2>_<timestamp>.txt

        // notify the first player to start with keyword "start"

        // get the message from one user and send it to the other
        // log the words - <playerX> <word>
        // if someone sends "exit" or one of them disconnects notify the other about winning and terminate the game
    }

    public void handleClients(){
        while (serverAlive) {
            try {
                GameHandler gh = new GameHandler(serverSocket.accept(), serverSocket.accept());
                gh.start();
//                ClientHandler player1 = new ClientHandler(serverSocket.accept());
//                ClientHandler player2 = new ClientHandler(serverSocket.accept());
//                player1.start();
//                player2.start();
//                System.out.println("Két játékos csatlakozott!");

            } catch (IOException e) {
                System.err.println("Letelt a " + TIMEOUT_LENGTH/1000 + "mp, a szerver leáll a már megkezdett játékok vége után.");
                serverAlive = false;
            }
        }
    }

    class GameHandler extends Thread {

        Socket socket1;
        Socket socket2;
        PrintWriter pw1;
        PrintWriter pw2;
        BufferedReader br1;
        BufferedReader br2;

        GameHandler(Socket s1, Socket s2) {
            try {
                this.socket1 = s1;
                this.socket2 = s2;
                pw1 = new PrintWriter(s1.getOutputStream(), true);
                pw2 = new PrintWriter(s2.getOutputStream(), true);
                br1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                br2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
            } catch (IOException e) {
                System.err.println("Inicializalasi problema egy kliensnel.");
            }
        }

        @Override
        public void run(){
            System.out.println("Két játékos csatlakozott!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End");
//            while (true){
//
//            }
        }

    }
/*
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

        }
    }*/

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        if (server != null) server.handleClients();
    }

}
