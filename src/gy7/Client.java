/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gy7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fevtabb
 */
public class Client {
    
    static Socket s;
    static PrintWriter pw;
    static Scanner sc;
    static boolean active = true;
    
    public static void main(String[] args) throws IOException, InterruptedException{
        
        s = new Socket("157.181.163.11", 12345);
        pw = new PrintWriter(s.getOutputStream(), true);
        sc = new Scanner(s.getInputStream());
        
        String username = "nemenvotam";
        System.out.println("Sent username: " + username);
        pw.println("qwert1111");
        pw.flush();
        
        Thread.sleep(1000);
        
        String answer = sc.nextLine();
        System.out.println(answer);
        
        if("ok".equals(answer)) {
            new Olvaso().start();
            new Kuldo().start();
        }
        else {
            System.out.println("Failed to connect to server, server response: " + answer);
        }
        
    }
    
    static class Kuldo extends Thread {
        @Override
        public void run(){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while(active) {
                try {
                    System.out.print(">>> ");
                    s = br.readLine();
                    pw.println(s);
                    pw.flush();
                    if(s.equals("exit")) active = false;
                    if(s.equals("egykisddos")) {
                        while(true){
                            pw.println("ha");
                            pw.flush();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    static class Olvaso extends Thread {
        @Override
        public void run(){
            while(active){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(sc.nextLine());
            }
        }
    }
}
