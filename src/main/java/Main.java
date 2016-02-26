import Board.Board;
import Cards.Deck.Deck;
import Dice.Dice;
import Players.AllPlayers;
import Players.Player;
import Rules.*;

import java.nio.file.Paths;
import java.util.ConcurrentModificationException;
import java.util.Vector;

/**
 * Created by marc on 23/11/2015.
 */
public class Main {

    public static void main(String args[]){


        //Init Rules
        AuctionRules auctionRules = new AuctionRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/AuctionRules.lua");
        AllRules.setAuctionRules(auctionRules);
        BankruptcyRules bankruptcyRules = new BankruptcyRules();
        AllRules.setBankruptcyRules(bankruptcyRules);
        BuildRules buildRules = new BuildRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/BuildRules.lua");
        AllRules.setBuildRules(buildRules);
        GoRules goRules = new GoRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/GoRules.lua");
        AllRules.setGoRules(goRules);
        JailRules jailRules = new JailRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/JailRules.lua");
        AllRules.setJailRules(jailRules);
        SellingRules sellingRules = new SellingRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/SellingRules.lua");
        AllRules.setSellingRules(sellingRules);
        StationRules stationRules = new StationRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/StationRules.lua");
        AllRules.setStationRules(stationRules);
        TaxRules taxRules = new TaxRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/TaxRules.lua");
        AllRules.setTaxRules(taxRules);
        UtilityRules utilityRules = new UtilityRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/UtilityRules.lua");
        AllRules.setUtilityRules(utilityRules);
        Bank bank = new Bank(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/Bank.lua");
        AllRules.setBankRules(bank);


        //init Board
        Board.getInstance().populateBoard("Monopoly Map.csv");

        //Init Deck
        Deck.getInstance().initializeDeck("ExampleOfCards.csv");
        System.out.println(Deck.getInstance().drawChanceCard().getAction().toString());

        //Init Players
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        Dice[] diceForGame = {dice1, dice2};
        Player player1 = new Player(1500, diceForGame);
        Player player2 = new Player(1500, diceForGame);
        Player player3 = new Player(200, diceForGame);
        Player player4 = new Player(200, diceForGame);
        Vector<Player> playersInGame = new Vector<Player>();
        playersInGame.add(player1);
        playersInGame.add(player2);
//        playersInGame.add(player3);
//        playersInGame.add(player4);
        AllPlayers.init(playersInGame);
        int turn = 1;
        Vector<Player> allPlayers;
        while (AllPlayers.getInstance().getAllPlayers().size() > 1) {
            System.out.print(".");
            allPlayers = AllPlayers.getInstance().getAllPlayers();
            try {
                for (Player player : allPlayers) {
                    allPlayers = AllPlayers.getInstance().getAllPlayers();
                    for (Player p : allPlayers) {
                        p.betweenTurns();
                    }

                    player.onTurn();
                }
            } catch (ConcurrentModificationException e) {

            }

            turn++;
        }
        System.out.println("Game Finished");

        //Run Simulation

    }
}
