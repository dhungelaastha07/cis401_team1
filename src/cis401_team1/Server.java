package cis401_team1;

import java.io.*;
import java.lang.ClassNotFoundException;
import java.net.*;
import java.util.*;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static int port = 7777;
	public List<User> UserList;
	public String[][] chatHists = new String[4][4];

	public Server() {
		UserList = new ArrayList<User>();
		UserList.add(new User("Aastha", "1234"));
		UserList.add(new User("Brandy", "1234"));
		UserList.add(new User("Tim", "1234"));
		UserList.add(new User("Nicolai", "1234"));
	}

	public static void main(String args[]) throws IOException, ClassNotFoundException {

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
				OutputStream outputStream = socket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

				while (true) {
					if (msg.getType().contentEquals("login")) {
						System.out.println("TypeFound: login");
						if (userExists(UserList, msg.getUsers()[0])) {
							objectOutputStream.writeObject(
									(validateLogin(UserList, findUser(UserList, msg.getUsers()[0]), msg.getText())));
						} else {
							objectOutputStream.writeObject(new Message("confirm", msg.getUsers(), "no"));
						}
					}

					if (msg.getType().contentEquals("chat")) {
						System.out.println("TypeFound: chat");
						objectOutputStream.writeObject(updateChat(msg.getUsers(), msg.getText()));
					}

					if (msg.getType().contentEquals("logoff")) {
						System.out.println("TypeFound: logoff");
						objectOutputStream.writeObject(new Message("confirm", msg.getUsers(), "yes"));
						break;

					}

					if (msg.getType().contentEquals("quit")) {
						System.out.println("TypeFound: quit");
						System.out.println("Shutting down server.../n");
						break;
					}
					msg = (Message) objectInputStream.readObject();
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
			return new Message("login", userID, "Yes");
		} else {
			return new Message("login", userID, "no");
		}
	}

	/**
	 * private void sendClient(Message outcome, Socket socket) { try {
	 * ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	 * oos.writeObject(outcome); } catch (IOException e) { e.printStackTrace(); } }
	 **/
	private Message updateChat(String users[], String msgText) {
		// Take in coming messages and add them to both users version of the chat
		// history.
		// Or simply send unchanged history if no message
		System.out.println("Working on chat history");
		// convert user IDs to ints
		int user1 = Integer.parseInt(users[0]);
		int user2 = Integer.parseInt(users[1]);
		System.out.println("Step 1");
		if (chatHists[user1][user2] == null) {
			chatHists[user1][user2] = "";
		}
		if (chatHists[user2][user1] == null) {
			chatHists[user2][user1] = "";
		}

		// Update each chat history, only if message contains text
		if (msgText != null) {
			System.out.println("Step 2, updating");
			chatHists[user1][user2] = chatHists[user1][user2] + "\n" + UserList.get(user1).getUsername() + ": "
					+ msgText;
			if (user1 != user2) {
				chatHists[user2][user1] = chatHists[user2][user1] + "\n" + UserList.get(user1).getUsername() + ": "
						+ msgText;
			}

		}

		// Create message with new chat history
		System.out.println("Message sent:" + chatHists[user1][user2]);
		return new Message("chat", users, chatHists[user1][user2]);

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
