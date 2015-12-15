package pokertexasholdem;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    private Socket socket;
    private ClientThread clientThread;
    private PrintWriter out;
    
    private ConnectionWindow connectionWindow;
    private ClientWindow clientWindow;
    
    Client() {
        connectionWindow = new ConnectionWindow(this);
    }
    
    public void connect(String adress, String port, String name) {
        try {
            
            socket = new Socket(adress, Integer.parseInt(port));
            
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(name);
            } catch (IOException e) {
                connectionWindow.setTitle("No I/O");
            }
            
            clientThread = new ClientThread(this, socket);
            
            Thread t = new Thread(clientThread);
            t.start();
            
        } catch (IOException e) {
            connectionWindow.setTitle("I/O Exception");
        } catch (NullPointerException e) {
            connectionWindow.setTitle("Connection failed");
            return;
        } catch (NumberFormatException e) {
            connectionWindow.setTitle("Wrong port: " + port);
            return;
        }
    }
    
    public static void main(String[] args) {
        
        new Client();
    }
    
    public void createClientWindow() {
        clientWindow = new ClientWindow(this);
    }
    
    /**
     * @return the connectionWindow
     */
    public ConnectionWindow getConnectionWindow() {
        return connectionWindow;
    }

    /**
     * @return the clientWindow
     */
    public ClientWindow getClientWindow() {
        return clientWindow;
    }


}
