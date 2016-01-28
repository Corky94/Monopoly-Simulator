package Cards;

import Board.Board;
import Board.Space;
import Cards.Deck.Deck;
import Players.Player;
import junit.framework.TestCase;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

/**
 * Created by userhp on 27/01/2016.
 */
public class CardTest extends TestCase {

    private Deck deck = Deck.getInstance();
    public void setUp(){
       deck.initializeBlankDeck();
    }

    public void testOnDrawAdvanceToLocation() throws Exception {
        Player player = mock(Player.class);
        Space space = mock(Space.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.AdvanceToLocation,space );
       
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(space);
    }
    public void testOnDrawCollectMoneyFromBank() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.CollectMoneyFromBank,10 );
       
        card.onDraw(player);
        verify(player,atLeastOnce()).receiveMoney(10);
    }
    public void testOnDrawGetOutOfJail() throws Exception {
        Player player = mock(Player.class);
       
        Space space = mock(Space.class);
        CommunityChestCard card = new CommunityChestCard("Test 1",CardAction.GetOutOfJail );
        
        card.onDraw(player);
        verify(player,atLeastOnce()).keepCard(card);
    }
    public void testOnDrawPayBank() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.PayBank,10 );
        
        card.onDraw(player);
        verify(player,atLeastOnce()).giveMoneyToBank(10);
    }
    public void testOnDrawCollectFromPlayers() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.CollectFromPlayers,10 );
        
        card.onDraw(player);
        verify(player,atLeastOnce()).receiveMoneyFromPlayers(10);
    }
    public void testOnDrawPayBankDependingOnHouseAndHotels() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.PayBankDependingOnHousesAndHotelsOwned,10,20 );
        
        card.onDraw(player);
        verify(player,atLeastOnce()).giveMoneyToBank(anyInt());
    }

    //Need to find a way to fully mock this test
    public void testOnDrawGoBackSpaces() throws Exception {
        Board board = Board.getInstance();
        board.populateBoard("Monopoly Map.csv");
        Player player = mock(Player.class);
        Space space = board.getSpaceOnBoard(1);
        Space expectedSpace = board.getSpaceOnBoard(39);

        when(player.getCurrentLocation()).thenReturn(space);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.GoBackSpaces,3);
       
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(expectedSpace);
    }
    public void testOnDrawAdvanceToNearestUtility() throws Exception {
        Board board = Board.getInstance();
        board.populateBoard("Monopoly Map.csv");
        Player player = mock(Player.class);
        Space space = board.getSpaceOnBoard(10);
        when(player.getCurrentLocation()).thenReturn(space);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.AdvanceToNearestUtility);
       
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(any(Space.class));
    }
    public void testOnDrawAdvanceToNearestStation() throws Exception {
        Board board = Board.getInstance();
        board.populateBoard("Monopoly Map.csv");
        Player player = mock(Player.class);
        Space space = board.getSpaceOnBoard(10);
        when(player.getCurrentLocation()).thenReturn(space);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.AdvanceToNearestStation);
       
        card.onDraw(player);
        verify(player,atLeastOnce()).moveToLocation(any(Space.class));
    }
    public void testOnDrawPayOtherPlayers() throws Exception {
        Player player = mock(Player.class);
        CommunityChestCard card = new CommunityChestCard("Test 1", CardAction.PayPlayers,10 );
       
        card.onDraw(player);
        verify(player,atLeastOnce()).payOtherPlayers(10);
    }


}