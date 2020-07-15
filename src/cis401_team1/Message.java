package cis401_team1;

import java.io.Serializable;

// must implement Serializable in order to be sent
public class Message implements Serializable{
	private static int count = 0;
    private final int id;
    private final String text;
    private final String type;
    private final String userID[];

    public Message(String type, String userId[], String text) {
        this.text = text;
        this.userID = userId;
        this.type = type;
        this.id = ++count;	        
    }

    public String getText() {
        return text;
    }

    public int getID(){
        return id;
    }
	    
    public String[] getUserID() {
        return userID;
    }
	    
    public String getType(){
        return type;
    }
}


