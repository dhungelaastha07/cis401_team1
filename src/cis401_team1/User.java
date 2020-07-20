package cis401_team1;
public class User {
	private static int count = -1;
	private int userID;
	private String username;
	private String password;
	

	public User(String userName, String passWord) {
		this.username = userName;
		this.password = passWord;
		this.userID = ++count;
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
