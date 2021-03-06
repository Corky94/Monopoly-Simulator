import Board.*;
import Cards.Deck.Deck;
import Dice.Dice;
import Players.AllPlayers;
import Players.OrderStartingPlayers;
import Players.Player;
import Rules.*;
import Logger.*;

import java.util.logging.*;
import java.util.*;
import java.nio.file.Paths;
import java.util.ConcurrentModificationException;
import java.util.Vector;

/**
 * Created by marc on 23/11/2015.
 */

public class Main {

    public static void main(String args[]) {
        try {
            LogManager.getLogManager().reset();
//            FileHandler fh = new FileHandler(Paths.get("").toAbsolutePath().toString() + "/logs/debugLog.log");
//            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).addHandler(fh);
//            SimpleFormatter formatter = new SimpleFormatter();
//            fh.setFormatter(formatter);
//            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setUseParentHandlers(false);
            //Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.WARNING);

        } catch (Exception e) {
            e.printStackTrace();
        }
        int endlessGames = 0;
        int simulationsToRun = 1000;
        TurnLogger tl = new TurnLogger(Paths.get("").toAbsolutePath().toString() + "/logs/turnLogPlayers8.csv");
        int[] winners = {0, 0, 0, 0, 0, 0, 0, 0};
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


            //init BoardGui
            Board.getInstance().populateBoard("Monopoly Map.csv");

            //Init Deck
            Deck.getInstance().initializeDeck("ExampleOfCards.csv");

            //Init Players
            Dice dice1 = new Dice();
            Dice dice2 = new Dice();
            Dice[] diceForGame = {dice1, dice2};
            Player player1 = new Player("Player 1", 1500, diceForGame);
            Player player2 = new Player("Player 2", 1500, diceForGame);
            Player player3 = new Player("Player 3", 1500, diceForGame);
            Player player4 = new Player("Player 4", 1500, diceForGame);
            Player player5 = new Player("Player 5", 1500, diceForGame);
            Player player6 = new Player("Player 6", 1500, diceForGame);
            Player player7 = new Player("Player 7", 1500, diceForGame);
            Player player8 = new Player("Player 8", 1500, diceForGame);
            Vector<Player> playersInGame = new Vector<Player>();
            playersInGame.add(player1);
            playersInGame.add(player2);
            playersInGame.add(player3);
            playersInGame.add(player4);
            playersInGame.add(player5);
            playersInGame.add(player6);
            playersInGame.add(player7);
            playersInGame.add(player8);
            Collections.sort(playersInGame, new OrderStartingPlayers());
            AllPlayers.init(playersInGame);
            TurnCounter.resetCounter();
            Vector<Player> allPlayers;
            Long StartingTime = System.nanoTime();
            while (AllPlayers.getInstance().getAllPlayers().size() > 1 && TurnCounter.getTurn() < 500) {
                allPlayers = AllPlayers.getInstance().getAllPlayers();
                try {
                    for (Player player : AllPlayers.getInstance().getAllPlayers()) {
                        player.onTurn();
                        for (Player p : AllPlayers.getInstance().getAllPlayers()) {
                            p.betweenTurns();
                        }

                        
                    }
                } catch (ConcurrentModificationException e) {

                }
                //endOfTurnLog(allPlayers);
                TurnCounter.newTurn();
            }
            if (TurnCounter.getTurn() > 499) {
                endlessGames++;
                System.out.println("Game ended at 3000 turns");
                Player player = AllPlayers.getInstance().getAllPlayers().firstElement();
                for (Player p : AllPlayers.getInstance().getAllPlayers()) {
                    if (p.getMoney() > player.getMoney()) {
                        player = p;
                    }
                }
                System.out.println("Winner is " + player.getName());
                if (player.getName().equalsIgnoreCase("Player 1")) {
                    winners[0]++;
                } else if (player.getName().equalsIgnoreCase("Player 2")) {
                    winners[1]++;
                } else if (player.getName().equalsIgnoreCase("Player 3")) {
                    winners[2]++;
                } else if (player.getName().equalsIgnoreCase("Player 4")) {
                    winners[3]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 5")) {
                    winners[4]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 6")) {
                    winners[5]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 7")) {
                    winners[6]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 8")) {
                    winners[7]++;
                }
            } else {
                Player player = AllPlayers.getInstance().getAllPlayers().firstElement();
                System.out.println("Winner is " + player.getName());
                if (player.getName().equalsIgnoreCase("Player 1")) {
                    winners[0]++;
                } else if (player.getName().equalsIgnoreCase("Player 2")) {
                    winners[1]++;
                } else if (player.getName().equalsIgnoreCase("Player 3")) {
                    winners[2]++;
                } else if (player.getName().equalsIgnoreCase("Player 4")) {
                    winners[3]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 5")) {
                    winners[4]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 6")) {
                    winners[5]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 7")) {
                    winners[6]++;
                
                } else if (player.getName().equalsIgnoreCase("Player 8")) {
                    winners[7]++;
                }
            }
            System.out.println("Game Finished in " + (System.nanoTime() - StartingTime) / 1000000000.0 + "s");
            System.out.println("Turns taken = " + TurnCounter.getTurn());
            DataLogger.closeFiles();
            TurnLogger.writeToLog(TurnCounter.getTurn());
            TurnCounter.resetCounter();
            //Run Simulation

        }
        TurnLogger.closeFiles();
        System.out.println("Endless games = " + endlessGames + " out of games played = " + simulationsToRun);
        System.out.println("Player 1 won : " + winners[0] + " games");
        System.out.println("Player 2 won : " + winners[1] + " games");
        System.out.println("Player 3 won : " + winners[2] + " games");
        System.out.println("Player 4 won : " + winners[3] + " games");
        System.out.println("Player 5 won : " + winners[4] + " games");
        System.out.println("Player 6 won : " + winners[5] + " games");
        System.out.println("Player 7 won : " + winners[6] + " games");
        System.out.println("Player 8 won : " + winners[7] + " games");
    }

    private static void endOfTurnLog(Vector<Player> players) {
        Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        for (Player player : players) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(player.getName());
            stringBuilder.append(" Current cash = ");
            stringBuilder.append(player.getMoney());
            stringBuilder.append("\nOwned spaces:\n");
            List<Ownable> properties = (List) player.getOwnedSpaces();
            Collections.sort(properties, new OwnableComparator());

            for (Ownable owned : properties) {
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
