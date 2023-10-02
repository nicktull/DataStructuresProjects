import java.io.*;

public class DataIO {
	
	public static void main(String[] args) throws IOException {
		int x = -37; 
		double y = Math.PI;
		String z = "sample string";
		DataOutputStream os = new DataOutputStream(new FileOutputStream("test.dat"));
		os.writeInt(x); System.out.println(os.size() + " bytes written"); //
		os.writeDouble(y); System.out.println(os.size() + " bytes written");
		os.writeUTF(z); System.out.println(os.size() + " bytes written"); // Every character is worth two bytes and then there is a two byte length in front of it.
		os.writeShort(z.length()); System.out.println(os.size() + " bytes written");
		os.writeChars(z); System.out.println(os.size() + " bytes written");
		os.close();
		
	
		DataInputStream is = new DataInputStream(new FileInputStream("test.dat"));
		int a = is.readInt(); System.out.println(a);
		double b = is.readDouble(); System.out.println(b);
		String c = is.readUTF(); System.out.println(c);
		int len = is.readShort();
		String d = "";
		for(int i = 0; i < len; i++) {
			d += is.readChar();
		}System.out.println(d);
		is.close();
		
		
		RandomAccessFile raf = new RandomAccessFile ("test.dat", "r"); // Single letter character r is a parameter , r stands for read, w stands for write
		int g = raf.readInt(); System.out.println(g);
		raf.seek(12); 
		String h = raf.readUTF(); System.out.println(h);
		raf.seek(4); // Positions the file to that particular byte number. By default it starts at zero
		double  m = raf.readDouble(); System.out.println(m);
		// Seek method allows us to skip around in the file. 
	}

}  // If we want only the computer to read this information we will use Data Io compared to the bufferedReader and printwriter which
   // we have to convert to a human readable language.
