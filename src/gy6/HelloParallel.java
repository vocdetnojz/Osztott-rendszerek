package gy6;

public class HelloParallel {
    
    public static void main(String[] args) {
        new Hello().start();
        new Hello().start();
    }
}

class Hello extends Thread {
    @Override
    public void run() {
        String s = "Hello Világ! ";
        for (int i=0; i < 100; i++) {
            synchronized (Hello.class) {
                for (int j=0; j < s.length(); j++) {
                    System.out.print(s.charAt(j));
                }
            }
        }
    }
}
