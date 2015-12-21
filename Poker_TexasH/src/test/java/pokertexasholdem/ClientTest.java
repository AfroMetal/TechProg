package pokertexasholdem;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;

import pokertexasholdem.client.Client;


public class ClientTest {

	private static Client client;
	
	private static String adress;
	private static String port;
	private static String name;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		adress = "";
		port = "";
		name = "A";
	}

	@Test
	public void test() {
		client = new Client();
		
		client.connect(adress, port, name, false);
		
		assertNotNull(client);
		assertNotNull(client.getConnectionWindow());
	}

}
