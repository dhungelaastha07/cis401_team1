package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import cis401_team1.Message;

public class Message_UserArrayTest {

	@Test
	public void test() {
		String userID[];
		userID = new String[2];
		userID[0] = "1";
		Message test = new Message("Chat", userID ,"hey");
		assertEquals("1", test.getUserID()[0]);
	}

}
