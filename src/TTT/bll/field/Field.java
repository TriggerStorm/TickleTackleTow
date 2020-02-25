/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.bll.field;

import TTT.bll.move.IMove;
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

    
    @Override
    public void clearBoard() {
        
        for (int microX = 0; microX < microboard.length; microX ++) { 
            for (int microY = 0; microY < microboard.length; microY ++) { 
                microboard [microX][microY] = EMPTY_FIELD;
//System.out.println(microX + " " + microY + "=" + EMPTY_FIELD);
            }
        }
        
        for (int macroX = 0; macroX < macroboard.length; macroX ++) { 
            for (int macroY = 0; macroY < macroboard.length; macroY ++) { 
                macroboard [macroX][macroY] = AVAILABLE_FIELD;
//System.out.println(macroX + " " + macroY + "=" + AVAILABLE_FIELD);
            }
        }
        
    }

    
    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> availableMoves = new ArrayList<>();

        for (int macroX = 0; macroX < macroboard.length; macroX ++) { 
            for (int macroY = 0; macroY < macroboard.length; macroY ++) { 
                if (macroboard [macroX][macroY] == AVAILABLE_FIELD) {
                    for (int microX = (macroX*3); microX < ((microboard.length)-((2-macroX)*3)); microX ++) { 
                        for (int microY = (macroX*3); microY < ((microboard.length)-((2-macroY)*3)); microY ++) { 
                            if(microboard[microX][microY] == EMPTY_FIELD) {
               //                 availableMoves.add([microX][microY])  // Whatever an IMove is.
                            }
                        }        
                    }
                }
                return null;
            }
        }
        return availableMoves;
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
