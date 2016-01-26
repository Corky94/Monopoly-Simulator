package Cards.Deck;

import Cards.CardAction;
import Cards.ChanceCard;
import Cards.CommunityChestCard;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by userhp on 26/01/2016.
 */
public class DeckTest extends TestCase {

    public void testInitialiseEmptyDeck(){
        Deck deck = new Deck();
        assertTrue(deck.drawChanceCard() == null);
        assertTrue(deck.drawCommunityChestCard() == null);
    }
    public void testAddingChanceCardToDeck(){
        Deck deck = new Deck();
        ChanceCard card = new ChanceCard("Test 1", CardAction.GoBackSpaces);
        deck.addChanceCard(card);
        assertEquals(card,deck.drawChanceCard());
    }
    public void testAddingSeveralChanceCardToDeck(){
        Deck deck = new Deck();
        ChanceCard card = new ChanceCard("Test 1", CardAction.GoBackSpaces);
        ChanceCard card1 = new ChanceCard("Test 2", CardAction.GoBackSpaces);
        ChanceCard card2 = new ChanceCard("Test 3", CardAction.GoBackSpaces);
        ChanceCard card3 = new ChanceCard("Test 4", CardAction.GoBackSpaces);
        ChanceCard card4 = new ChanceCard("Test 5", CardAction.GoBackSpaces);
        deck.addChanceCard(card);
        deck.addChanceCard(card1);
        deck.addChanceCard(card2);
        deck.addChanceCard(card3);
        deck.addChanceCard(card4);

        assertEquals(card,deck.drawChanceCard());
        assertEquals(card1,deck.drawChanceCard());
        assertEquals(card2,deck.drawChanceCard());
        assertEquals(card3,deck.drawChanceCard());
        assertEquals(card4,deck.drawChanceCard());

    }
    public void testAddingCommunityChestCardsToDeck(){
        Deck deck = new Deck();
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.GoBackSpaces);
        deck.addCommunityChestCard(card);
        assertEquals(card,deck.drawCommunityChestCard());
    }
    public void testAddingSeveralCommunityChestCardsToDeck(){
        Deck deck = new Deck();
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.GoBackSpaces);
        CommunityChestCard card1 = new CommunityChestCard("Test 2", CardAction.GoBackSpaces);
        CommunityChestCard card2 = new CommunityChestCard("Test 3", CardAction.GoBackSpaces);
        CommunityChestCard card3 = new CommunityChestCard("Test 4", CardAction.GoBackSpaces);
        CommunityChestCard card4 = new CommunityChestCard("Test 5", CardAction.GoBackSpaces);
        deck.addCommunityChestCard(card);
        deck.addCommunityChestCard(card1);
        deck.addCommunityChestCard(card2);
        deck.addCommunityChestCard(card3);
        deck.addCommunityChestCard(card4);

        assertEquals(card,deck.drawCommunityChestCard());
        assertEquals(card1,deck.drawCommunityChestCard());
        assertEquals(card2,deck.drawCommunityChestCard());
        assertEquals(card3,deck.drawCommunityChestCard());
        assertEquals(card4,deck.drawCommunityChestCard());

    }
    public void testShuffleFunction(){
        Deck deck = new Deck();
        ChanceCard card = new ChanceCard("Test 1", CardAction.GoBackSpaces);
        ChanceCard card1 = new ChanceCard("Test 2", CardAction.GoBackSpaces);
        ChanceCard card2 = new ChanceCard("Test 3", CardAction.GoBackSpaces);
        ChanceCard card3 = new ChanceCard("Test 4", CardAction.GoBackSpaces);
        ChanceCard card4 = new ChanceCard("Test 5", CardAction.GoBackSpaces);
        CommunityChestCard card5 = new CommunityChestCard("Test 1", CardAction.GoBackSpaces);
        CommunityChestCard card6 = new CommunityChestCard("Test 2", CardAction.GoBackSpaces);
        CommunityChestCard card7 = new CommunityChestCard("Test 3", CardAction.GoBackSpaces);
        CommunityChestCard card8 = new CommunityChestCard("Test 4", CardAction.GoBackSpaces);
        CommunityChestCard card9 = new CommunityChestCard("Test 5", CardAction.GoBackSpaces);
        deck.addChanceCard(card);
        deck.addChanceCard(card1);
        deck.addChanceCard(card2);
        deck.addChanceCard(card3);
        deck.addChanceCard(card4);
        deck.addCommunityChestCard(card5);
        deck.addCommunityChestCard(card6);
        deck.addCommunityChestCard(card7);
        deck.addCommunityChestCard(card8);
        deck.addCommunityChestCard(card9);
        deck.shuffleDecks();
        


    }

}