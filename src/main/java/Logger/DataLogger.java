package Logger;

import Board.Ownable;
import Board.Space;
import Players.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by userhp on 07/03/2016.
 */
public class DataLogger {
    private static File csvFile;

    public DataLogger(String filename) {
        csvFile = new File(filename);

    }

    public static void writeToLog(Player player, Space location) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile, true));
            writer.append(player.getName() + "," + player.getMoney() + "," + location.getName() + ",");
            for (Ownable ownable : player.getOwnedSpaces()) {
                writer.append(ownable.getName() + ",");
            }
            writer.append("\n");
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
