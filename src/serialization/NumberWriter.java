package serialization;

import java.io.*;
import java.util.Random;

public class NumberWriter {
    
    public static void main(String[] args) throws Exception {
	
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("numbers.ser"));
	
	Random rand = new Random();
	for (int i=0; i<100; i++) {
	    Object o;
	    if (rand.nextBoolean()) {
		o = rand.nextInt();
	    } else {
		o = rand.nextDouble();
	    }
	    oos.writeObject(o);
	    oos.flush();
	}
	oos.close();
    }
}
