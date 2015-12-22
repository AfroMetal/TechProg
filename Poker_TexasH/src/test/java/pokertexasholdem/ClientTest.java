package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import pokertexasholdem.client.Client;

public class ClientTest {
    
    private static Client client;
    private static Client bot;
    private static String adress;
    private static String port;
    private static String nameClient;
    private static String nameBot;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        adress = "";
        port = "";
        nameClient = "A";
        nameBot = "B";
    }
    
    @Test
    public void test() {
        client = new Client();
        
        client.connect(adress, port, nameClient, false);
        
        assertNotNull(client);
        assertNotNull(client.getConnectionWindow());
        //assertNotNull(client.getClientThread());
        
        bot = new Client();
        
        bot.connect(adress, port, nameBot, true);
        
        assertNotNull(bot);
        assertNotNull(bot.getConnectionWindow());
        //assertNotNull(bot.getClientThread());
        assertNull(bot.getClientWindow());
    }
    
}
