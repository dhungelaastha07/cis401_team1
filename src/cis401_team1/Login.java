package cis401_team1;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.Border;

public class Login implements ActionListener {
	Client activeClient;
	JPanel loginPanel;
	JLabel titleLabel, usernameLabel, passwordLabel, error;
	JTextField username;
	JPasswordField password;
	JButton login;

	Login(Client _activeClient) {
		this.activeClient = _activeClient;
		loginPanel = new JPanel(new GridLayout(9, 0));
		
		// title
		titleLabel = new JLabel();
		titleLabel.setText("Login to your account");
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		loginPanel.add(titleLabel);
		
		// username 
		usernameLabel = new JLabel();
		usernameLabel.setText("Username");
		username = new JTextField();
		loginPanel.add(usernameLabel);
		loginPanel.add(username);
		
		// password 
		passwordLabel = new JLabel();
		passwordLabel.setText("Password");
		password = new JPasswordField();
		loginPanel.add(passwordLabel);
		loginPanel.add(password);
		
		// message
		error = new JLabel();
		error.setForeground(Color.RED);
		loginPanel.add(error);
		
		// login
		login = new JButton("Login");
		login.setBackground(new Color(99, 173, 242));
		login.setOpaque(true);
		login.setBorderPainted(false);
		login.addActionListener(this);
		loginPanel.add(login);
			

		// adding padding
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		loginPanel.setBorder(padding);

	}
	
	JPanel getPanel() {
		return this.loginPanel;
	}

	// TODO:need to connect to the server logic
		
	@Override
	public void actionPerformed(ActionEvent e) {
		String usernameInput = username.getText();
		String passwordInput = new String(password.getPassword());
		
		if(!usernameInput.isEmpty() && !passwordInput.isEmpty()) {
			if(e.getActionCommand().equals("Login")) {
				try {
					activeClient.output.writeObject(new Message("login", new String[] {usernameInput}, passwordInput));
				} catch (IOException e1) {
					error.setText("Something went wrong.");
					e1.printStackTrace();
				}
			} 
		} else {
			error.setText("Please fill up both fields");
		}
		
	}
}