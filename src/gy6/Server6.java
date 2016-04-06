package gy6;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server6 {
    
    public static int PORT = 11223;
    
    private static int state = 0;
    
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        
        while (true) {
            Socket s = ss.accept();
            new Handler(s).start();
        }
        //ss.close();
    }
    
    static class Handler extends Thread {
        
        Socket s;
        
        Handler(Socket s) {
            this.s = s;
        }
        
        @Override
        public void run() {
            try {
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            Scanner sc = new Scanner(s.getInputStream());

            while (true) {
                int x = sc.nextInt();
                synchronized (Server6.class) {
                    System.out.println("A beolvasott ertek: " + x);
                    state += x;
                    pw.println(state);
                    System.out.println("A kuldott ertek: " + state);
                }
                if (x == 0) {
                    break;
                }
            }

            s.close();
            } catch (Exception e) {
                System.err.println("Hiba a klienessel valo kommunikacioban.");
            }
        }
    }
    
}
