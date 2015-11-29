package pokertexasholdem;

import java.util.Map;
import java.util.HashMap;

public class Deal {
    
    Map<String, Hand> playersMap;
    Deck d;
    
    public Deal( Deck d ) {
        this.d = d;
        playersMap = new HashMap<String, Hand>();
    }
    
    public void dealToPlayers( int playersAmount ) {
    	for( int i=0; i<playersAmount; i++ ) {
    		Card[] cards = new Card[2];
    		cards[0] = d.drawFromDeck();
    		cards[1] = d.drawFromDeck();
    		Hand hand = new Hand( cards );
    		playersMap.put( ("Player" + i), hand );
    	}
    }
}
