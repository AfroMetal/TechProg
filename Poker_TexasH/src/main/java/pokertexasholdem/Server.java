package pokertexasholdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    
    private static volatile Server instance = null;
    private static int playersNumber;
    private static int money;
    private static int smallBlind;
    private static int port;
    private int playersCounter = 0;
    
    private ServerSocket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    
    private HashMap<Socket, String> playersSocketsNames = new HashMap<Socket, String>();
    
    private Server() {
        try {
            socket = new ServerSocket(port);
        } catch (IOException ioe) {
            System.out.println("[SERVER] Could not listen on port" + port);
            System.exit(-1);
        }
        System.out.println("[SERVER] Started with settings: players=" + playersNumber + ", money=$" + money
                + ", smallBlind=$" + smallBlind + ", on port=" + port);
    }
    
    public static Server getInstance() {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }
    
    public void acceptPlayers() {
        while (true) {
            
            if (playersCounter < playersNumber) {
                
                try {
                    Socket socket = this.socket.accept();
                    System.out.println("[SERVER] Player" + playersCounter + " accepted.");
                    try {
                        out = new PrintWriter(socket.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String name = in.readLine();
                        System.out.println("[SERVER] Got player name: " + name);
                        playersSocketsNames.put(socket, name);
                    } catch (IOException ioe) {
                        System.out.println("[SERVER] IO Exception");
                    }
                    
                    out.println("[CONNECTED]");
                    playersCounter++;
                } catch (IOException ioe) {
                    System.out.println("[SERVER] accept failed");
                }
                
                if (playersCounter == playersNumber) {
                    Table table = new Table(playersSocketsNames, money, smallBlind);
                    System.out.println("[SERVER] Table constructed for " + playersNumber + " players with $" + money
                            + " and small blind of " + smallBlind);
                    table.prepareClients();
                    table.run();
                    playersSocketsNames = new HashMap<Socket, String>();
                    playersCounter = 0;
                }
                
            } else {
                try {
                    Socket socket = this.socket.accept();
                    System.out.println("[SERVER] Player" + playersCounter + " accepted.");
                    try {
                        out = new PrintWriter(socket.getOutputStream(), true);
                    } catch (IOException ioe) {
                        System.out.println("[SERVER] IO Exception");
                    }
                    
                    out.println("[GAMESTARTED]");
                } catch (IOException ioe) {
                    System.out.println("[SERVER] accept failed");
                }
                
                /*
                 * try { PrintWriter output = new
                 * PrintWriter(this.socket.accept().getOutputStream(), true);
                 * output.println("[GAMESTARTED]"); } catch (IOException ioe) {
                 * System.out.println("[SERVER] accept failed"); }
                 */
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO check if players <11 and smallBlind<money
        try {
            playersNumber = Integer.parseInt(args[0]);
            money = Integer.parseInt(args[1]);
            smallBlind = Integer.parseInt(args[2]);
            port = Integer.parseInt(args[3]);
        } catch (NumberFormatException nfe) {
            System.out.println(
                    "Arguments have to be numbers representing in order: <players number> <start money> <small blind> <port>");
            return;
        } catch (ArrayIndexOutOfBoundsException aie) {
            System.out
                    .println("Give 4 number parameters in order: <players number> <start money> <small blind> <port>");
            return;
        }
        
        Server server = getInstance();
        server.acceptPlayers();
    }
    
}
