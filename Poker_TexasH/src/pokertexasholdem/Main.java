package pokertexasholdem;

public class Main {
    
    public static void main(String[] args) {
        
        int playersAmount = Integer.parseInt(args[0]);
        
        Deck deck = new Deck();
        
        System.out.println("----------------------------------New deck----------------------------------");
        
        for( int i=0; i<deck.getDeckList().size(); i++ ) {
            System.out.println( deck.getDeckList().get(i).toString() );
        }
        
        System.out.println("--------------------------------Shuffled deck--------------------------------");
        
        deck.shuffleDeck(42);
        
        for( int i=0; i<deck.getDeckList().size(); i++ ) {
            System.out.println( deck.getDeckList().get(i).toString() );
        }
        
        System.out.println("--------------------------------Draw first card------------------------------");
        System.out.println( deck.drawFromDeck().toString() );

        System.out.println("----------------------------------Deal 2 cards--------------------------------");
        Card[] cardArray = new Card[2];
        cardArray[0] = deck.drawFromDeck();
        cardArray[1] = deck.drawFromDeck();
        
        for( int i=0; i<2; i++ ) {
            System.out.println( "Card" + i + ": " + cardArray[i].toString() );
        }

        System.out.println("---------------------------------Deal to players------------------------------");
        
        Deal deal = new Deal( deck );
        
        deal.dealToPlayers( playersAmount );
        
        Hand playerHand;
        
        for( int i=0; i<playersAmount; i++ ) {
            System.out.println( "Player" + i + ": " );
            playerHand = deal.playersMap.get("Player"+i);
            System.out.println( playerHand.toString() );
        }
        
        System.out.println("----------------------------------Actual deck--------------------------------");
        for( int i=0; i<deck.getDeckList().size(); i++ ) {
            System.out.println( deck.getDeckList().get(i).toString() );
        }
    }
  
}
