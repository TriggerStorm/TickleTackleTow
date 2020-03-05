package TTT.bll.bot;

import dk.easv.bll.field.IField;
import dk.easv.bll.game.GameState;
import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import dk.easv.bll.move.Move;
import java.util.List;


public class workingwithteachersversionbot implements IBot {

    private static final String BOTNAME = "test";
      private IGameState state;
    
    protected int[][] preferredMoves = {
            {1, 1},
            {0, 0}, {2, 2}, {0, 2}, {2, 0},  
            {0, 1}, {2, 1}, {1, 0}, {1, 2}}; 

    
    public IMove doMove(IGameState state) {
         
            
           String[][] boardToCheck = state.getField().getBoard();
         
        List<IMove> validMoves = state.getField().getAvailableMoves();
        for(int i = 0 ; i < validMoves.size(); i++)
        {
            //String[][] boardToCheck = state.getField().getBoard();
            boardToCheck[validMoves.get(i).getX()][validMoves.get(i).getY()] = ""+1;
            if(isWin(boardToCheck,validMoves.get(i),""+1))
            { 
              
                return new Move(validMoves.get(i).getX(),validMoves.get(i).getY());
            }
           // String[][] boardToCheckk = state.getField().getBoard();
            boardToCheck[validMoves.get(i).getX()][validMoves.get(i).getY()] = ""+0;
            if(isWin(boardToCheck,validMoves.get(i),""+0))
            {
                
                return new Move(validMoves.get(i).getX(),validMoves.get(i).getY());
            }
          return findMove(state);
        }
        return null;
            
    
    
    }
    
//    public void loadBoard(IGameState state)
//        {
//            this.state = new GameState();
//            // copy board
//            String[][] board = new String[9][9];
//            for(int i = 0; i < board.length; i++)
//            {
//                for(int j = 0; j < board[i].length; j++)
//                {
//                    board[i][j] = state.getField().getBoard()[i][j];
//                }
//            }
//            this.state.getField().setBoard(board);
//        }
//    
    
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

        //NOTE: Something failed, just take the first available move I guess!
        return state.getField().getAvailableMoves().get(0);
    }

    @Override
    public String getBotName() {
        return BOTNAME;
    }
    public static boolean isWin(String[][] board, IMove move, String currentPlayer){
        int localX = move.getX() % 3;
        int localY = move.getY() % 3;
        int startX = move.getX() - (localX);
        int startY = move.getY() - (localY);

        //check col
        for (int i = startY; i < startY + 3; i++) {
            if (!board[move.getX()][i].equals(currentPlayer))
                break;
            if (i == startY + 3 - 1) return true;
        }

        //check row
        for (int i = startX; i < startX + 3; i++) {
            if (!board[i][move.getY()].equals(currentPlayer))
                break;
            if (i == startX + 3 - 1) return true;
        }

        //check diagonal
        if (localX == localY) {
            //we're on a diagonal
            int y = startY;
            for (int i = startX; i < startX + 3; i++) {
                if (!board[i][y++].equals(currentPlayer))
                    break;
                if (i == startX + 3 - 1) return true;
            }
        }

        //check anti diagonal
        if (localX + localY == 3 - 1) {
            int less = 0;
            for (int i = startX; i < startX + 3; i++) {
                if (!board[i][(startY + 2)-less++].equals(currentPlayer))
                    break;
                if (i == startX + 3 - 1) return true;
            }
        }
        return false;
    }
//    private boolean isWinOnBoard(String[][] board, int startingX, int startingY)
//    {
//        for(int x = startingX; x < startingX+3; x++)
//        {
//            if(isHorizontalWin(board, x, startingY))
//            {
//                return true;
//            }
//            for(int y = startingY; y < startingY+3; y++)
//            {
//                
//                if(isVerticalWin(board, startingX, y))
//                {
//                    return true;
//                }
//            }
//        }
//        return isDiagonalWin(board, startingX, startingY);
//    }
//    
//    private boolean isHorizontalWin(String[][] board, int startingX, int startingY)
//    {
//        return ((board[startingX][startingY].equals("0") || board[startingX][startingY].equals("1"))
//                    && board[startingX][startingY].equals(board[startingX][startingY+1]) 
//                    && board[startingX][startingY+1].equals(board[startingX][startingY+2]));
//    }
//    
//    private boolean isVerticalWin(String[][] board, int startingX, int startingY)
//    {
//        return ((board[startingX][startingY].equals("0") || board[startingX][startingY].equals("1"))
//                    && board[startingX][startingY].equals(board[startingX+1][startingY]) 
//                    && board[startingX+1][startingY].equals(board[startingX+2][startingY]));
//    }
//    
//    private boolean isDiagonalWin(String[][] board, int startingX, int startingY)
//    {
//        if((board[startingX][startingY].equals("0") || board[startingX][startingY].equals("1"))
//                && board[startingX][startingY].equals(board[startingX+1][startingY+1])
//                && board[startingX+1][startingY+1].equals(board[startingX+2][startingY+2]))
//        {
//            return true;
//        }
//        else if((board[startingX][startingY+2].equals("0") || board[startingX][startingY+2].equals("1"))
//                && board[startingX][startingY+2].equals(board[startingX+1][startingY+1])
//                && board[startingX+1][startingY+1].equals(board[startingX+2][startingY]))
//        {
//            return true;
//        }
//        return false;
//    }
//    
}
