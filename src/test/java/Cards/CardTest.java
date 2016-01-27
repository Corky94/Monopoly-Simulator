package Cards;

import Board.Space;
import Cards.Deck.Deck;
import Players.Player;
import junit.framework.TestCase;

import static org.mockito.Mockito.*;

/**
 * Created by userhp on 27/01/2016.
 */
public class CardTest extends TestCase {

    public void testOnDrawAdvanceToLocation() throws Exception {
        Player player = mock(Player.class);
        Space space = mock(Space.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.AdvanceToLocation,space );
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(space);
    }
    public void testOnDrawCollectMoneyFromBank() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.CollectMoneyFromBank,10 );
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).receiveMoney(10);
    }
    public void testOnDrawGetOutOfJail() throws Exception {
        Player player = mock(Player.class);
        Deck deck = mock(Deck.class);
        Space space = mock(Space.class);
        CommunityChestCard card = new CommunityChestCard("Test 1",CardAction.GetOutOfJail );
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).keepCard(card);
    }
    public void testOnDrawPayBank() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.PayBank,10 );
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).giveMoneyToBank(10);
    }
    public void testOnDrawCollectFromPlayers() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.CollectFromPlayers,10 );
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).receiveMoneyFromPlayers(10);
    }
    public void testOnDrawPayBankDependingOnHouseAndHotels() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.PayBankDependingOnHousesAndHotelsOwned,10,20 );
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).giveMoneyToBank(anyInt());
    }
    public void testOnDrawGoBackSpaces() throws Exception {
        Player player = mock(Player.class);
        Space space = mock(Space.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.GoBackSpaces);
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(any(Space.class));
    }
    public void testOnDrawAdvanceToNearestUtility() throws Exception {
        Player player = mock(Player.class);
        Space space = mock(Space.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.AdvanceToNearestUtility);
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(any(Space.class));
    }
    public void testOnDrawAdvanceToNearestStation() throws Exception {
        Player player = mock(Player.class);
        Space space = mock(Space.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.AdvanceToNearestStation);
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(any(Space.class));
    }
    public void testOnDrawPayOtherPlayers() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.PayPlayers,10 );
        Deck.initilaizeBlankDeck();
        card.onDraw(player);
        verify(player,atLeastOnce()).payOtherPlayers(10);
    }


}