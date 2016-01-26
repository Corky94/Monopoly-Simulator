package Cards.Deck;

import Cards.Card;
import Cards.ChanceCard;
import Cards.CommunityChestCard;

import java.util.*;

/**
 * Created by userhp on 26/01/2016.
 */
public class Deck {
    private LinkedList<CommunityChestCard> CommunityChestDeck;
    private LinkedList<ChanceCard> ChanceDeck;


    public Deck(String csvFile){
        initilaizeDeck(csvFile);
        shuffleDecks();
    }
    public Deck(){
        CommunityChestDeck = new LinkedList<CommunityChestCard>();
        ChanceDeck = new LinkedList<ChanceCard>();
    }

    public void shuffleDecks() {
        Collections.shuffle(CommunityChestDeck);
        Collections.shuffle(ChanceDeck);
    }

    private void initilaizeDeck(String csvFile){

    }
    public ChanceCard drawChanceCard(){
        ChanceCard card = null;
        try{
            card = ChanceDeck.pop();
        }
        catch(NoSuchElementException e){
            System.out.println("No Chance Cards in Deck");
        }
        return card;
    }
    public void addChanceCard(ChanceCard chanceCard){
        ChanceDeck.addLast(chanceCard);
    }
    public CommunityChestCard drawCommunityChestCard(){
        CommunityChestCard card = null;
        try{
            card = CommunityChestDeck.pop();
        }
        catch(NoSuchElementException e){
            System.out.println("No Community Chest cards in Deck");
        }
        return card;
    }
    public void addCommunityChestCard(CommunityChestCard communityChestCard){
        CommunityChestDeck.addLast(communityChestCard);
    }


}
