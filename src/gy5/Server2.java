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
public class Server2 {
    
    public static final int PORT = 12345;
    
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        Socket s = ss.accept();
        
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        Scanner sc = new Scanner(s.getInputStream());
        
        int n = sc.nextInt();
        sc.nextLine();
        
        String text = sc.nextLine();
        System.out.println("A kapott szám: " + n +
                            ", a kapott szöveg: " + text);
        kuldes1(n, text, pw);
        s.close();
        ss.close();
                
        
    }
    
    private static void kuldes1(int n, String text, PrintWriter pw) {
        String answer = "";
        for(int i = 0; i < n; i++){
            answer += text;
        }
        pw.println(answer);
        pw.flush();
    }
}
