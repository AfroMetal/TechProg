package pokertexasholdem.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.SpinnerNumberModel;

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
        
        String message = "";
        
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
        
        while (message != null) {
            message = in.readLine();
            System.out.println("[THREAD] Got message: " + message);
            
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
                clientWindow.setAutoRequestFocus(true);
                clientWindow.setVisible(true);
                connectionWindow.setVisible(false);
                clientWindow.toFront();
            }
            
            if (message.startsWith("NOWACT")) {
                String[] parts = message.split("#");
                Integer minBet = Integer.parseInt(parts[0].substring(7));
                String[] legalActions = parts[1].substring(1).split(" ");
                clientWindow.getSpinnerBetRaise().setModel(new SpinnerNumberModel(new Integer(minBet), new Integer(minBet), null, new Integer(5)));
                clientWindow.getSpinnerTextField().setText(minBet.toString());
                for (String btnAction : legalActions) {
                    clientWindow.getMapBtnAction().get(btnAction).setEnabled(true);
                }
                clientWindow.setLblInfoText("It is your turn");
            }
            
            if (message.startsWith("MONEY")) {
                // parts[0] = "Player#" parts[1] = <amount>
                String[] parts = message.substring(6).split(" ");
                clientWindow.setMoney(parts[0], parts[1]);
            }
            
            if (message.startsWith("NAME")) {
                // parts[0] = "Player#" parts[1] = <name>
                String[] parts = message.substring(5).split(" ");
                clientWindow.setName(parts[0], parts[1]);
            }
            
            if (message.startsWith("ACTION")) {
                // parts[0] = "Player#" parts[1] = <action>
                String[] parts = message.substring(7).split(" ");
                clientWindow.setAction(parts[0], parts[1]);
            }
            
            if (message.startsWith("BET")) {
                // parts[] = "Player#" parts[1] = <amount>
                String[] parts = message.substring(4).split(" ");
                clientWindow.setBet(parts[0], parts[01]);
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
            
            if (message.startsWith("POT")) {
                String value = message.substring(4);
                clientWindow.setLblPotValue(value);
            }
            
            if (message.startsWith("RESETBETS")) {
                for (String playerAndIndex : clientWindow.getMapLblAction().keySet()) {
                    clientWindow.setAction(playerAndIndex, "");
                }
                for (String playerAndIndex : clientWindow.getMapLblBet().keySet()) {
                    clientWindow.setBet(playerAndIndex, "");
                }
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
                // parts[0] = "Player#" parts[1] = <rank><suit> parts[2] = <rank><suit>
                // setCardPlayer("Card1" + parts[0], parts[1])
                String[] parts = message.substring(11).split(" ");
                clientWindow.setCardPlayer("Card1"+parts[0], parts[1]);
                clientWindow.setCardPlayer("Card2"+parts[0], parts[2]);
            }
            
            if (message.startsWith("VALUEPLAYER")) {
             // parts[0] = "Player#" parts[1] = <value>
                String[] parts = message.substring(12).split(" ");
                clientWindow.setBet(parts[0], parts[1]);
            }
            
            if (message.startsWith("CARDCOMMUNITY")) {
                // parts[0] = "Card#" parts[1] = <rank><suit>
                String[] parts = message.substring(14).split(" ");
                clientWindow.setCardCommunity(parts[0], parts[1]);
                clientWindow.setLblInfoText("Dealer draw card");
            }
            
            if (message.startsWith("YOURCARDS")) {
                // parts[0] = <rankCard1><suitCard1> parts[1] =
                // <rankCard2><suitCard2>
                String[] parts = message.substring(10).split(" ");
                clientWindow.setCards(parts[0], parts[1]);
            }
            System.out.println("[THREAD]    Done.");
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
