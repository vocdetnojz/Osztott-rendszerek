/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando;

import java.io.IOException;

/**
 *
 * @author fevtabb
 */
public class SzolancJatek {


    public static void main(String[] args) throws IOException, InterruptedException {

        ServerThread st = new ServerThread();
        st.start();

        AIThread ait = new AIThread("Robot", "szokincs1.txt");
        ait.start();

        ClientThread clt = new ClientThread();
        clt.start();

    }

    static class ServerThread extends Thread {

        GameServer gs = null;

        ServerThread () {
            try {
                gs = new GameServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run(){
            try {
                gs.startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class AIThread extends Thread {
        AI ai = null;

        AIThread(String name, String words){
            ai = new AI(name, words);
        }

        @Override
        public void run(){
            try {
                ai.startAI();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientThread extends Thread {
        Client cl = null;

        ClientThread(){
            cl = new Client();
        }

        @Override
        public void run(){
            try {
                cl.startClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
