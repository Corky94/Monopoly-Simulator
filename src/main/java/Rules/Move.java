package Rules;

/**
 * Created by userhp on 01/02/2016.
 */
public class Move {
   private MoveType moveType;
   private int sumOfDiceRolled;

   public MoveType getMoveType() {
      return moveType;
   }

   public void setMoveType(MoveType moveType) {
      this.moveType = moveType;
   }

   public int getSumOfDiceRolled() {
      return sumOfDiceRolled;
   }

   public void setSumOfDiceRolled(int sumOfDiceRolled) {
      this.sumOfDiceRolled = sumOfDiceRolled;
   }
}
