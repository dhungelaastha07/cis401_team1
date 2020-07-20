package cis401_team1;
import java.io.*;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.JOptionPane;

public class Client {
	Socket socket;
	JPanel latestPanel;
	JFrame mainFrame;
	User loggedInUser;
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	Hashtable<String, User> userList;

	public Client() {
		this.socket = null;
		this.latestPanel = null;
		this.loggedInUser = null;
		this.objectInputStream = null;
		this.objectOutputStream = null;
		this.userList = new Hashtable<String, User>();
		userList.put("aastha", new User("aastha", "1234"));
		userList.put("safal", new User("safal", "1234"));

		this.mainFrame = new JFrame();
		JPanel loginPanel = new Login(this).getPanel();
		this.latestPanel = loginPanel;
		mainFrame.add(loginPanel);
		mainFrame.setTitle("Generic Messenger System");
		mainFrame.setSize(400, 400);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public User getClientByUsername(String username) {
		return this.userList.get(username);
	}

	public void setLoggedInUser(User _loggedInUser) throws IOException {
		String host = "localhost";
		int port = 7777;
		this.loggedInUser = _loggedInUser;

		// Connect to the ServerSocket at host:port
		this.socket = new Socket(host, port);

		// Output stream socket.
		OutputStream outputStream = socket.getOutputStream();
		// Create object output stream from the output stream to send an object through
		// it
		this.objectOutputStream = new ObjectOutputStream(outputStream);

		objectOutputStream.writeUTF("USER_ONLINE="+this.loggedInUser.getUsername());
		objectOutputStream.flush();

		// input stream from the connected socket to the server
		InputStream inputStream = socket.getInputStream();
		// create a ObjectInputStream so we can read data from it.
		this.objectInputStream = new ObjectInputStream(inputStream);
		System.out.print(objectInputStream.readUTF());
		this.setPanel(new ContactPage().getPanel());
	}

	public void setPanel(JPanel panel) {
		if (this.latestPanel != null) {
			mainFrame.remove(this.latestPanel);
		}
		mainFrame.add(panel);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) throws IOException {

		Client x = new Client();

		// Login
//        int numUsers = 1;
//        String [] userLogin = new String[numUsers];
//        userLogin[numUsers-1] = log.username.getText();
//        String pass = log.password.getText();
//        Message login = new Message("login", userLogin, pass);
		// objectOutputStream.writeObject(login);

		// confirmation message
		try {
			// Message confirmation = (Message) objectInputStream.readObject();

//	       while(confirmation.getText() != "yes")
//	       {
//	    	   JOptionPane.showMessageDialog(null,"Error: Retry UserName and Password");
//	       }

			// once successfully logged on, open contact page
			// create Users for the contacts & link usernames and userID to the buttons
			// new ContactPage();

			// if contact user.getID() != current userID then they can message each other
//        numUsers = 2;
//        String [] userChat = new String[numUsers];

		} catch (Exception e) {

		}

		// logout
//       Message logout = new Message("logout", user, "");
		// socket.close();
	}
}
