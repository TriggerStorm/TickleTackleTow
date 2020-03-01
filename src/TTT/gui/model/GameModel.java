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
import java.util.List;



/**
 *
 * @author Trigger
 */
public class GameModel {
        private static GameModel instance; //????
        private int posx,posy;
        private GameState gameState;
        private GameManager GM;
     private GameModel()
    {
        
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
    
    public boolean PlayerMove(int xPosition, int yPosition)
    {
       return GM.makeMove(new Move(xPosition, yPosition));
    }
    
    public List<IMove> getAvailableMoves()
    {
        return gameState.getField().getAvailableMoves();
    }
            
    
 
}
