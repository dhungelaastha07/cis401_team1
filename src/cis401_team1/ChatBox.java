package cis401_team1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ChatBox implements ActionListener {
	Client activeClient;
	JPanel messagePanel;
	JTextArea msgBox;
	JTextField typeBox;
	String	sendTo;
	String sender;
	
	public ChatBox(Client _activeClient, String chatUser, String chatPartner) {
		this.activeClient = _activeClient;
		messagePanel = new JPanel(new GridLayout(2, 1));
		sender = chatUser;
		if (chatPartner == "Aastha") {
			sendTo = "0";
		}else if(chatPartner == "Brandy") {
			sendTo = "1";
			
		}else if(chatPartner == "Tim") {
			sendTo = "2";
		
		}else if(chatPartner == "Nicolai") {
			sendTo = "3";
		}
		
		msgBox = new JTextArea();
		msgBox.setBackground(new Color(99, 173, 242));
		msgBox.setEditable(false);
		JScrollPane scrollableTextArea = new JScrollPane(msgBox);

		typeBox = new JTextField();
		typeBox.addActionListener(this);

		messagePanel.add(scrollableTextArea);
		messagePanel.add(typeBox, BorderLayout.SOUTH);
		

		int delay = 1000;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					activeClient.output.writeObject(new Message("chat", new String[] { sender, sendTo }, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Timer(delay, taskPerformer).start();

	}

	JPanel getPanel() {
		return this.messagePanel;
	}

	public void updateChatPanel(String msg) {
		// msg = "Aastha: " + msg;
		// String currentChat = msgBox.getText();
		// msgBox.setText(currentChat + msg);
		msgBox.setText(msg);
		Rectangle rect = new Rectangle(0,msgBox.getHeight(),10,10);
		msgBox.scrollRectToVisible(rect);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = typeBox.getText() + "\n";
		// String currentChat = msgBox.getText();
		// msg = "\nYou: " + msg;
		if (msg != null) {
			typeBox.setText("");
			// msgBox.setText(currentChat + msg);
			// msgBox.setText(currentChat + msg);
			// this.activeClient.updateChatScreen(this.messagePanel);
			try {
				String chatIDs[];
				chatIDs = new String[2];
				chatIDs[0] = sender;
				chatIDs[1] = sendTo;
				activeClient.output.writeObject(new Message("chat", chatIDs, msg));

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
