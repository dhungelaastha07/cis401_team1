package JunitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Message_TextTest.class, Message_TypeTest.class, Message_UserArrayTest.class })
public class AllTests {

}
