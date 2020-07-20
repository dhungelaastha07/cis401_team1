package cis401_team1;

import java.io.*;
import java.lang.ClassNotFoundException;
import java.net.*;
import java.util.*;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class Server {
	private static ServerSocket server;
	private static int port = 7777;
	private List<User> UserList;
	public String[][] chatHists = new String[4][4];

		while (true) {
			System.out.println("Awaiting Connection...\n");
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

		Server serv = new Server();
		try (ServerSocket listener = new ServerSocket(port)) {
			System.out.println("The server is running...");
			ExecutorService pool = Executors.newFixedThreadPool(20);
			while (true) {
				pool.execute(serv.new ClientConnect(listener.accept()));
			}
		}

	}

	private class ClientConnect implements Runnable {
		private Socket socket;

		ClientConnect(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("Connected: " + socket);
			try {
				InputStream in = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(in);
				Message msg = (Message) objectInputStream.readObject();
				System.out.println(msg.getType());
				while (true) {

					if (msg.getType() == "login") {
						if (userExists(UserList, msg.getUserID()[0])) {
							sendClient(validateLogin(UserList, findUser(UserList, msg.getUserID()[0]), msg.getText()),
									socket);
						} else {
							sendClient(new Message("confirm", msg.getUserID(), "no"), socket);
						}
					}

					if (msg.getType() == "chat") {
						sendClient(updateChat(msg.getUserID(), msg.getText()), socket);
					}

					if (msg.getType() == "logoff") {
						try {
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sendClient(new Message("confirm", msg.getUserID(), "yes"), socket);
					}

					if (msg.getType() == "quit") {
						try {
							socket.close();
							// server.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("Shutting down server.../n");
					}

				}
			} catch (Exception e) {
				System.out.println("Error:" + socket);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
				}
				System.out.println("Closed: " + socket);
			}
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

	private void sendClient(Message outcome, Socket socket) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
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
/**
 * public class echoThread extends Thread { protected Socket socket;
 * 
 * public void EchoThread(Socket clientSocket) { this.socket = clientSocket; }
 * 
 * public void run() { InputStream inp = null; BufferedReader brinp = null;
 * DataOutputStream out = null; try { inp = socket.getInputStream(); brinp = new
 * BufferedReader(new InputStreamReader(inp)); out = new
 * DataOutputStream(socket.getOutputStream()); } catch (IOException e) { return;
 * } String line; while (true) { try { line = brinp.readLine(); if ((line ==
 * null) || line.equalsIgnoreCase("QUIT")) { socket.close(); return; } else {
 * out.writeBytes(line + "\n\r"); out.flush(); } } catch (IOException e) {
 * e.printStackTrace(); return; } } } }
 **/
