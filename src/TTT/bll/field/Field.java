/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.bll.field;

import TTT.bll.move.IMove;
import TTT.bll.move.Move;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author admin
 */
public class Field implements IField {
    String [][] microboard = new String [9][9];
    String [][] macroboard = new String [3][3];
    IMove imove;

    @Override
    public void clearBoard() {
        for (int microX = 0; microX < microboard.length; microX ++) { 
            for (int microY = 0; microY < microboard.length; microY ++) { 
                microboard [microX][microY] = EMPTY_FIELD;
            }
        }
        for (int macroX = 0; macroX < macroboard.length; macroX ++) { 
            for (int macroY = 0; macroY < macroboard.length; macroY ++) { 
                macroboard [macroX][macroY] = AVAILABLE_FIELD;
            }
        }
    }

    
    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> availableIMoves = new ArrayList<>();
          
        for (int macroY = 0; macroY < macroboard.length; macroY ++) { 
            for (int macroX = 0; macroX < macroboard.length; macroX ++) { 
System.out.println("Board: X = " + macroX + " Y = " + macroY);        
              
                if ((macroboard [macroX][macroY]).equals(AVAILABLE_FIELD)) {
                    for (int microY = (macroY*3); microY < ((microboard.length)-((2-macroY)*3)); microY ++) { 
                        for (int microX = (macroX*3); microX < ((microboard.length)-((2-macroX)*3)); microX ++) { 
                            if((microboard[microX][microY]).equals(EMPTY_FIELD)) {
                            Move move = new Move(microX,microY);
                            availableIMoves.add(move);
System.out.println("AvailableImoves: X = " + move.getX() + " Y = " + move.getY());        
                            
                            }
                        }        
                    }
                }
            }
        }
System.out.println(" TotalAvailableImoves = " + availableIMoves.size());        
        return availableIMoves;

    }
            
            
            
            
    
    @Override
    public String getPlayerId(int column, int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getMacroboard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBoard(String[][] board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMacroboard(String[][] macroboard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
