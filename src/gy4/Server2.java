/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gy4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author fevtabb
 */
public class Server2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // haszánálandó port
        final int port = 8080;
        // elindítunk egy szervert
        ServerSocket server = new ServerSocket(port);
        System.out.println("A szerver elindult");
        // elmentjük a kliens konnekciót
        Socket client ;
        
        
        // olvasáshoz:
        InputStream is;
        BufferedReader br;
        
        // íráshoz
        OutputStream os;
        PrintWriter pw;
        
        String message = "x";
        
        while(message != null){
            
            client = server.accept(); System.out.println("Kliens csatlakozott:");
            
            is = client.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            os = client.getOutputStream();
            pw = new PrintWriter(os);
            
            message = br.readLine();
            
            System.out.println("-----");
            System.out.println("A kliens címe: " +client.getInetAddress());
            System.out.println("A kliens üzenete: " + message);
            Thread.sleep(3000);
            pw.println(new StringBuilder(message).reverse().toString());
            pw.flush();
            System.out.println("Válasz kiküldve.");
            
            
            client.close(); System.out.println("Kliens lecsatlakoztatva.");
            System.out.println("-----");
        }
        server.close();
        System.out.println("A szerver leállt");
        
    }
}
