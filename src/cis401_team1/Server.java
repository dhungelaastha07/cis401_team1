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
	private static int port = 7777;
	public List<User> UserList;
	public String[][] chatHists = new String[4][4];
	public int resHistory = -1;
	
	public Server() {
		UserList = new ArrayList<User>();
		UserList.add(new User("Aastha", "1234"));
		UserList.add(new User("Tim", "1234"));
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
		ObjectOutputStream oos;

		ClientConnect(Socket socket) throws IOException {
			this.socket = socket;
			this.oos  = new ObjectOutputStream(socket.getOutputStream());
		}

		@Override
		public void run() {
			System.out.println("Connected: " + socket);
			try {
				InputStream in = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(in);
				Message msg = null;
				while (msg == null || !msg.getType().equals("quit")) {
					msg = (Message) objectInputStream.readObject();
					System.out.println(msg);
					if (msg.getType().equals("login")) {
						User currentuser = findUser(UserList, msg.getUsers()[0]);
						if (currentuser != null) {
							sendClient(validateLogin(currentuser, msg.getText()), oos);
						} else {
							sendClient(new Message("confirm", msg.getUsers(), "no"), oos);
						}
					} else if (msg.getType().equals("chat")) {
						sendClient(updateChat(msg.getUsers(), msg.getText()), oos);
					}else if (msg.getType().equals("logoff")) {
						try {
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sendClient(new Message("confirm", msg.getUsers(), "yes"), oos);
					} else if (msg.getType().equals("quit")) {
						try {
							socket.close();
							// server.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("Shutting down server...\n");
					} else {
						System.out.println("Invalid message type.....\n");
						sendClient(msg, oos);
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


	private User findUser(List<User> listOfUsers, String testName) {
		for (int i = 0; i < listOfUsers.size(); i++) {
			if (listOfUsers.get(i).getUsername().equals(testName)) {
				return listOfUsers.get(i);
			}
		}
		return null;
	}

	private Message validateLogin(User currentUser, String password) {
		if (currentUser.getPassword().equals(password)) {
			return new Message("login", new String[] {currentUser.getUsername()}, "yes");
		} else {
			return new Message("invalid", new String[] {currentUser.getUsername()}, "no");
		}
	}

	private void sendClient(Message outcome, ObjectOutputStream output) {
		try {	
			output.writeObject(outcome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Message updateChat(String users[], String msgText) {
		String[] sampleResponse = new String[] {
				"Hello there, how's it going?",
				"I am fine, thanks for asking",
				"The weather is pleasent here",
				"Alright, bye"
		};
		resHistory++;
		// Take in coming messages and add them to both users version of the chat
		// history.
		// Or simply send unchanged history if no message

		// convert user IDs to ints
		int user1 = findUser(UserList, users[0]).getUserID();
		int user2 = findUser(UserList, users[1]).getUserID();
		
		
		// Update each chat history, only if message contains text
		if (msgText != "") {
			chatHists[user1][user2] = chatHists[user1][user2] + "\n" + users[user1] + ": " + msgText;
			chatHists[user2][user1] = chatHists[user2][user1] + "\n" + users[user1] + ": " + msgText;
		}
		
		
		// Create message with new chat history
		return new Message("chat", users, sampleResponse[resHistory]);

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