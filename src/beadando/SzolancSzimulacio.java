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
public class SzolancSzimulacio {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerThread st = new ServerThread();
        st.start();

        Thread.sleep(100);

        AI ai1 = new AI("Jatekos1", "szokincs1.txt");
        ai1.startAI();

        Thread.sleep(200);

        AI ai2 = new AI("Jatekos2", "szokincs1.txt");
        ai2.startAI();

        Thread.sleep(200);

        AI ai3 = new AI("Jatekos3", "szokincs1.txt");
        ai3.startAI();

        Thread.sleep(200);

        AI ai4 = new AI("Jatekos4", "szokincs2.txt");
        ai4.startAI();

    }

    private static class ServerThread extends Thread {

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

}
