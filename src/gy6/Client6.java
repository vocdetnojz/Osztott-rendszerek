package gy6;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client6 {
   
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", Server6.PORT);
        
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(s.getInputStream());
        
        for (int i = 10; i >= 0; i--) {
            pw.println(i);
        }
        /*
        Scanner sc_user = new Scanner(System.in);
        
        while (true) {
            System.out.print("> ");
            while (! sc_user.hasNextInt()) {
                System.err.println("nem szam");
                System.out.print("> ");
                sc_user.next();
            }
            int i = sc_user.nextInt();
            pw.println(i);
            System.out.println("A kuldott ertek: " + i);
            System.out.println("A kapott ertek: " + sc.nextInt());
            if (i == 0) break;
        }*/
        
        s.close();
    }
}
