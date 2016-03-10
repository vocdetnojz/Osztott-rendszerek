/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gy5;

import java.net.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author fevtabb
 */
public class Client2 {
    
    
    
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", Server2.PORT);
        
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        Scanner sc = new Scanner(s.getInputStream());
        
        int n = 30000;
        String text = "almafa";
        
        pw.println(n);
        pw.println(text);
        pw.flush();
        
        long t1 = System.currentTimeMillis();
        String answer = sc.nextLine();
        long t2 = System.currentTimeMillis();
        
        System.out.println("A szerver válaszideje: " + (t2 - t1));
        
    }
    
}
