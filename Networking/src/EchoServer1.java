import java.net.*;
import java.io.*;

public class EchoServer1 {
	final static int PORT = 2222;	//port number should be > 1023

	public static void main (String[] args) {
		ServerSocket serverSocket = null;
	
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + PORT + ", " + e);
			System.exit(1);
		}

		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Accept failed: " + PORT + ", " + e);
				continue;
			}
			try {
				BufferedReader is = new BufferedReader(new 
									 InputStreamReader(clientSocket.getInputStream()));
				PrintWriter os = new PrintWriter(new 
									 BufferedOutputStream(clientSocket.getOutputStream()));

				String inputLine, outputLine;

				while ((inputLine = is.readLine()) != null) {
					outputLine = inputLine;
					os.println(outputLine);	//just send back whatever was sent to us
					os.flush();
					if (outputLine.equals("quit"))
						break;
				}
				os.close();
				is.close();
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
		}
	
	}
}
