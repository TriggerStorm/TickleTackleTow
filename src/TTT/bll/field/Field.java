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
    String [][] board = new String [9][9];
    String [][] macroboard = new String [3][3];
    IMove imove;

    
    @Override
    public void clearBoard() {
        for (int microX = 0; microX < board.length; microX ++) { 
            for (int microY = 0; microY < board.length; microY ++) { 
                board [microX][microY] = EMPTY_FIELD;
            }
        }
        for (int macroX = 0; macroX < macroboard.length; macroX ++) { 
            for (int macroY = 0; macroY < macroboard.length; macroY ++) { 
                macroboard [macroX][macroY] = AVAILABLE_FIELD;
            }
        }
 //       board[5][5] = "X";
 //       board[3][7] = "J";
 //       board[0][4] = "0";
 //      board[4][4] = "4";

    }

    
    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> availableIMoves = new ArrayList<>();
          
        for (int macroY = 0; macroY < macroboard.length; macroY ++) { 
            for (int macroX = 0; macroX < macroboard.length; macroX ++) { 
System.out.println("");
        System.out.println("MacroBoard: X = " + macroX + " Y = " + macroY);        
              
                if ((macroboard [macroX][macroY]).equals(AVAILABLE_FIELD)) {
                    for (int microY = (macroY*3); microY < ((board.length)-((2-macroY)*3)); microY ++) { 
                        for (int microX = (macroX*3); microX < ((board.length)-((2-macroX)*3)); microX ++) { 
                            if((board[microX][microY]).equals(EMPTY_FIELD)) {
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
        String playerId;
    playerId = board[column][row];
    return playerId;
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
        List<IMove> availableMoves = getAvailableMoves();
        for (int j = 0; j < availableMoves.size(); j++) {
            IMove imove = availableMoves.get(j);
            if((imove.getX() == x) && (imove.getY() == y)) {
                return true;
            }
        }
        return false;
    }

    
    @Override
    public String[][] getBoard() {
        return board;
    }
    
    
    @Override
    public String[][] getMacroboard() {
        return macroboard;
    }

    
    @Override
    public void setBoard(String[][] board) {
        this.board = this.board;
    }

    
    @Override
    public void setMacroboard(String[][] macroboard) {
        this.macroboard = macroboard;
    }
    
    
    
}
