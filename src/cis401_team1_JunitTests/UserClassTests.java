package cis401_team1_JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import cis401_team1.*;

public class UserClassTests {

	@Test
	public void test1() {
		//getUsername test
		User test1 = new User("Bob", "1234");
		assertEquals("Bob",test1.getUsername());
	}

	@Test
	public void test2() {
		//getPassword test
		User test2 = new User("Ann", "1234");
		assertEquals("1234",test2.getPassword());
	}

	@Test
	public void test3() {
		//getUserID test
		User test3 = new User("Phoebe", "1234");
		assertTrue(2 == test3.getUserID());	//third user made, so ID should be 2
	}

}
