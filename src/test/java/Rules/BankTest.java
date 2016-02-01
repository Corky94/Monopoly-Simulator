package Rules;

import Board.Property;
import Players.Player;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.mockito.*;
import static org.mockito.Mockito.*;
/**
 * Created by userhp on 29/01/2016.
 */
public class BankTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testAuctionProperty() throws Exception {
        Property property = Mockito.mock(Property.class);
        when(property.getCost()).thenReturn(500);
        AuctionRules rules = Mockito.mock(AuctionRules.class);
        when(rules.getStartingPriceMultiplier()).thenReturn(0.1);
        when(rules.getStartingPriceMultiplier()).thenReturn(0.05);
        Bank.initializeBank(null,null,rules,null,null,1,0);
        Bank bank = Bank.getInstance();
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        Player player3 = Mockito.mock(Player.class);
        Player player4 = Mockito.mock(Player.class);

        when(player1.wantsToBuyPropertyForPrice(Matchers.eq(property),anyInt())).thenReturn(true).thenReturn(true).thenReturn(false);
        when(player2.wantsToBuyPropertyForPrice(Matchers.eq(property),anyInt())).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(player3.wantsToBuyPropertyForPrice(Matchers.eq(property),anyInt())).thenReturn(true).thenReturn(false);
        when(player4.wantsToBuyPropertyForPrice(Matchers.eq(property),anyInt())).thenReturn(true);

        Player[] players = {player1,player2,player3,player4};
        bank.auctionProperty(property,players);

        verify(player4,times(1)).addProperty(property);
        verify(property,times(1)).setOwner(player4);
        verify(player1,never()).addProperty(property);
        verify(player2,never()).addProperty(property);
        verify(player3,never()).addProperty(property);
    }


    public void testBuyingHouse() throws Exception {
        Property property = Mockito.mock(Property.class);
        when(property.getHouseCost()).thenReturn(50);
        BuildRules rules = Mockito.mock(BuildRules.class);
        Player player = Mockito.mock(Player.class);
        when(property.getOwner()).thenReturn(player);
        when(player.spendMoney(50)).thenReturn(true);
        when(rules.canBuildHouse(property,player)).thenReturn(true);
        Bank.initializeBank(null,rules,null,null,null,1,0);
        Bank bank = Bank.getInstance();
        assertTrue(bank.buyHouse(property,player));
        verify(player, times(1)).spendMoney(50);
        verify(property, times(1)).addHouse();

    }
    public void testBuyingAHouseWithNoHousesInBank() throws Exception {
        Property property = Mockito.mock(Property.class);
        when(property.getHouseCost()).thenReturn(50);
        BuildRules rules = Mockito.mock(BuildRules.class);
        Player player = Mockito.mock(Player.class);
        when(property.getOwner()).thenReturn(player);
        when(player.spendMoney(50)).thenReturn(true);
        when(rules.canBuildHouse(property,player)).thenReturn(true);
        Bank.initializeBank(null,rules,null,null,null,0,0);
        Bank bank = Bank.getInstance();
        assertFalse(bank.buyHouse(property,player));
        verify(player, never()).spendMoney(50);
        verify(property, never()).addHouse();

    }
    public void testBuyingAHotel() throws Exception {
        Property property = Mockito.mock(Property.class);
        when(property.getHouseCost()).thenReturn(50);
        BuildRules rules = Mockito.mock(BuildRules.class);
        Player player = Mockito.mock(Player.class);
        when(property.getOwner()).thenReturn(player);
        when(player.spendMoney(50)).thenReturn(true);
        when(rules.canBuildHotel(property,player)).thenReturn(true);
        when(rules.amountOfHousesNeededForHotel()).thenReturn(4);
        Bank.initializeBank(null,rules,null,null,null,0,1);
        Bank bank = Bank.getInstance();
        assertTrue(bank.buyHotel(property,player));
        verify(player, times(1)).spendMoney(50);
        verify(property, times(1)).addHotel();
        assertEquals(4,bank.getHousesInBank());
    }
    public void testBuyingAHotelWhenNoHotelsInBank() throws Exception {
        Property property = Mockito.mock(Property.class);
        when(property.getHouseCost()).thenReturn(50);
        BuildRules rules = Mockito.mock(BuildRules.class);
        Player player = Mockito.mock(Player.class);
        when(property.getOwner()).thenReturn(player);
        when(player.spendMoney(50)).thenReturn(true);
        when(rules.canBuildHotel(property,player)).thenReturn(true);
        when(rules.amountOfHousesNeededForHotel()).thenReturn(4);
        Bank.initializeBank(null,rules,null,null,null,0,0);
        Bank bank = Bank.getInstance();
        assertFalse(bank.buyHotel(property,player));
        verify(player, never()).spendMoney(50);
        verify(property, never()).addHotel();
        assertEquals(bank.getHousesInBank(),0);
    }

    public void testSellingAHouse() throws Exception {
        Property property = Mockito.mock(Property.class);
        when(property.getHouseCost()).thenReturn(50);
        BuildRules rules = Mockito.mock(BuildRules.class);
        SellingRules sellingRules = Mockito.mock(SellingRules.class);
        when(sellingRules.priceReductionForSellingOfHouse()).thenReturn(0.5);
        Player player = Mockito.mock(Player.class);
        when(property.getOwner()).thenReturn(player);
        when(rules.canSellHouse(property,player)).thenReturn(true);
        Bank.initializeBank(null,rules,null,sellingRules,null,0,0);
        Bank bank = Bank.getInstance();
        bank.sellHouse(property,player);
        verify(player, times(1)).receiveMoney(25);
        verify(property, times(1)).removeHouse();
        assertEquals(bank.getHousesInBank(),1);
    }
    public void testSellingAHotel() throws Exception {
        Property property = Mockito.mock(Property.class);
        when(property.getHouseCost()).thenReturn(50);
        BuildRules rules = Mockito.mock(BuildRules.class);
        SellingRules sellingRules = Mockito.mock(SellingRules.class);
        when(sellingRules.priceReductionForSellingOfHotel()).thenReturn(0.1);
        Player player = Mockito.mock(Player.class);
        when(property.getOwner()).thenReturn(player);
        when(rules.canSellHouse(property,player)).thenReturn(true);
        when(rules.amountOfHousesNeededForHotel()).thenReturn(4);
        Bank.initializeBank(null,rules,null,sellingRules,null,4,0);
        Bank bank = Bank.getInstance();
        bank.sellHotel(property,player);
        verify(player, times(1)).receiveMoney(5);
        verify(property, times(1)).removeHotel();
        verify(property, times(4)).addHouse();
        assertEquals(bank.getHotelsInBank(),1);
        assertEquals(bank.getHousesInBank(),0);

    }

}