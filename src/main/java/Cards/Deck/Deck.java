package Cards.Deck;

import Board.Chance;
import Cards.Card;
import Cards.ChanceCard;
import Cards.CommunityChestCard;

import java.util.*;

/**
 * Created by userhp on 26/01/2016.
 */
public  class Deck {
    private static LinkedList<CommunityChestCard> CommunityChestDeck;
    private static LinkedList<ChanceCard> ChanceDeck;




    public static void shuffleDecks() {
        Collections.shuffle(CommunityChestDeck);
        Collections.shuffle(ChanceDeck);
    }

    public static void initilaizeDeck(String csvFile){

    }
    public static void initilaizeBlankDeck(){
        CommunityChestDeck = new LinkedList<CommunityChestCard>();
        ChanceDeck = new LinkedList<ChanceCard>();
    }
    public static ChanceCard drawChanceCard(){
        ChanceCard card = null;
        try{
            card = ChanceDeck.pop();
        }
        catch(NoSuchElementException e){
            System.out.println("No Chance Cards in Deck");
        }
        return card;
    }
    public static void addCard(Card card){
        if (card instanceof ChanceCard){
            ChanceDeck.addLast((ChanceCard) card);
        }
        else{
            CommunityChestDeck.addLast((CommunityChestCard) card);
        }
    }
    public static void addChanceCard(ChanceCard chanceCard){
        ChanceDeck.addLast(chanceCard);
    }
    public static CommunityChestCard drawCommunityChestCard(){
        CommunityChestCard card = null;
        try{
            card = CommunityChestDeck.pop();
        }
        catch(NoSuchElementException e){
            System.out.println("No Community Chest cards in Deck");
        }
        return card;
    }
    public static void addCommunityChestCard(CommunityChestCard communityChestCard){
        CommunityChestDeck.addLast(communityChestCard);
    }


}
