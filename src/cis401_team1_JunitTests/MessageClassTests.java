package cis401_team1_JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import cis401_team1.*;

public class MessageClassTests {

	@Test
	public void test1() {
		//getType test
		String[] users = new String[] {"2"};
		Message msg = new Message("login", users, "success");
		assertTrue("login" == msg.getType());
	}

	@Test
	public void test2() {
		//getID test
		String[] users = new String[] {"1"};
		Message msg = new Message("text", users, "hello!");
		assertTrue(2 == msg.getID());
	}

	@Test
	public void test3() {
		//getText test
		String[] users = new String[] {"0"};
		Message msg = new Message("text", users, "hello, how are you?");
		assertEquals("hello, how are you?", msg.getText());
	}

	@Test
	public void test4() {
		//getUsers test
		String[] users = new String[2];
		users[0] = "0";
		users[1] = "3";
		String[] expectedResult = new String[] {"0","3"};
		Message msg = new Message("text", users, "hello, how are you?");
		assertArrayEquals(expectedResult, msg.getUsers());
	}
}
