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
public class Client1 {
    public static void main(String[] args) throws IOException{
        final String host = "localhost";
        final int port = 8080;
        Socket client = new Socket(host,port);
        System.out.println("A kliens csatlakozott a szerverhez");
        
        OutputStream os = client.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        
        pw.println("Yo, motherfucker!");
        pw.flush();
        System.out.println("Üzenet elküldve");
        client.close();
    }
}
