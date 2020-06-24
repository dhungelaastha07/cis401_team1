import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class ContactPage extends JFrame {
	
	JPanel contactPanel;
	JLabel titleLabel;
	JButton contactName1,contactName2,contactName3,contactName4;
	
	//title 
	ContactPage(){
		contactPanel = new JPanel(new GridLayout(8, 0));
		titleLabel = new JLabel();
		titleLabel.setText("Your Contacts");
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		contactPanel.add(titleLabel);
		
		
		// contactname1
		contactName1 = new JButton("Aastha");
		contactName1.setBackground(new Color(113, 128, 245));
		contactName1.setOpaque(true);
		contactName1.setBorderPainted(false);
		//contactName1.addActionListener(this);
		contactPanel.add(contactName1);
		contactPanel.add(new JSeparator());
		

		// contactname2
		contactName2 = new JButton("Brandy");
		contactName2.setBackground(new Color(113, 128, 245));
		contactName2.setOpaque(true);
		contactName2.setBorderPainted(false);
		//contactName2.addActionListener(this);
		contactPanel.add(contactName2);
		contactPanel.add(new JSeparator());
		

		// contactname3
		contactName3 = new JButton("Tim");
		contactName3.setBackground(new Color(113, 128, 245));
		contactName3.setOpaque(true);
		contactName3.setBorderPainted(false);
		//contactName3.addActionListener(this);
		contactPanel.add(contactName3);
		contactPanel.add(new JSeparator());
		

		// contactname4
		contactName4 = new JButton("Nicolai");
		contactName4.setBackground(new Color(113, 128, 245));
		contactName4.setOpaque(true);
		contactName4.setBorderPainted(false);
		//contactName4.addActionListener(this);
		contactPanel.add(contactName4);
		
		// adding padding
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		contactPanel.setBorder(padding);

		// panel setup
		add(contactPanel);
		setTitle("Generic Messenger System");
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			String usernameInput = username.getText();
//			String passwordInput = new String(password.getPassword());
//			
//			if(!usernameInput.isEmpty() && !passwordInput.isEmpty()) {
//				if(e.getActionCommand().equals("Login")) {
//					// Login flow
//					// Pass uname/password
//					// Wait for reply
//					// If valid, show contact list panel
//					// If error 
//					
//				} else {
//					// Signup flow
//					// Pass uname/password
//					// Wait for reply
//					// If valid, show contact list panel
//					// If error 
//				}
//			} else {
//				error.setText("Please fill up both fields");
//			}
//			
//		}
		
		
	}
}
