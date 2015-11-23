import Board.Group;

import java.io.*;

/**
 * Created by marc on 23/11/2015.
 */
public class Main {

    public static void main(String args[]){
        File in = new File("Monopoly Map.csv");

        try {
            FileReader fr;
            BufferedReader br;
            fr = new FileReader(in);
            br = new BufferedReader(fr);
            String line = br.readLine();

            //Ignore the first line which is just the column names;
            line = br.readLine();
            while(!(line== null)){

                System.out.println(line);
                String [] splittedstring = line.split(",");

                
                int loc = Integer.parseInt(splittedstring[0]);
                String name = splittedstring[1];
                Group group;
                for(Group g : Group.values()){
                    if(g.toString().equals(splittedstring[2])){
                        group = g;
                        break;
                    }
                }
                int cost = Integer.parseInt(splittedstring[3]);
                int mtg = Integer.parseInt(splittedstring[4]);
                int houseCost = Integer.parseInt(splittedstring[5]);
                int baseRent = Integer.parseInt(splittedstring[6]);
                int oneHouse = Integer.parseInt(splittedstring[7]);
                int twoHouse = Integer.parseInt(splittedstring[8]);
                int threeHouse = Integer.parseInt(splittedstring[9]);
                int fourHouse = Integer.parseInt(splittedstring[10]);
                int hotelRent = Integer.parseInt(splittedstring[11]);



                    line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
