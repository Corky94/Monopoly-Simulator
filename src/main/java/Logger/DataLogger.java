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
    private static BufferedWriter writer;

    public DataLogger(String filename) {
        csvFile = new File(filename);
        try{
            writer = new BufferedWriter(new FileWriter(csvFile, true));
        }
        catch (IOException e) {
                e.printStackTrace();

        }

    }

    public static void writeToLog(Player player, Space location) {
        if(csvFile != null){
            
            try {
                
                writer.append(player.getName() + "," + player.getMoney() + "," + location.getName() + ",");
                for (Ownable ownable : player.getOwnedSpaces()) {
                    writer.append(ownable.getName() + ",");
                }
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
	public static void closeFiles(){
try {

		writer.close();
} catch (IOException e) {
                e.printStackTrace();

            }

	}
}
