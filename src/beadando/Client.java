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
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author fevtabb
 */
public class Client {
    Socket s;
    PrintWriter pw;
    Scanner sc;
    boolean active = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
        client.startClient();
    }

    public void startClient() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Username: ");
        String username = br.readLine();
        s = new Socket("localhost", 32123);
        pw = new PrintWriter(s.getOutputStream(), true);
        sc = new Scanner(s.getInputStream());
        pw.println(username);
        pw.flush();

        String serverResponse;
        String word;
        String temp;
        try {
            while (active) {
                serverResponse = sc.nextLine();
                switch (serverResponse) {
                    case "start":
                        // elsőként adhat meg szót
                        System.out.print("Kérek egy szót: ");
                        word = br.readLine();
                        if (word.equals("exit")) {
                            System.out.println("Feladtad, vesztettél!");
                            active = false;
                        }
                        pw.println(word);
                        pw.flush();
                        break;
                    case "nyert":
                        // az ellenfél kilépett, ez a klien győz
                        System.out.println("Győztél!");
                        active = false;
                        break;
                    default:
                        // kapott szóra megfelelő választ
                        System.out.println("ellenfél szava: " + serverResponse);
                        temp = serverResponse.substring(serverResponse.length()-1, serverResponse.length());
                        System.out.print("Kérek egy szót: ");
                        word = br.readLine();
                        // kérd újra, amíg nem lesz megfelelő szó
                        while(!word.substring(0,1).equals(temp) && !word.equals("exit")){
                            System.out.print("Nem megfelelő szó, kérek egy másikat: ");
                            word = br.readLine();
                        }
                        if (word.equals("exit")) {
                            System.out.println("Feladtad, vesztettél!");
                            active = false;
                        }
                        pw.println(word);
                        pw.flush();
                }
            }
        } catch (NoSuchElementException e) {
            // ha nem létezik már kapocsolat, az ellenfél lecsatlakozott, ezért ez a kliens nyert
            System.out.println("Győztél!");
        }
    }
}
//