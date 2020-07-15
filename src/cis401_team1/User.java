package cis401_team1;

public class User {
	private static int userID = -1;
	private String username;
	private String password;

	public User(String userName, String passWord) {
		username = userName;
		password = passWord;
		userID++;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getUserID() {
		return userID;
	}
	
}
