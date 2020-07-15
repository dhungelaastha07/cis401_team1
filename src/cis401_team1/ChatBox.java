import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ChatBox extends JFrame implements ActionListener{
	
	JPanel messagePanel;
	JLabel titleLabel,message1,message2, message3;
	JTextField textfield1, textfield2;
	JButton sendButton, exitButton;
	
	

	public ChatBox() {
		
			messagePanel = new JPanel((new GridLayout(13,0)));
			
			// Chat messages title
			titleLabel = new JLabel();
			titleLabel.setText("Chat Messages");
			titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
			messagePanel.add(titleLabel);
			
			//message1
			message1 = new JLabel();
			message1.setText("Hi, I am Aastha. How are you?");
			message1.setFont(new Font("Verdana", Font.BOLD, 18));
			message1.setBackground(new Color(208, 211, 143));
			message1.setOpaque(true);
			messagePanel.add(message1);
			messagePanel.add(new JSeparator());
			
			//message2
			message2 = new JLabel();
			message2.setText("Hi, I am Safal.I am fine.");
			message2.setFont(new Font("Verdana", Font.BOLD, 18));
			message2.setBackground(new Color(246, 202, 131));
			message2.setOpaque(true);
			messagePanel.add(message2);
			messagePanel.add(new JSeparator());
			
			
			
			message3 = new JLabel();
			message3.setFont(new Font("Verdana", Font.BOLD, 18));
			message3.setBackground(new Color(246, 202, 131));
			message3.setOpaque(true);
			messagePanel.add(message3);
			messagePanel.add(new JSeparator());
			
			//textfeilds
			
			textfield1 = new JTextField("Text field 1",15);
//			textfield2 = new JTextField("Text field 2",15);
			messagePanel.add(textfield1);
			messagePanel.add(new JSeparator());
//			messagePanel.add(textfield2);
			
			//send Button
			sendButton = new JButton("Send");
			sendButton.setBackground(new Color(208, 211, 143));
			sendButton.setOpaque(true);
			sendButton.setBorderPainted(false);
			sendButton.addActionListener(this);
			messagePanel.add(sendButton);
			messagePanel.add(new JSeparator());
			
			//exit button
			exitButton = new JButton("EXIT");
			exitButton.setBackground(new Color(246, 202, 131));
			exitButton.setOpaque(true);
			exitButton.setBorderPainted(false);
			exitButton.addActionListener(this);
			messagePanel.add(exitButton);
			
			
			// adding padding
			Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
			messagePanel.setBorder(padding);

			// panel setup
			add(messagePanel);
			setTitle("Generic Messenger System");
			setSize(400, 400);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = textfield1.getText();
		if(e.getActionCommand().equals("Send")) {
			message3.setText(msg);
		} 
	}
		
	}


