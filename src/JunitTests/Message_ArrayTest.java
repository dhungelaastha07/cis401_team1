package JunitTests;
import cis401_team1.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Message_ArrayTest {

	@Test
	void test() {
		String userID[];
		userID = new String[2];
		userID[0] = "1";
		Message test = new Message("Chat", userID ,"hey");
		assertEquals("1", test.getUserID()[0]);
	}

}
