package serialization;

import java.net.*;
import java.io.*;

public class Server {

    public static double getC(Circle circle) {
	return circle.getC();
    }

    public static class ClientHandler extends Thread {

	Socket s;
	
	ClientHandler(Socket s) {
	    this.s = s;
	}
	
	@Override
	public void run() {
	    try (
                 ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                 ObjectInputStream ois = new ObjectInputStream(s.getInputStream()))
                    {
                        Circle circle = (Circle) ois.readObject();
                        System.out.println("A kapott kor: " + circle);

                        oos.writeDouble(getC(circle));
                        oos.flush();
		
                        s.close();
                    } catch (IOException | ClassNotFoundException e) {
		e.printStackTrace();
	    }
	}
    }

    public static void main(String[] args) throws Exception {

	ServerSocket ss = new ServerSocket(12345);

	while (true) {
	    Thread t = new ClientHandler(ss.accept());
	    t.start();
	}
	// ss.close();
    }
}
