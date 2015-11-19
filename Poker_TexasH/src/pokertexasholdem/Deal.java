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
    
    public void dealToPlayers( int playersAmount, int cardsAmount ) {
        for( int i=0; i<playersAmount; i++ ) {
            Hand hand = new Hand( cardsAmount, d );
            playersMap.put( ("Player" + i), hand );
        }
    }
    
}
