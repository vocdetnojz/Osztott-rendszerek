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
import java.time.LocalDateTime;
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
    private static int TIMEOUT_LENGTH = 30000;

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
    }

    public void handleClients(){
        while (serverAlive) {
            try {
                GameHandler gh = new GameHandler(serverSocket.accept(), serverSocket.accept());
                gh.start();
            } catch (IOException e) {
                System.err.println("Letelt a " + TIMEOUT_LENGTH/1000 + "mp, a szerver leáll a már megkezdett játékok vége után.");
                serverAlive = false;
            }
        }
    }

    class GameHandler extends Thread {

        Socket socket1;
        Socket socket2;
        String name1 = "";
        String name2 = "";
        PrintWriter pw1;
        PrintWriter pw2;
        BufferedReader br1;
        BufferedReader br2;
        String turn = name1;
        PrintWriter writer = null;

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
            System.out.println("JÁTSZMA KEZDETE: Két játékos csatlakozott!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                String temp;
                name1 = br1.readLine();
                name2 = br2.readLine();
                String filename = makeFilename(name1, name2);
                writer = new PrintWriter(filename, "UTF-8");
                pw1.println("start");
                pw1.flush();

                turn = name1;
                while (true){

//                    temp = br1.readLine();
//                    if(temp.equals("exit")) temp = "nyert";
//                    pw2.println(temp);
//                    pw2.flush();
//                    if(temp.equals("nyert")) break;
//                    else writer.println(name1 + " " + temp);
//                    turn = name2;
//
//                    temp = br2.readLine();
//                    if(temp.equals("exit")) temp = "nyert";
//                    pw1.println(temp);
//                    pw1.flush();
//                    if(temp.equals("nyert")) break;
//                    else writer.println(name2 + " " + temp);
//                    turn = name1;
//
                    if (turn.equals(name1)){
                        temp = br1.readLine();
                        if(temp.equals("exit")) temp = "nyert";
                        pw2.println(temp);
                        pw2.flush();
                        if(temp.equals("nyert")) break;
                        else writer.println(name1 + " " + temp);
                        turn = name2;
                    } else if(turn.equals(name2)) {
                        temp = br2.readLine();
                        if(temp.equals("exit")) temp = "nyert";
                        pw1.println(temp);
                        pw1.flush();
                        if(temp.equals("nyert")) break;
                        else writer.println(name2 + " " + temp);
                        turn = name1;
                    }

                }
                System.out.println("JÁTSZMA VÉGE: " + name1 + " és " + name2 + " között: FELADÁS");
            } catch (IOException e) {
                System.out.println("JÁTSZMA VÉGE " + name1 + " és " + name2 + " között: DISCONNECT");
                pw1.println("nyert");
                pw1.flush();
                pw2.println("nyert");
                pw2.flush();
            }
            writer.close();
        }

    }

    private String makeFilename(String name1, String name2){
        String filename = name1 + "_" + name2 + "_";
        LocalDateTime now = LocalDateTime.now();

        int year = now.getYear();
        filename += year + "-";

        int month = now.getMonthValue();
        filename += month + "-";

        int day = now.getDayOfMonth();
        filename += day + " ";

        int hours = now.getHour();
        if(hours < 10) filename += "0" + hours + "_";
        else filename += hours + "_";

        int minutes = now.getMinute();
        if(minutes < 10) filename += "0" + minutes + "_";
        else filename += minutes + "_";

        int seconds = now.getSecond();
        if(seconds < 10) filename += "0" + seconds;
        else filename += seconds;

        filename += ".txt";
        return filename;
    }

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        if (server != null) server.handleClients();
    }

    public void startServer() throws IOException{
        //GameServer server = new GameServer();
        if (this != null) this.handleClients();
    }

}
