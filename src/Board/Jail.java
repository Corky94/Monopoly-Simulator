package Board;

import Players.Money;
import Players.Player;

/**
 * Created by marc on 20/11/2015.
 */
public class Jail extends Space {
    private boolean justVisiting = true;

    @Override
    public Money costOfVisiting(Player player) {
        return null;
    }
}
