package TTT.bll.game;

import TTT.bll.move.IMove;
import TTT.bll.move.Move;
import TTT.bll.bot.IBot;
import TTT.bll.field.Field;
import TTT.bll.field.IField;
import TTT.bll.game.GameState;
import TTT.bll.game.IGameState;
import TTT.gui.model.GameModel;






/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe,
 * the implementation of which is up to whoever uses this interface.
 * Note that initializing a game through the constructors means
 * that you have to create a new instance of the game manager 
 * for every new game of a different type (e.g. Human vs Human, Human vs Bot or Bot vs Bot),
 * which may not be ideal for your solution, so you could consider refactoring
 * that into an (re-)initialize method instead.
 * @author mjl
 */
public class GameManager {
    
    /**
     * Three different game modes.
     */
    public enum GameMode{
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }
    
    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;
    private String playerOneIcon = "O";
    private String playerTwoIcon = "X";
    private String drawIcon = "-";

    private GameModel gm = GameModel.getInstance();
    

    
 //   private Imove lastMove;
    
    
    
    /**
     * Set's the currentState so the game can begin.
     * Game expected to be played Human vs Human
     * @param currentState Current game state, usually an empty board, 
     * but could load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
        
       
    }
    
    /**
     * Set's the currentState so the game can begin.
     * Game expected to be played Human vs Bot
     * @param currentState Current game state, usually an empty board, 
     * but could load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }
    
    /**
     * Set's the currentState so the game can begin.
     * Game expected to be played Bot vs Bot
     * @param currentState Current game state, usually an empty board, 
     * but could load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }
    
    /**
     * User input driven Update
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(IMove move)
    {
        //Verify the new move
        if(!verifyMoveLegality(move)) 
        { 
            return false; 
        }
        
        //Update the currentState
        updateBoard(move);
        updateMacroboard(move);
        
        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;
        
        return true;
    }
    
    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame()
    {
        //Check game mode is set to one of the bot modes.
        assert(mode != GameMode.HumanVsHuman);
        
        //Check if player is bot, if so, get bot input and update the state based on that.
        if(mode == GameMode.HumanVsBot && currentPlayer == 1)
        {
            //Check bot is not equal to null, and throw an exception if it is.
            assert(bot != null);
            
            IMove botMove = bot.doMove(currentState);
            
            //Be aware that your bots might perform illegal moves.
            return updateGame(botMove);
        }
        
        //Check bot is not equal to null, and throw an exception if it is.
        assert(bot != null);
        assert(bot2 != null);
        
        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private Boolean verifyMoveLegality(IMove move)
    {
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot.
        System.out.println("Checking move validity against macroboard available field");
        System.out.println("Not currently checking move validity actual board");
        return currentState.getField().isInActiveMicroboard(move.getX(), move.getY());
    }
    
    private void updateBoard(IMove move) {  //Alan's method
        String[][] updatedboard = currentState.getField().getBoard();
        updatedboard[move.getX()][move.getY()] = getPlayerIcon();
        currentState.getField().setBoard(updatedboard);
    }
    
    private void updateMacroboard(IMove move) {   //Alan's method
        String[][] macroboard = currentState.getField().getMacroboard();
        int macroX = (move.getX()/3);
        int macroY = (move.getY()/3);
        if(isValidMove(move)) {
            updateBoard(move);  // maybe not here
            checkMicroboardWin(move.getX(), move.getY());
        }
        
        
    }
    
    private boolean isValidMove (IMove move) {
                if(currentState.getField().isInActiveMicroboard(move.getX(), move.getY())) {
                
     }
         return false;
    }
    
    
    private void setActiveMicroBoard(int lastX, int lastY) {  //Alan's Method
        int nextBoardX = 2 - (lastX%3);
        int nextBoardY = 2 - (lastY%3);
        String[][] updatedMacroboard = currentState.getField().getMacroboard();
        if(updatedMacroboard[nextBoardX][nextBoardX] != IField.AVAILABLE_FIELD) {
            unSetActiveMicroboard();
        } else {
            for(int x = 0; x < updatedMacroboard.length; x++) {
                for(int y = 0; y < updatedMacroboard.length; x++) {
                    if((updatedMacroboard[x][y] == IField.AVAILABLE_FIELD) &&  (!(x == nextBoardX && y == nextBoardY))) {
                            updatedMacroboard[x][y] = IField.UNAVAILABLE_FIELD;
                    }
                }
            }
        }
    }
    
    
    private void unSetActiveMicroboard() {
        String[][] updatedMacroboard = currentState.getField().getMacroboard();
        for(int x = 0; x < updatedMacroboard.length; x++) {
            for(int y = 0; y < updatedMacroboard.length; x++) {
                if(updatedMacroboard[x][y] == IField.UNAVAILABLE_FIELD) {
                    updatedMacroboard[x][y] = IField.AVAILABLE_FIELD;
                } 
            }
        }
    }

    
        
    
    
    private String getPlayerIcon() {
        String playerIcon;
        if (currentPlayer == 0) {
            playerIcon = playerOneIcon;
        }
        else {
            playerIcon = playerTwoIcon;
        }
        return playerIcon;
    }
    
    
/*    private boolean checkMicroboardWin(IMove move) {   //Alan's method
        int startX = (move.getX()/3)*3;
        int startY = (move.getY()/3)*3;
        String [][] boardToCheck = currentState.getField().getBoard();
        return checkForBoardWin(boardToCheck, startX, startY);    
    }
 */   

    private boolean checkMicroboardWin( int posx, int posy) {   
       
        String [][] boardToCheck = currentState.getField().getBoard();
      if(checkForBoardWin(boardToCheck, posx, posy))
        return true;
      else return false;
    }
    
    private boolean checkMacroboardWin() {   
       
        String [][] boardToCheck = currentState.getField().getBoard();
      if(checkForBoardWin(boardToCheck,0, 0))
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
    
    
}
