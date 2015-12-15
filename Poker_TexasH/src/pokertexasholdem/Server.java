package pokertexasholdem;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private int playersNumber;
	private int money;
	private int smallBlind;
	private int playersCounter = 0;
	
	private ServerSocket socket;
	
	private ArrayList<Socket> playersSockets = new ArrayList<Socket>();
	
	Server(int playersNumber, int money, int smallBlind, int port) {
		
		this.playersNumber = playersNumber;
		this.money = money;
		this.smallBlind = smallBlind;
		
		try {
			socket = new ServerSocket(port);
		}
		catch(IOException ioe) {
			System.out.println("[SERVER] Could not listen on port" + port);
			System.exit(-1);
		}
	}
	
	public void acceptPlayers() {
		while(true) {
			
			if(playersCounter < playersNumber) {
				
				try {
					playersSockets.add(socket.accept());
					PrintWriter output = null;
					try {
						output = new PrintWriter(playersSockets.get(playersCounter).getOutputStream(), true);
					}
					catch(IOException ioe) {
						System.out.println("[SERVER] IO Exception");
					}
					
					output.println("Server: Connected");
					playersCounter++;
				}
				catch(IOException ioe) {
					System.out.println("[SERVER] accept failed");
				}
			}
			else {
				if(playersCounter == playersNumber) {
					new Table(playersSockets, money, smallBlind);
					System.out.println("[SERVER] Table constructed for " + playersNumber + " players with $" + money + " and small blind of " + smallBlind);
				}
				try {
					PrintWriter output = new PrintWriter(socket.accept().getOutputStream(), true);
					output.println("Server: Sorry to inform you that game already started. Without you. How sad.");
				}
				catch(IOException ioe) {
					System.out.println("[SERVER] accept failed");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		int playersNumber = 0;
		int money = 0;
		int smallBlind = 0;
		int port = 0;
		//TODO check if players <11 and smallBlind<money
		try {
			playersNumber = Integer.parseInt(args[0]);
			money = Integer.parseInt(args[1]);
			smallBlind = Integer.parseInt(args[2]);
			port = Integer.parseInt(args[3]);
		}
		catch(NumberFormatException nfe) {
			System.out.println("Arguments have to be numbers representing in order: <players number> <start money> <small blind> <port>");
			return;
		}
		catch(ArrayIndexOutOfBoundsException aie) {
			System.out.println("Give 4 number parameters in order: <players number> <start money> <small blind> <port>");
			return;
		}
		
		Server server = new Server(playersNumber, money, smallBlind, port);
		server.acceptPlayers();
	}
}
