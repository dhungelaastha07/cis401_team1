package cis401_team1;

import java.io.Serializable;

// must implement Serializable in order to be sent
public class Message implements Serializable{
    private static int count = 0;
    private final int id;
    private final String text;
    private final String type;

    public Message(String text) {
        this.text = text;
        this.type = "default";
        this.id = ++count;
    }

    public String getText() {
        return text;
    }

    public int getID(){
        return id;
    }

    public String getType(){
        return type;
    }
}

