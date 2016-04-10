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

        Thread.sleep(1000);

        AIThread ai1 = new AIThread("Jatekos1", "szokincs1.txt");
        ai1.start();

        Thread.sleep(500);

        AIThread ai2 = new AIThread("Jatekos2", "szokincs1.txt");
        ai2.start();

        Thread.sleep(500);

        AIThread ai3 = new AIThread("Jatekos3", "szokincs1.txt");
        ai3.start();

        Thread.sleep(500);

        AIThread ai4 = new AIThread("Jatekos4", "szokincs2.txt");
        ai4.start();

    }

    private static class ServerThread extends Thread {

        GameServer gs = null;

        ServerThread() {
            try {
                gs = new GameServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
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
