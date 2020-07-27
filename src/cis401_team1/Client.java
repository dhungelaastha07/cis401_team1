package cis401_team1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;
import javax.swing.*;

public class Client {
	ChatBox chatbox;
	Socket socket;
	JPanel latestPanel;
	JFrame mainFrame;
	String loggedInUser;
	String chattingWith;
	String host = "localhost";
	int port = 7777;
	ObjectInputStream input;
	ObjectOutputStream output;
	Hashtable<String, User> userList;

	public Client() throws UnknownHostException, IOException, ClassNotFoundException {
		this.latestPanel = null;
		this.loggedInUser = null;
		this.chattingWith = null;

		// Connect to the ServerSocket at host:port

		this.mainFrame = new JFrame();
		JPanel loginPanel = new Login(this).getPanel();
		this.latestPanel = loginPanel;
		mainFrame.add(loginPanel);
		mainFrame.setTitle("Generic Messenger System");
		mainFrame.setSize(400, 400);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.socket = new Socket(host, port);
		this.output = new ObjectOutputStream(this.socket.getOutputStream());
		this.output.flush();
		this.input = new ObjectInputStream(this.socket.getInputStream());

		startRunning();
	}

	public void startRunning() throws IOException, ClassNotFoundException {
		Message msg = null;

		while (msg == null || !msg.getType().equals("quit")) {
			msg = (Message) input.readObject();
			System.out.println(msg);

			if (msg.getType().equals("login")) {
				this.setLoggedInUser(msg.getUsers()[0]);
			} else if (msg.getType().equals("chat")) {
				this.chatbox.updateChatPanel(msg.getText());
			} else if (msg.getType().equals("logoff")) {

			} else if (msg.getType().equals("quit")) {

			} else {
				System.out.println("Invalid message type.....\n");

			}

		}
	}

	public User getClientByUsername(String username) {
		return this.userList.get(username);
	}

	public void setLoggedInUser(String _loggedInUser) throws IOException, ClassNotFoundException {
		this.loggedInUser = _loggedInUser;
		JPanel contactPanel = new ContactPage(this).getPanel();
		this.setPanel(contactPanel);
		this.latestPanel = contactPanel;
		
	}

	public void setChatScreen(String chattingWith) {
		System.out.println("With: " + chattingWith);
		this.chatbox = new ChatBox(this, this.loggedInUser, chattingWith);
		this.chattingWith = chattingWith;
		JPanel chatPanel = this.chatbox.getPanel();
		this.setPanel(chatPanel);
		this.latestPanel = chatPanel;
	}

	public void updateChatScreen(JPanel chatBox) {
		this.setPanel(chatBox);
		this.latestPanel = chatBox;
	}

	public void setPanel(JPanel panel) {
		if (this.latestPanel != null) {
			mainFrame.remove(this.latestPanel);
		}
		mainFrame.add(panel);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		try {
			Client x = new Client();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
