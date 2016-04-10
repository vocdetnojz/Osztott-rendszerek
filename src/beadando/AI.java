/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author fevtabb
 */
public class AI {

    Socket s;
    PrintWriter pw;
    Scanner sc;
    boolean active = true;
    String username;
    String filename;

    private static LinkedList<String> makeWords(String filename) throws IOException {
        LinkedList<String> w = new LinkedList<>();
        FileReader fr = new FileReader(new File(filename));
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null) {
            w.add(line);
        }
        return w;
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        AI ai = new AI(args[0], args[1]);
        ai.startAI();
    }

    public AI(String username, String filename){
        this.username = username;
        this.filename = filename;
    }

    public void startAI() throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList<String> words = makeWords(filename);

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
                word = "exit";
                serverResponse = sc.nextLine();
                switch (serverResponse) {
                    case "start":
                        // elsőként adhat meg szót
                        // System.out.print("Kérek egy szót: ");
                        word = words.pop();
                        System.out.println(username + ": " + word);
                        if (word.equals("exit")) {
                            System.out.println("Feladtad, vesztettél!");
                            active = false;
                        }
                        Thread.sleep(500);
                        pw.println(word);
                        pw.flush();
                        break;
                    case "nyert":
                        // az ellenfél kilépett, ez a gép győz
                        System.out.println(username + " nyert");
                        active = false;
                        break;
                    default:
                        // kapott szóra megfelelő választ
                        // System.out.println("ellenfél szava: " + serverResponse);
                        temp = serverResponse.substring(serverResponse.length()-1, serverResponse.length());
                        for (String w : words) {
                            if (w.substring(0, 1).equals(temp)) {
                                word = w;
                                words.remove(w);
                                break;
                            }
                        }
                        System.out.println(username + ": " + word);
                        Thread.sleep(500);
                        pw.println(word);
                        pw.flush();
                        if(word.equals("exit")){
                            //System.out.println("Feladtad, vesztettél!");
                            System.exit(0);
                        }
                }
            }
        } catch (NoSuchElementException e) {
            // ha nem létezik már kapocsolat, az ellenfél lecsatlakozott, ezért ez a kliens nyert
            System.out.println("Győztél!");
        }
    }


}
