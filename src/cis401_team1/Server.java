
package cis401_team1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import cis401_team1.*;

public class server {
	private static ServerSocket server;
	private static int port = 666;
	private List<User> UserList;
	public String[][] chatHists = new String[4][4];
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		server = new ServerSocket(port);

		while (true) {
			System.out.println("Awaiting Connection.../n");
			Socket socket = server.accept();
			System.out.println("Connection from " + socket + " found.../n");

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			// message recieved;
			//processMessage(message, socket, oos);

			ois.close();
			oos.close();
			socket.close();
		}
	}

	private void processMessage(Message msg, Socket socket, ObjectOutputStream oos) {
		if (msg.getType() == "login") {
			if (userExists(UserList, msg.getUserID()[0])) {
				sendClient(validateLogin(UserList, findUser(UserList, msg.getUserID()[0]), msg.getText()), socket, oos);
			} else {
				sendClient(new Message("confirm", msg.getUserID(), "no"), socket, oos);
			}
		}

		if (msg.getType() == "chat") {
			sendClient(updateChat(msg.getUserID(), msg.getText()), socket, oos);
		}

		if (msg.getType() == "logoff") {
			sendClient(new Message("confirm", msg.getUserID(), "yes"), socket, oos);
		}

		if (msg.getType() == "quit") {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Shutting down server.../n");
		}

	}

	private boolean userExists(List<User> listOfUsers, String testName) {
		for (int i = 0; i < listOfUsers.size(); i++) {
			if (listOfUsers.get(i).getUsername().equals(testName)) {
				return true;
			}
		}
		return false;
	}

	private int findUser(List<User> listOfUsers, String testName) {
		for (int i = 0; i < listOfUsers.size(); i++) {
			if (listOfUsers.get(i).getUsername().equals(testName)) {
				return i;
			}
		}
		return -1;
	}

	private Message validateLogin(List<User> listOfUsers, int index, String password) {

		String userID[];
		userID = new String[1];
		userID[0] = Integer.toString(index);

		if (listOfUsers.get(index).getPassword().equals(password)) {
			return new Message("confirm", userID, "no");
		} else {
			return new Message("confirm", userID, "no");
		}
	}

	private void sendClient(Message outcome, Socket socket, ObjectOutputStream oos) {
		try {
			oos.writeObject(outcome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Message updateChat(String users[], String msgText) {
		// Take in coming messages and add them to both users version of the chat
		// history.
		// Or simply send unchanged history if no message
		
		// convert user IDs to ints
		int user1 = Integer.parseInt(users[0]);
		int user2 = Integer.parseInt(users[1]);

		// Update each chat history, only if message contains text
		if (msgText != "") {

			chatHists[user1][user2] = chatHists[user1][user2] + "\n" + users[user1] + ": " + msgText;
			chatHists[user2][user1] = chatHists[user2][user1] + "\n" + users[user1] + ": " + msgText;

		}

		// Create message with new chat history
		return new Message("chatHist", users, chatHists[user1][user2]);

	}

}

