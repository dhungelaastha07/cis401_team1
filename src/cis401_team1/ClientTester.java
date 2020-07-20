package cis401_team1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientTester {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
		int port = 7777;
		String host = "localhost";

		// Connect to the ServerSocket at host:port
		Socket socket = new Socket(host, port);
		System.out.println("Connected to " + host + ":" + port);

		// Output stream socket.
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		InputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		
		while (true) {
		System.out.println("Enter Type(chat, logoff): ");
		String type = sc.next();
		
		System.out.println("Enter message:");
		String msg = sc.next();

		String userID[];
		userID = new String[2];
		userID[0] = "1";
		userID[1] = "2";
		Message test = new Message(type, userID, msg);
		
		System.out.println("Sending Message Objects");
		objectOutputStream.writeObject(test);
		
		if(socket.getInputStream() != inputStream) {
			inputStream = socket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
		}
			try {
				test = (Message) objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(test.getText());
			if (test.getType() == "confirm") {
				break;
			}
		}

		System.out.println("Closing socket");
		sc.close();
		socket.close();
	}
}
