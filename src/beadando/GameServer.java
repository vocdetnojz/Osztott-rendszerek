/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando;

import static gy6.Server6.PORT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author fevtabb
 */
public class GameServer {
    private static final int PORT = 32123;
        ServerSocket ss;

    public int getPort(){
        return PORT;
    }
        
    public GameServer() throws IOException {
        this.ss = new ServerSocket(PORT);
    // put this into a thread
        // get two players
        
        // create a log file - <player1>_<player2>_<timestamp>.txt

        // notify the first player to start with keyword "start"

        // get the message from one user and send it to the other
        // log the words - <playerX> <word>
        // if someone sends "exit" or one of them disconnects notify the other about winning and terminate the game
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

}
