package pokertexasholdem;


import static org.junit.Assert.*;


import org.junit.Test;

public class ServerTest {
	
	@Test
	public void test() {
		assertNotNull(Server.getInstance());
		
	}

}
