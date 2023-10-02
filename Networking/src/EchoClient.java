import java.net.*;
import java.io.*;

public class EchoClient {
	final static int PORT = 2222;	//port number must match server port number
	final static String SERVER = "127.0.0.1";	//IP address of the server

	public static void main (String[] args) {
		Socket socket = null;
	
		try {
			socket = new Socket(SERVER, PORT);
		} catch (UnknownHostException e) {
			System.out.println("Can't find the Echo server");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Echo server not responding");
		}

		try {
			BufferedReader user  = new BufferedReader(new InputStreamReader(System.in));

			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));

			String inputLine, resultLine;

			while ((inputLine = user.readLine()) != null) {
				os.println(inputLine);	//send user input to the server
				os.flush();
				resultLine = is.readLine();	//read server response
				System.out.println("Server response: " + resultLine);
				if (resultLine.equals("quit"))
					break;
			}
			user.close();
			os.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("I/O error: " + e);
		}

	}
}
