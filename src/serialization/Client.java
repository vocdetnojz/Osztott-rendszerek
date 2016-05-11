package serialization;

import java.net.*;
import java.io.*;

public class Client {
    
    public static void main(String[] args) throws Exception {
	
	Socket s = new Socket("localhost", 12345);
	ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
	ObjectInputStream ois =	new ObjectInputStream(s.getInputStream());
	
	Circle c = new Circle(new Point(1,2),3);
	oos.writeObject(c);
	oos.flush();
	
	double d = ois.readDouble();
	System.out.println("A kor kerulete: " + d);
	s.close();
    }
}
