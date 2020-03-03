/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.gui.model;

import TTT.bll.bot.IBot;
import TTT.bll.game.GameManager;
import TTT.bll.game.GameState;
import TTT.bll.move.IMove;
import TTT.bll.move.Move;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;



/**
 *
 * @author Trigger
 */
public class GameModel {
        private final List<InvalidationListener> listeners = new ArrayList<>();
        private static GameModel instance; //????
        private int posx,posy;
        private GameState gameState;
        private GameManager GM;
     public GameModel()
    {
        gameState = new GameState();
        GM = new GameManager(gameState);
    }
     
      public static GameModel getInstance() // 
    {
        if(instance == null)
        {
            instance = new GameModel();
        }
        return instance;
    }
      
    public void newPVPGame()
    {
        gameState = new GameState();
        GM = new GameManager(gameState);
    }
    
    public void newPVBGame(IBot bot)
    {
        gameState = new GameState();
        GM = new GameManager(gameState, bot);
    }
    
    public void newBVBGame(IBot bot1,IBot bot2)
    {
        gameState = new GameState();
        GM = new GameManager(gameState, bot1, bot2);
    }
   
    public void setLastMove(int x, int y) // ?
    {
             this.posx = x;
             this.posy = y;
    }
     public int getLastMoveX() // ?
    {
            return posx;
    }
    
     
    public int getLastMoveY() // ?
    {
           return posy;
    }
    public int getCurrentPlayer()
    {
        
        return GM.getCurrentPlayer();
    }
    
    public boolean PlayerMove(IMove move)
    {
       return GM.updateGame2(move);
    }
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }
    private void notifyAllListeners(){
        for (InvalidationListener listener : listeners){
            listener.invalidated((Observable) this);
        }
    }
    public boolean doMove() {
        boolean valid = GM.updateGame();
        if(valid)
            notifyAllListeners();
        return valid;
    }
    
    public boolean doMove(IMove move){
        boolean valid = GM.updateGame(move);
        if(valid)
            notifyAllListeners();
        return valid;
    }
    public List<IMove> getAvailableMoves()
    {
        return gameState.getField().getAvailableMoves();
    }
    
    public String[][] getBoard(){
        return GM.getCurrentState().getField().getBoard();
    }
    
    public String[][] getMacroboard()
    {
        return GM.getCurrentState().getField().getMacroboard();
    }
    public GameManager.GameOverState getGameOverState() {
        return GM.getGameOver();
    }
 
}
