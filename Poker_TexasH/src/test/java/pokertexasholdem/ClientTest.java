package pokertexasholdem;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.net.Socket;

import javax.sound.sampled.Port;

import org.junit.BeforeClass;
import org.junit.Test;

import pokertexasholdem.client.Client;
import pokertexasholdem.client.ClientThread;
import pokertexasholdem.client.ClientWindow;
import pokertexasholdem.client.ConnectionWindow;

public class ClientTest {

	private static Client client;
	private static Socket socket;
	private static ClientThread clientThread;
	private static PrintWriter out;
	private static ConnectionWindow connectionWindow;
	private static ClientWindow clientWindow;
	
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
		
		client.connect(adress, port, name);
		
		assertNotNull(client);
		assertNotNull(client.getConnectionWindow());
	}

}
