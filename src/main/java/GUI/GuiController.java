package GUI;

import Board.Board;
import Cards.Deck.Deck;
import Dice.Dice;
import Logger.DataLogger;
import Logger.TurnCounter;
import Logger.TurnLogger;
import Players.AllPlayers;
import Players.OrderStartingPlayers;
import Players.Player;
import Rules.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Vector;

/**
 * Created by userhp on 28/03/2016.
 */
public class GuiController {


    public TextField SimulationsToRun;
    @FXML
    private CardController CardInformationController;
    @FXML
    private BoardController BoardController;
    @FXML
    private SpaceInformationController PropertyInformationController;
    @FXML
    private BottomBarController BarController;

    public void initialize() {
        BoardController.accessToControllers(CardInformationController, PropertyInformationController);
        BarController.accessToBoardController(BoardController);
    }


}


