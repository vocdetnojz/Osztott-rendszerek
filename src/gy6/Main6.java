package gy6;

public class Main6 {
    
    public static void main(String[] args) {
        
        new Thread() {
            @Override
            public void run() {
                try {
                    Server6.main(new String[0]);
                } catch (java.io.IOException e) {
                    System.err.println("Szerver oldali hiba.");
                }
            }
        }.start();
        
        Runnable clientTask =
             () -> {try {
                        Client6.main(new String[0]);
                    } catch (java.io.IOException e) {
                        System.err.println("Kliens oldali hiba.");
                    }
             };
         
        for (int i=0; i < 5; i++) {
            new Thread(clientTask).start();
        }
    }
    
}
