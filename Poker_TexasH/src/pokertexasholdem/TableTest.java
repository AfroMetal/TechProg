package pokertexasholdem;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TableTest {
    
    private static Table table;
    private static int playersAmount;
    private static int money;
    private static int smallBlind;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        playersAmount = 4;
        money = 500;
        smallBlind = 10;
    }
    
    @Before
    public void setUp() throws Exception {
        table = new Table(playersAmount, money, smallBlind);
    }
    
    @Test
    public void testRun() {
        //TODO: write tests to infinite loop run()
    }
    
}
