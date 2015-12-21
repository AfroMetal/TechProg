package pokertexasholdem;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class ServerTest {
	
	private static Server server;
	
	private static int playersCounter = 0;
	private static PrintWriter out = null;
	private static BufferedReader in = null;
	private static Socket socket;
	private static HashMap<Socket, String> playersSocketsNames;
	private static int port;
	private static int money;
	private static int smallBlind;
	private static int playersNumber;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playersSocketsNames = new HashMap<Socket, String>();
		port = 0;
		money = 100;
		smallBlind = 10;
		playersNumber = 2;
		socket = null;
	}

	@Test
	public void test() {
		server = new Server(playersNumber, money, smallBlind, port);
	}

}
