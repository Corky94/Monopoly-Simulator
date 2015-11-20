package Board;

/**
 * Created by marc on 20/11/2015.
 */
public class Space {
    private Group group;
    private int location;

    public Group getGroup(){
        return group;
    }
    protected void setGroup(Group newGroup){
        group = newGroup;
    }
    public int getLocation(){
        return location;
    }
    protected void setLocation(int loc){
        location=loc;
    }
}
