import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Login extends JFrame implements ActionListener {
	JPanel loginPanel;
	JLabel titleLabel, usernameLabel, passwordLabel, error;
	JTextField username;
	JPasswordField password;
	JButton login, signup;

	Login() {
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
		
		
		
		// signup
		signup = new JButton("Signup");
		signup.setBackground(new Color(167, 204, 237));
		signup.setOpaque(true);
		signup.setBorderPainted(false);
		signup.addActionListener(this);
		loginPanel.add(signup);
		
		
		
		

		// adding padding
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		loginPanel.setBorder(padding);

		// panel setup
		add(loginPanel);
		setTitle("Generic Messenger System");
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// TODO:need to connect to the server logic
		
	@Override
	public void actionPerformed(ActionEvent e) {
		String usernameInput = username.getText();
		String passwordInput = new String(password.getPassword());
		
		if(!usernameInput.isEmpty() && !passwordInput.isEmpty()) {
			if(e.getActionCommand().equals("Login")) {
				// Login flow
				// Pass uname/password
				// Wait for reply
				// If valid, show contact list panel
				// If error 
				
			} else {
				// Signup flow
				// Pass uname/password
				// Wait for reply
				// If valid, show contact list panel
				// If error 
			}
		} else {
			error.setText("Please fill up both fields");
		}
		
	}
}