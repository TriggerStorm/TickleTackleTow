/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.bll.bot;

import TTT.bll.field.Field;
import TTT.bll.field.IField;
import TTT.bll.game.IGameState;
import TTT.bll.move.IMove;
import TTT.bll.move.Move;
import java.util.List;

/**
 *
 * @author macos
 */
public class filipmethod {
    private int[][] preferredMoves = {
            {1, 1}, 
            {0, 0}, {2, 2}, {0, 2}, {2, 0}, 
            {0, 1}, {2, 1}, {1, 0}, {1, 2}};
   private Field f;
   public List<IMove> getMoves()
   {
       return f.getAvailableMoves();
   }
    public IMove doMove(IGameState state) {

        //Find macroboard to play in
        for (int[] move : preferredMoves)
        {
            if(state.getField().getMacroboard()[move[0]][move[1]].equals(IField.AVAILABLE_FIELD))
            {
                //find move to play
                for (int[] selectedMove : preferredMoves)
                {
                    int x = move[0]*3 + selectedMove[0];
                    int y = move[1]*3 + selectedMove[1];
                    if(state.getField().getBoard()[x][y].equals(IField.EMPTY_FIELD))
                    {
                        return new Move(x,y);
                    }
                }
            }
        }

        //NOTE: Something failed, just take the first available move I guess!
        return f.getAvailableMoves().get(0);
    }
    
}
