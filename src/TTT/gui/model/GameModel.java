/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.gui.model;



/**
 *
 * @author Trigger
 */
public class GameModel {
        private static GameModel instance;
        private int posx,posy;
     
    
     private GameModel()
    {
        
    }

     public static GameModel getInstance()
    {
        if(instance == null)
        {
            instance = new GameModel();
        }
        return instance;
    }
 public void setLastMove(int x, int y)
 {
     this.posx = x;
     this.posy = y;
 }
 public int getLastMoveX()
 {
    
     return posx;
 }
 public int getLastMoveY()
 {
     return posy;
 }
 
}
