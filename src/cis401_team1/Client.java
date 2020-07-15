package cis401_team1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
        System.out.print("Enter the port number to connect to: <7777>");
        int port = sc.nextInt();
        System.out.print("Enter the host address to connect to: <localhost> ");
        String host = sc.next();

        // Connect to the ServerSocket at host:port
        Socket socket = new Socket(host, port);
        System.out.println("Connected to " + host + ":" + port);

        // Output stream socket.
        OutputStream outputStream = socket.getOutputStream();
        // Create object output stream from the output stream to send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        
        // input stream from the connected socket to the server
        InputStream inputStream = socket.getInputStream();
        // create a ObjectInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        
        Login log = new Login();
        //Login 
        int numUsers = 1;
        String [] userLogin = new String[numUsers];
        userLogin[numUsers-1] = log.username.getText();
        String pass = log.password.getText();
        Message login = new Message("login", userLogin, pass);
        objectOutputStream.writeObject(login);
       
        //confirmation message
        try {
        Message confirmation = (Message) objectInputStream.readObject();
       
	       while(confirmation.getText() != "yes")
	       {
	    	   //display a message asking user to re-input login
	       }
	       
	    //once successfully logged on, open contact page 
    	//create Users for the contacts & link usernames and userID to the buttons
        new ContactPage();
        //if contact user.getID() != current userID then they can message each other
        numUsers = 2;
        String [] userChat = new String[numUsers];
        userChat[numUsers-2] = log.username.getText();
        // userChat[numUsers-1] = contactListUser;
        // String msg = get text from JTextFrame?
        
        //create while loop for sending messages until user logs off
//		  Message chat = new Message("Message", userChat, msg);
//        objectOutputStream.writeObject(chat);
//        try {
//        Message chatHist = (Message) objectInputStream.readObject();
//          //display chatHist.getText() to GUI;
//        }
//        catch (Exception f)
//        {
//        	//error message
//        }
        
       }
       catch (Exception e) {
    	   
       }
       
        
       
       //logout
//       Message logout = new Message("logout", user, "");
       socket.close();
    }
}
