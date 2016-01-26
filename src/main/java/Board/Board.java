package Board;

import java.io.*;

/**
 * Contains the layout of all the spaces within the board.
 *
 * Created by marc on 27/12/2015.
 */
public class Board {

    private Space[] spaces;
    public Board(String fileName){
        populateBoard(fileName);
    }


    protected void populateBoard(String fileName) {
        File in = new File(fileName);
        try {
            FileReader fr;
            BufferedReader br;
            fr = new FileReader(in);
            br = new BufferedReader(fr);
            int size = 0;

            while(!(br.readLine()== null))size++;
            fr = new FileReader(in);
            br = new BufferedReader(fr);
            spaces = new Space[size-1];

            br.readLine();
            String line =br.readLine();
            while(!(line== null)){

                String [] splittedstring = line.split(",");
                int loc = Integer.parseInt(splittedstring[0]);
                Space space = generateSpace(splittedstring, loc);

                spaces[loc-1]=space;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Space generateSpace(String[] splittedstring, int loc) {
        String name = splittedstring[1];
        Group group = getGroup(splittedstring[2]);

        int cost = Integer.parseInt(splittedstring[3]);
        //Todo Need to ensure mortgage is incorporated
        int mtg = Integer.parseInt(splittedstring[4]);
        int houseCost = Integer.parseInt(splittedstring[5]);
        int baseRent = Integer.parseInt(splittedstring[6]);
        int oneHouse = Integer.parseInt(splittedstring[7]);
        int twoHouse = Integer.parseInt(splittedstring[8]);
        int threeHouse = Integer.parseInt(splittedstring[9]);
        int fourHouse = Integer.parseInt(splittedstring[10]);
        int hotelRent = Integer.parseInt(splittedstring[11]);

        Space space = null;
        switch(group) {

            case GO:
                space = new GO(name,loc,group);
                break;

            case Jail:
                space = new Jail(name,loc,group);
                break;

            case Tax:
                space = new Tax(name, loc,cost);
                break;

            case Chance:
               space = new Chance(name,loc,group);
                break;


            case CommunityChest:
               space = new CommunityChest(name,loc,group);
                break;


            case Station:
                space = new Station(name,loc,group,cost);
                break;

            case Utility:
                space = new Utilities(name,loc,group,cost);
                break;

            case GoToJail:
                space = new GoToJail(name,loc,group);
                break;


            case FreeParking:
                space = new FreeParking(name,loc,group);
                break;

            default:
                space = new Property(name,group,loc,baseRent,cost,houseCost,oneHouse,twoHouse,threeHouse,fourHouse,hotelRent);
                break;

        }
        return space;
    }

    private Group getGroup(String anObject) {
        Group group = null;
        for(Group g : Group.values()){
            if(g.toString().equals(anObject)){
                group = g;
            }

        }
        return group;
    }
    public Space[] getAllSpaces(){
        return spaces;
    }
}
