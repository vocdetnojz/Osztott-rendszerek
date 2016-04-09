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
import java.net.Socket;
import java.util.Scanner;

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

        s = new Socket("localhost", 32123);
        pw = new PrintWriter(s.getOutputStream(), true);
        sc = new Scanner(s.getInputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Username: ");
        String username = br.readLine();
//        System.out.println("Sent username: " + username);
        pw.println(username);
        pw.flush();

        String serverResponse;

        while(active){
            serverResponse = sc.nextLine();
            switch(serverResponse){
                case "start":
                    System.out.print("Kérek egy szót: ");
                    pw.println(br.readLine());
                    pw.flush();
                    break;
                case "exit":
                    active = false;
                    break;
                case "Ön nyert!":
                    active = false;
                    System.out.println(serverResponse);
                    break;
                default:
                    System.out.println("ellenfél szava: " + serverResponse);
                    System.out.print("Kérek egy szót: ");
                    pw.println(br.readLine());
                    pw.flush();
            }
        }

    }
}
