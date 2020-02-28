/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FieldTest;

import TTT.bll.field.Field;
/**
 *
 * @author admin
 */
public class FieldTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Field field = new Field();
        Boolean valid;
        String[][] board = new String[9][9];
            field.clearBoard();
            field.getAvailableMoves();
            board = field.getBoard();
            field.getMacroboard();
          
            valid = field.isInActiveMicroboard(1, 1);
System.out.println("Valid at 4,4 = " + valid); 
int test = 23/4;
        System.out.println(test);
            System.out.println("finish");

        }
    
}
