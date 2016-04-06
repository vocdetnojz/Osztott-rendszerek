package gy6;

public class Counter {

    static Integer counter = 100;
    
    public static void main(String[] args) {
        for (int i=0; i < 5; i++) {
            new MyThread().start();
        }
    }
    
    static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (counter) {
                    if (counter <= 0) break;
                    System.out.println(counter + "(" + this.getName() + ")");
                    counter--;
                }
            } 
        }
    }
}
