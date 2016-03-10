/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gy4;

import java.io.*;
import java.net.*;

/**
 *
 * @author fevtabb
 */
public class Server1 {
    
    public static void main(String[] args) throws IOException {
        // haszánálandó port
        final int port = 8080;
        // elindítunk egy szervert
        ServerSocket server = new ServerSocket(port);
        System.out.println("A szerver elindult");
        // elmentjük a kliens konnekciót
        Socket client = server.accept();
        System.out.println("Kliens csatlakozott");
        // olvasáshoz:
        InputStream is = client.getInputStream();
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(is));
        String message = br.readLine();
        
        System.out.println("A kliens üzenete: " + message);
        client.close();
        server.close();
        System.out.println("A szerver leállt");
        
    }
    
}
