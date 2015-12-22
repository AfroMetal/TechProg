package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServerTest {
    
    private static Server server;
    private static Server server2;
    
    @Test
    public void test() {
        assertNull(server);
        server = Server.getInstance();
        assertNotNull(server);
        server2 = Server.getInstance();
        assertSame(server, server2);
        
        
    }
    
}
