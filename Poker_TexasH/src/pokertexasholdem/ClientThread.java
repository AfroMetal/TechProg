package pokertexasholdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread implements Runnable {
    
    private Client client;
    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private ConnectionWindow connectionWindow;
    private ClientWindow clientWindow;
    
    public ClientThread( Client client, Socket socket ) {
        this.client = client;
        this.socket = socket;
        
        connectionWindow = client.getConnectionWindow();
    }
    
    private void listenSocket() throws NullPointerException, IOException {
        
        String message;
        
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (NullPointerException e) {
            connectionWindow.setTitleText("No connection");
        } catch (NumberFormatException e) {
            connectionWindow.setTitleText("NumberFormatException");
        } catch (UnknownHostException e) {
            connectionWindow.setTitleText("Unknown host");
        } catch (IOException e) {
            connectionWindow.setTitleText("No I/O");
        } catch (IllegalArgumentException e) {
            connectionWindow.setTitleText("Wrong port");
        }
        
        while (true) {
            message = in.readLine();
            
            if (message.startsWith("[CONNECTED]")) {
                connectionWindow.setBtnConnectEnabled(false);
                connectionWindow.setTitle("Waiting for opponents...");
                connectionWindow.setBtnConnectText("Connected!");
            }
            
            if (message.startsWith("CREATEGUI")) {
                client.createClientWindow();
                clientWindow = client.getClientWindow();
            }
            
            if (message.startsWith("GAMEREADY")) {
                clientWindow.setVisible(true);
            }
            
            if (message.startsWith("MONEY")) {
                // parts[0] = "MONEY" parts[1] = "Player#" parts[2] = <amount>
                String[] parts = message.split(" ");
                clientWindow.setMoney(parts[1], parts[2]);
            }
            
            if (message.startsWith("NAME")) {
                // parts[0] = "NAME" parts[1] = "Player#" parts[2] = <name>
                String[] parts = message.split(" ");
                clientWindow.setName(parts[1], parts[2]);
            }
            
            if (message.startsWith("INFO")) {
                String info = message.substring(5);
                clientWindow.setLblInfoText(info);
            }
            
            if (message.startsWith("DEALER")) {
                String dealer = message.substring(7);
                clientWindow.setDealer(dealer);
            }
            
            if (message.startsWith("ACTOR")) {
                String actor = message.substring(6);
                clientWindow.setActor(actor);
            }
            
            if (message.startsWith("RESETROUND")) {
                clientWindow.setLblPotValue("");
                for (String cardAndIndexDealName : clientWindow.getMapLblCardCommunity().keySet()) {
                    clientWindow.setCardCommunity(cardAndIndexDealName, "");
                }
                for (String playerAndIndex : clientWindow.getMapLblAction().keySet()) {
                    clientWindow.setAction(playerAndIndex, "");
                }
                for (String playerAndIndex : clientWindow.getMapLblBet().keySet()) {
                    clientWindow.setBet(playerAndIndex, "");
                }
                for (String cardAndIndexPlayerAndIndex : clientWindow.getMapLblCardPlayer().keySet()) {
                    clientWindow.setCardPlayer(cardAndIndexPlayerAndIndex, "");
                }
                clientWindow.setCards("", "");
            }
            // TODO implement in Table
            if (message.startsWith("CARDPLAYER")) {
                // parts[0] = "CARDPLAYER" parts[1] = "Card#Player#" parts[2] =
                // <rank><suit>
                
            }
            
            if (message.startsWith("YOURCARDS")) {
                // parts[0] = "YOURCARDS" parts[1] = <rankCard1><suitCard1>
                // parts[2] = <rankCard2><suitCard2>
                String[] parts = message.split(" ");
                clientWindow.setCards(parts[1], parts[2]);
            }
        }
    }
    
    @Override
    public void run() {
        try {
            listenSocket();
        } catch (NullPointerException e) {
            System.out.println("[THREAD] listenSocket() NullPointerException");
        } catch (IOException e) {
            System.out.println("[THREAD] listenSocket() I/O Exception");
        }
    }
    
}
