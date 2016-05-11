package serialization;

import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
	
	Circle c1 = new Circle(new Point(1,2), 3);
	Circle c2 = new Circle(new Point(4,5), 6);
	
	System.out.println(c2);
	System.out.println(c2.getC());
	System.out.println(c2);
	System.out.println("--------------------------");
	
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("circles.ser"));
	c1.move(1,1);
	oos.writeObject(c1);
	c1.move(1,1);
	oos.reset(); //!!!
	oos.writeObject(c1);
	oos.writeObject(c2);
	oos.close();
	
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("circles.ser"));
	Circle c11 = (Circle) ois.readObject();
	Circle c12 = (Circle) ois.readObject();
	Circle c21 = (Circle) ois.readObject();
	ois.close();
	
	System.out.println(c11);
	System.out.println(c12);
	System.out.println(c21);
    }

}
