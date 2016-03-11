import Board.*;
import Cards.Deck.Deck;
import Dice.Dice;
import Players.AllPlayers;
import Players.Player;
import Rules.*;
import Logger.*;

import java.io.IOException;
import java.util.logging.*;

import java.nio.file.Paths;
import java.util.ConcurrentModificationException;
import java.util.Vector;

/**
 * Created by marc on 23/11/2015.
 */
public class Main {

    public static void main(String args[]) {
        try {
            FileHandler fh = new FileHandler(Paths.get("").toAbsolutePath().toString() + "/logs/debugLog.log");
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setUseParentHandlers(false);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.WARNING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int endlessGames = 0;
        int simulationsToRun = 10;
        for (int i = 0; i < simulationsToRun; i++) {
            DataLogger dl = new DataLogger(Paths.get("").toAbsolutePath().toString() + "/logs/dataLog" + i + ".csv");
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
            Player player1 = new Player("Player 1", 1500, diceForGame);
            Player player2 = new Player("Player 2", 1500, diceForGame);
            Player player3 = new Player("Player 3", 1500, diceForGame);
            Player player4 = new Player("Player 4", 1500, diceForGame);
            Vector<Player> playersInGame = new Vector<Player>();
            playersInGame.add(player1);
            playersInGame.add(player2);
            playersInGame.add(player3);
            playersInGame.add(player4);

            AllPlayers.init(playersInGame);
            int turn = 1;
            Vector<Player> allPlayers;
            Long StartingTime = System.nanoTime();
            while (AllPlayers.getInstance().getAllPlayers().size() > 1 && turn < 500) {
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
                endOfTurnLog(allPlayers);
                turn++;
            }
            if (turn > 499) {
                endlessGames++;
                System.out.println("Game ended at 3000 turns");
                Player player = AllPlayers.getInstance().getAllPlayers().firstElement();
                for (Player p : AllPlayers.getInstance().getAllPlayers()) {
                    if (p.getMoney() > player.getMoney()) {
                        player = p;
                    }
                }
                System.out.println("Winner is " + player.getName());
            } else {
                Player player = AllPlayers.getInstance().getAllPlayers().firstElement();
                System.out.println("Winner is " + player.getName());
            }
            System.out.println("Game Finished in " + (System.nanoTime() - StartingTime) / 1000000000.0 + "s");
            System.out.println("Turns taken = " + turn);
            //Run Simulation

        }
        System.out.println("Endless games = " + endlessGames + " out of games played = " + simulationsToRun);
    }

    private static void endOfTurnLog(Vector<Player> players) {
        Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        for (Player player : players) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(player.getName());
            stringBuilder.append(" Current cash = ");
            stringBuilder.append(player.getMoney());
            stringBuilder.append("\nOwned spaces:\n");

            for (Ownable owned : player.getOwnedSpaces()) {
                stringBuilder.append(owned.getName());
                stringBuilder.append(" Group: ");
                stringBuilder.append(owned.getGroup());
                if (owned instanceof Property) {
                    stringBuilder.append(" Houses: ");
                    stringBuilder.append(((Property) owned).getHouses());
                    stringBuilder.append(" Hotels: ");
                    stringBuilder.append(((Property) owned).getHotels());
                }
                stringBuilder.append("\n");
            }
            log.warning(stringBuilder.toString());
        }

    }
}
