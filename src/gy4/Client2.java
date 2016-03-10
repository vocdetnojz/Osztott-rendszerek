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
import java.net.Socket;

/**
 *
 * @author fevtabb
 */
public class Client2 {
    public static void main(String[] args) throws IOException{
        final String host = "localhost";
        final int port = 8080;
        Socket client = new Socket(host,port);
        System.out.println("A kliens csatlakozott a szerverhez");
        
        OutputStream os = client.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        InputStream is = client.getInputStream();
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        
        pw.println("A nigga stole my bike");
        pw.flush();
        System.out.println("Üzenet elküldve");
        String message = br.readLine();
        System.out.println("Válasz: " + message);
        
        client.close();
    }
}
