package cis401_team1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ChatBox implements ActionListener {
	Client activeClient;
	JPanel messagePanel;
	JTextArea msgBox;
	JTextField typeBox;
	public ChatBox(Client _activeClient) {
		this.activeClient = _activeClient;
		messagePanel = new JPanel(new GridLayout(2, 1));
		
		msgBox = new JTextArea();
		msgBox.setBackground(new Color(99, 173, 242));
		msgBox.setEditable(false);
		JScrollPane scrollableTextArea = new JScrollPane(msgBox);  
		
		typeBox = new JTextField();
		typeBox.addActionListener(this);

		messagePanel.add(scrollableTextArea);
		messagePanel.add(typeBox, BorderLayout.SOUTH);
	}

	JPanel getPanel() {
		return this.messagePanel;
	}
	
	public void updateChatPanel(String msg) {
		msg = "Aastha: " + msg;
		String currentChat = msgBox.getText();
		msgBox.setText(currentChat + msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = typeBox.getText() + "\n";
		String currentChat = msgBox.getText();
		msg = "\nYou: " + msg;
		if(msg != null) {
			typeBox.setText("");
			msgBox.setText(currentChat + msg);
			this.activeClient.updateChatScreen(this.messagePanel);
			try {	
				activeClient.output.writeObject(new Message("chat", new String[] {"Tim", "Aastha"}, msg));

			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		}
	}

}
