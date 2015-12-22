package pokertexasholdem;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

public class TableTest {
    
    private static Table table;
    @Mock
    private static Table mockTable;
    private static Table toTest;
    private static int money;
    private static int smallBlind;
    private static HashMap<Socket, String> playersSocketsNames;
    private static Player player;
    private static Player player2;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        money = 1000;
        smallBlind = 200;
    }
    
    @Before
    public void setUpBefore() {
        playersSocketsNames = new HashMap<Socket, String>();
        playersSocketsNames.put(new Socket(), "A");
        playersSocketsNames.put(new Socket(), "B");
        player = new Player("player", null, money);
        player2 = new Player("player2", null, money);
        toTest = spy(new Table(playersSocketsNames, money, smallBlind));
        doReturn(mockTable).when(toTest).makeTable(null, money, smallBlind);
    }
    
    @Test
    public void testRunRaise() {
        //doReturn(mockTable).when(toTest).makeTable(null, money, smallBlind);
        toTest.setSleeptime(0);
        
        when(toTest.getActionFromResponse(anyString())).thenReturn(Action.RAISE);
        when(toTest.getBetFromResponse(anyString())).thenReturn(200);
        toTest.run();
        
        assertNotNull(toTest.getPlayersList());
        assertNotNull(toTest.getSmallBlind());
        assertEquals(200, toTest.getSmallBlind());
        assertNotNull(toTest.getBigBlind());
        assertEquals(400, toTest.getBigBlind());
        assertNotNull(toTest.getDeck());
        assertNotNull(toTest.getBoard());
        assertNotNull(toTest.getDealer());
        assertNotNull(toTest.getDealerPosition());
        assertNotNull(toTest.getActivePlayers());
        assertNotNull(toTest.getActor());
        assertNotNull(toTest.getActorPosition());
        assertNotNull(toTest.getPots());
        assertNotNull(toTest.getMinBet());
        assertNotNull(toTest.getBet());
        
    }
    
    @Test
    public void testRunCall() {
        //doReturn(mockTable).when(toTest).makeTable(null, money, smallBlind);
        toTest.setSleeptime(0);
        
        when(toTest.getActionFromResponse(anyString())).thenReturn(Action.CALL);
        when(toTest.getBetFromResponse(anyString())).thenReturn(200);
        toTest.run();
        
        assertNotNull(toTest.getPlayersList());
        assertNotNull(toTest.getSmallBlind());
        assertEquals(200, toTest.getSmallBlind());
        assertNotNull(toTest.getBigBlind());
        assertEquals(400, toTest.getBigBlind());
        assertNotNull(toTest.getDeck());
        assertNotNull(toTest.getBoard());
        assertNotNull(toTest.getDealer());
        assertNotNull(toTest.getDealerPosition());
        assertNotNull(toTest.getActivePlayers());
        assertNotNull(toTest.getActor());
        assertNotNull(toTest.getActorPosition());
        assertNotNull(toTest.getPots());
        assertNotNull(toTest.getMinBet());
        assertNotNull(toTest.getBet());
        
    }
    
    @Test
    public void testRunCheck() {
        //doReturn(mockTable).when(toTest).makeTable(null, money, smallBlind);
        toTest.setSleeptime(0);
        
        when(toTest.getActionFromResponse(anyString())).thenReturn(Action.CHECK);
        when(toTest.getBetFromResponse(anyString())).thenReturn(200);
        toTest.run();
        
        assertNotNull(toTest.getPlayersList());
        assertNotNull(toTest.getSmallBlind());
        assertEquals(200, toTest.getSmallBlind());
        assertNotNull(toTest.getBigBlind());
        assertEquals(400, toTest.getBigBlind());
        assertNotNull(toTest.getDeck());
        assertNotNull(toTest.getBoard());
        assertNotNull(toTest.getDealer());
        assertNotNull(toTest.getDealerPosition());
        assertNotNull(toTest.getActivePlayers());
        assertNotNull(toTest.getActor());
        assertNotNull(toTest.getActorPosition());
        assertNotNull(toTest.getPots());
        assertNotNull(toTest.getMinBet());
        assertNotNull(toTest.getBet());
        
    }
    
    @Test
    public void testRunBet() {
        //doReturn(mockTable).when(toTest).makeTable(null, money, smallBlind);
        toTest.setSleeptime(0);
        
        when(toTest.getActionFromResponse(anyString())).thenReturn(Action.BET);
        when(toTest.getBetFromResponse(anyString())).thenReturn(200);
        toTest.run();
        
        assertNotNull(toTest.getPlayersList());
        assertNotNull(toTest.getSmallBlind());
        assertEquals(200, toTest.getSmallBlind());
        assertNotNull(toTest.getBigBlind());
        assertEquals(400, toTest.getBigBlind());
        assertNotNull(toTest.getDeck());
        assertNotNull(toTest.getBoard());
        assertNotNull(toTest.getDealer());
        assertNotNull(toTest.getDealerPosition());
        assertNotNull(toTest.getActivePlayers());
        assertNotNull(toTest.getActor());
        assertNotNull(toTest.getActorPosition());
        assertNotNull(toTest.getPots());
        assertNotNull(toTest.getMinBet());
        assertNotNull(toTest.getBet());
        
    }
    /*
    @Test
    public void testRunFold() {
        //doReturn(mockTable).when(toTest).makeTable(null, money, smallBlind);
        toTest.setSleeptime(0);
        
        when(toTest.getActionFromResponse(anyString())).thenReturn(Action.FOLD);
        when(toTest.getBetFromResponse(anyString())).thenReturn(200);
        toTest.run();
        
        assertNotNull(toTest.getPlayersList());
        assertNotNull(toTest.getSmallBlind());
        assertEquals(200, toTest.getSmallBlind());
        assertNotNull(toTest.getBigBlind());
        assertEquals(400, toTest.getBigBlind());
        assertNotNull(toTest.getDeck());
        assertNotNull(toTest.getBoard());
        assertNotNull(toTest.getDealer());
        assertNotNull(toTest.getDealerPosition());
        assertNotNull(toTest.getActivePlayers());
        assertNotNull(toTest.getActor());
        assertNotNull(toTest.getActorPosition());
        assertNotNull(toTest.getPots());
        assertNotNull(toTest.getMinBet());
        assertNotNull(toTest.getBet());
    }  
    */
    
    @Test
    public void testRunAllIn() {
        //doReturn(mockTable).when(toTest).makeTable(null, money, smallBlind);
        toTest.setSleeptime(0);
        
        when(toTest.getActionFromResponse(anyString())).thenReturn(Action.ALL_IN);
        when(toTest.getBetFromResponse(anyString())).thenReturn(200);
        toTest.run();
        
        assertNotNull(toTest.getPlayersList());
        assertNotNull(toTest.getSmallBlind());
        assertEquals(200, toTest.getSmallBlind());
        assertNotNull(toTest.getBigBlind());
        assertEquals(400, toTest.getBigBlind());
        assertNotNull(toTest.getDeck());
        assertNotNull(toTest.getBoard());
        assertNotNull(toTest.getDealer());
        assertNotNull(toTest.getDealerPosition());
        assertNotNull(toTest.getActivePlayers());
        assertNotNull(toTest.getActor());
        assertNotNull(toTest.getActorPosition());
        assertNotNull(toTest.getPots());
        assertNotNull(toTest.getMinBet());
        assertNotNull(toTest.getBet());
        
    }
    
    @Test
    public void testGetLegalActions() {
        table = new Table(playersSocketsNames, money, smallBlind);
        player.setBet(0);
        String actions = table.getLegalActions(player);
        assertEquals(" btnCheck btnBet btnAllIn btnFold", actions);
        player.setBet(100);
        table.bet = 200;
        player.setMoney(100);
        actions = table.getLegalActions(player);
        assertEquals(" btnCall btnAllIn btnFold", actions);
        player.setMoney(1000);
        actions = table.getLegalActions(player);
        assertEquals(" btnCall btnRaise btnAllIn btnFold", actions);
        player.setBet(220);
        actions = table.getLegalActions(player);
        assertEquals(" btnCheck btnRaise btnAllIn btnFold", actions);
        player.allIn();
        actions = table.getLegalActions(player);
        assertEquals(" btnCheck", actions);
    }
    
    @Test
    public void testGetTotalPot() {
        table = new Table(playersSocketsNames, money, smallBlind);
        
        assertEquals(0, table.getTotalPot());
    }
    
    @Test
    public void testNextDealer() {
        table = new Table(playersSocketsNames, money, smallBlind);
        table.getActivePlayers().add(player);
        table.getActivePlayers().add(player2);
        table.dealer = player;
        table.nextDealer();
        assertEquals(player2, table.getDealer());
    }
    
    @Test
    public void testNextActor() {
        table = new Table(playersSocketsNames, money, smallBlind);
        table.getActivePlayers().add(player);
        table.getActivePlayers().add(player2);
        table.nextActor(player2);
        assertEquals(player, table.getActor());
        table.nextActor(player);
        assertEquals(player2, table.getActor());
    }
    
    @Test
    public void testContributePot() {
        table = new Table(playersSocketsNames, money, smallBlind);
        table.pots.add(new Pot(10));
        table.contributePot(0);
        assertEquals(0, table.getTotalPot());
        table.contributePot(50);
        assertEquals(50, table.getTotalPot());
    }
    
    @Test
    public void testDealCommunityCards() {
        table = new Table(playersSocketsNames, money, smallBlind);
        table.setSleeptime(0);
        table.deck = new Deck();
        table.dealCommunityCards(2);
        List<Card> board = table.getBoard();
        assertEquals(2, board.size());
    }
    
    @Test
    public void testInformPlayer() {
        table = new Table(playersSocketsNames, money, smallBlind);
        table.prepareClients();
        assertNull(table.informPlayer("foobar", player, false));
    }
    
    @Test
    public void testPlayRound() {
        table = new Table(playersSocketsNames, money, smallBlind);
        table.setSleeptime(0);
        table.getActivePlayers().add(player);
        table.playRound();
        assertNotNull(table.getBoard());
    }
    
    @Test
    public void testGetActionFromResponse() {
        table = new Table(playersSocketsNames, money, smallBlind);
        Action action = table.getActionFromResponse("CHECK 0");
        assertEquals(Action.CHECK, action);
        action = table.getActionFromResponse("BET 0");
        assertEquals(Action.BET, action);
        action = table.getActionFromResponse("CALL 0");
        assertEquals(Action.CALL, action);
        action = table.getActionFromResponse("RAISE 0");
        assertEquals(Action.RAISE, action);
        action = table.getActionFromResponse("ALL_IN 0");
        assertEquals(Action.ALL_IN, action);
        action = table.getActionFromResponse("FOLD 0");
        assertEquals(Action.FOLD, action);
    }
    
    @Test
    public void testGetBetFromResponse() {
        table = new Table(playersSocketsNames, money, smallBlind);
        int bet = table.getBetFromResponse("Bet 200");
        assertEquals(200, bet);
    }
}
