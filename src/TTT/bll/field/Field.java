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
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board.length; x++) {
                if(!board[x][y].equals(EMPTY_FIELD)) {
                    return false;
                }
            }
        }
        return true;
    }

    
    @Override
    public boolean isFull() {
        List<IMove> availableMoves = getAvailableMoves();
        if(availableMoves.size() > 0) {
            return false;
        }
        return true;    
    }

    
    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        int macroX = (x/3);
        int macroY = (y/3);
        return macroboard[macroX][macroY].equals(AVAILABLE_FIELD);
    }
    
   
    @Override
    public String[][] getBoard() {
        return board;
    }
    
    
    @Override
    public String[][] getMacroboard() {

System.out.println("");
System.out.println("MACROBOARD");

        String[][] macroboard = new String [3][3];
        for(int y = 0; y < this.macroboard.length; y++) { 
System.out.println("");

            for(int x = 0; x < this.macroboard.length; x++) { 
            macroboard[x][y] = getPlayerId(x, y);
            String value = macroboard[x][y];
 System.out.print(value + " ");
            }
        }

        return macroboard;
    }

    
    @Override
    public void setBoard(String[][] board) {
        this.board = board;
    }

    
    @Override
    public void setMacroboard(String[][] macroboard) {
        this.macroboard = macroboard;
    }

     
    
    
    
    
    
    
    
    
    public void printMicroboard() {
         System.out.println("");
        System.out.println("MICROBOARD");
        String [][] testBoard = board;
        for(int y = 0; y < testBoard.length; y++) { 
            for(int x = 0; x < testBoard.length; x++) { 
                testBoard[x][y] = getPlayerId(x, y);
                String value = testBoard[x][y];
                System.out.print(value + " ");
            }
            System.out.println("");
        }
    }
    
     
    public void printMacroboard() {
        System.out.println("");
        System.out.println("MACROBOARD");
        String [][] testBoard = macroboard;
        for(int y = 0; y < testBoard.length; y++) { 
            for(int x = 0; x < testBoard.length; x++) { 
                testBoard[x][y] = getPlayerId(x, y);
                String value = testBoard[x][y];
                System.out.print(value + " ");
            }
            System.out.println("");
        }
    }
    
    
    
}
