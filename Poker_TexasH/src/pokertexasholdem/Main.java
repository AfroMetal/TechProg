package pokertexasholdem;

public class Main {
    
    public static void main(String[] args) {
        
        int playersAmount = Integer.parseInt(args[0]);
        int cardsAmount = Integer.parseInt(args[1]);
        
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

        System.out.println("----------------------------------Deal " + args[1] + " cards--------------------------------");
        Card[] cardArray1 = deck.dealCardsFromDeck(cardsAmount);
        for( int i=0; i<cardsAmount; i++ ) {
            System.out.println( "Card" + i + ": " + cardArray1[i].toString() );
        }

        System.out.println("---------------------------------Deal to players------------------------------");
        
        Deal deal = new Deal( deck );
        
        deal.dealToPlayers( playersAmount, cardsAmount );
        
        Card[] cardArray2;
        
        for( int i=0; i<playersAmount; i++ ) {
            cardArray2 = deal.playersMap.get("Player"+i).getHand();
            System.out.println( "Player" + i + ": " );
            for( int j=0; j<cardsAmount; j++ ) {
                System.out.println( "   " + cardArray2[j].toString() );
            }
        }
        
        System.out.println("----------------------------------Actual deck--------------------------------");
        for( int i=0; i<deck.getDeckList().size(); i++ ) {
            System.out.println( deck.getDeckList().get(i).toString() );
        }
    }
  
}
