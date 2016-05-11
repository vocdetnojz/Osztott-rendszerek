package serialization;

import java.io.*;

public class NumberReader {
    
    public static void main(String[] args) throws Exception {
	
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("numbers.ser"));
	
	int intCount = 0;
	int doubleCount = 0;
	int intSum = 0;
	double doubleSum = 0;
	
	for (int i=0; i<100; i++) {
	    Object obj = ois.readObject();
	    if (obj instanceof Integer) {
		intCount++;
		intSum += (Integer) obj;
	    } else {
		doubleCount++;
		doubleSum += (Double) obj;
	    }
	}
	
	System.out.println(intCount + " db egesz szam, osszeguk: " + intSum);
	System.out.println(doubleCount + " db lebegopontos szam, osszeguk: " + doubleSum);
    }

}
