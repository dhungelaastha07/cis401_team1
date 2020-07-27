package cis401_team1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class ContactPage implements ActionListener {
	Client activeClient;
	JPanel contactPanel;
	JLabel titleLabel;
	JButton contactName1, contactName2, contactName3, contactName4, addNewContact;

	// title
	ContactPage(Client _activeClient) {
		this.activeClient = _activeClient;
		contactPanel = new JPanel(new GridLayout(10, 0));
		titleLabel = new JLabel();
		titleLabel.setText("Your Contacts");
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		contactPanel.add(titleLabel);

		// contactname1
		contactName1 = new JButton("Aastha");
		contactName1.setBackground(new Color(99, 173, 242));
		contactName1.setOpaque(true);
		contactName1.setBorderPainted(false);
		contactName1.addActionListener(this);
		contactPanel.add(contactName1);
		contactPanel.add(new JSeparator());

		// contactname2
		contactName2 = new JButton("Brandy");
		contactName2.setBackground(new Color(99, 173, 242));
		contactName2.setOpaque(true);
		contactName2.setBorderPainted(false);
		contactName2.addActionListener(this);
		contactPanel.add(contactName2);
		contactPanel.add(new JSeparator());

		// contactname3
		contactName3 = new JButton("Tim");
		contactName3.setBackground(new Color(99, 173, 242));
		contactName3.setOpaque(true);
		contactName3.setBorderPainted(false);
		contactName3.addActionListener(this);
		contactPanel.add(contactName3);
		contactPanel.add(new JSeparator());

		// contactname4
		contactName4 = new JButton("Nicolai");
		contactName4.setBackground(new Color(99, 173, 242));
		contactName4.setOpaque(true);
		contactName4.setBorderPainted(false);
		contactName4.addActionListener(this);
		contactPanel.add(contactName4);
		contactPanel.add(new JSeparator());

		// add button
		addNewContact = new JButton("Add New Contacts");
		addNewContact.setBackground(new Color(167, 204, 237));
		addNewContact.setOpaque(true);
		addNewContact.setBorderPainted(false);
		addNewContact.addActionListener(this);
		contactPanel.add(addNewContact);

		// adding padding
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		contactPanel.setBorder(padding);
	}

	JPanel getPanel() {

		return this.contactPanel;
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("Aastha")) {
			// take user to chat with Aastha
			this.activeClient.setChatScreen(command);
		} else if (command.equals("Brandy")) {
			this.activeClient.setChatScreen(command);
		} else if (command.equals("Tim")) {
			this.activeClient.setChatScreen(command);
			// take user to chat with Tim
		} else if (command.equals("Nicolai")) {
			this.activeClient.setChatScreen(command);
		} else if (command.equals("Add New Contacts")) {
			// add new contacts page
		} else {
			System.out.print("Invalid");
		}
	}

}
