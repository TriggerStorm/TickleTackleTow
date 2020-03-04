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
   private IGameState currentState;
   private String playerOneIcon = "O";
    private String playerTwoIcon = "X";
   public filipmethod()
   {
       this.currentState = currentState;
   }
   public List<IMove> getMoves()
   {
       return f.getAvailableMoves();
   }
   
   public IMove moveWithWinPriority()
   {
       for(int i=0; i < getMoves().size();i++)
       {
           String [][] boardToCheck = currentState.getField().getBoard();
           boardToCheck[getMoves().get(i).getX()][getMoves().get(i).getY()] = playerOneIcon; // if below will check if with "i" move there is win for player one
           if(checkMicroboardWin(boardToCheck,getMoves().get(i).getX(),getMoves().get(i).getY()) || checkMacroboardWin(boardToCheck))
               return getMoves().get(i);
           
           boardToCheck[getMoves().get(i).getX()][getMoves().get(i).getY()] = playerTwoIcon;// if below will check if with "i" move there is win for player two
           if(checkMicroboardWin(boardToCheck,getMoves().get(i).getX(),getMoves().get(i).getY()) || checkMacroboardWin(boardToCheck))
               return getMoves().get(i);
           
           
       }
       return findMove(currentState);
   }
   private boolean checkMicroboardWin(String[][] board, int posx, int posy) {   
       
       
      if(checkForBoardWin(board, posx, posy))
        return true;
      else return false;
    }
    
    private boolean checkMacroboardWin(String[][] board) {   
       
      if(checkForBoardWin(board,0, 0))
        return true;
      else return false;
    }
   
    private boolean checkForBoardWin (String[][] board,int posx,int posy) 
     {
         for(int x = posx; x < posx+3; x++)
        {
            if(checkForWin(board, x, posy))
            {
                return true;
            }
            for(int y = posy; y < posy+3; y++)
            {
                
                if(checkForWin(board, posx, y))
                {
                    return true;
                }
            }
        }
        if(checkForWin(board,posx,posy)) 
            return true;
        else return false;
     }
   
   private boolean checkForWin (String[][] board,int posx,int posy) 
    {   
      if ((board[posx][posy].equals(playerOneIcon) || board[posx][posy].equals(playerTwoIcon))
                    && board[posx][posy].equals(board[posx][posy+1]) 
                    && board[posx][posy+1].equals(board[posx][posy+2]))
        return true;
      else if ((board[posx][posy].equals(playerOneIcon) || board[posx][posy].equals(playerTwoIcon))
                    && board[posx][posy].equals(board[posx+1][posy]) 
                    && board[posx+1][posy].equals(board[posx][posy+2]))
          return true;
      else if ((board[posx][posy].equals(playerOneIcon) || board[posx][posy].equals(playerTwoIcon))
                    && board[posx][posy].equals(board[posx+1][posy+1]) 
                    && board[posx+1][posy+1].equals(board[posx+2][posy+2]))
          return true;
      else if ((board[posx][posy+2].equals(playerOneIcon) || board[posx][posy+2].equals(playerTwoIcon))
                && board[posx][posy+2].equals(board[posx+1][posy+1])
                && board[posx+1][posy+1].equals(board[posx+2][posy]))
          return true;
      else
          return false;
    }
   
   
   
    public IMove findMove(IGameState state) {

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

        //NOTE: Something failed, just take the first available move
        return f.getAvailableMoves().get(0);
    }
    
}
