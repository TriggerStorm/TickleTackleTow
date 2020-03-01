/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.gui.controller;

import TTT.gui.model.GameModel;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import TTT.bll.game.GameManager;
import TTT.bll.move.IMove;

        
        
/**
 * FXML Controller class
 *
 * @author Trigger
 */
public class GameBordController implements Initializable {
    private GameModel gModel;
    private HashMap<Integer, HashMap<Integer, Button>> Btn = new HashMap();
    
    
    @FXML
    private GridPane gridGameboard;
    @FXML
    private Rectangle x1;
    @FXML
    private Rectangle x2;
    @FXML
    private Circle c2;
    @FXML
    private Circle c1;
    @FXML
    private Label lb_score1;
    @FXML
    private Label lb_score2;
    @FXML
    private Label lb_player1;
    @FXML
    private Label lb_player2;
    @FXML
    private Label bn_menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InitialiseBoard();
        
        
    }    

    
    public void InitialiseBoard() {
        for (Node microboard : gridGameboard.getChildren()) {
            Integer microXpos = GridPane.getRowIndex(microboard);
            Integer microYpos = GridPane.getColumnIndex(microboard);

        }
    }
    
    
    @FXML
    private void clickOn(ActionEvent event) {
        
    }
    
    /*private playerMove(ActionEvent event){
    int player = gModel.getCurrentPlayer();
    int[] position = getFieldPosition(event);
    if(gModel.PlayerMove(position[0],position[1]))
        {
            
        }
    }*/
    
    
    private int[] getMicroPosition(ActionEvent event)
    {
        Integer microXPosition = GridPane.getRowIndex(((Node) event.getSource()).getParent());
        Integer microYPosition = GridPane.getColumnIndex(((Node) event.getSource()).getParent());
        microXPosition = (microXPosition == null) ? 0 : microXPosition;
        microYPosition = (microYPosition == null) ? 0 : microYPosition;
        int[] microPosition = {microXPosition, microYPosition};
        return microPosition;
    }
    
    private int[] getMacroPosition(ActionEvent event)
    {
        Integer macroXPosition = GridPane.getRowIndex(((Node) event.getSource()).getParent().getParent().getParent());
        Integer macroYPosition = GridPane.getColumnIndex(((Node) event.getSource()).getParent().getParent().getParent());
        macroXPosition = (macroXPosition == null) ? 0 : macroXPosition;
        macroYPosition = (macroYPosition == null) ? 0 : macroYPosition;
        int[] macroPosition = {macroXPosition, macroYPosition};
        return macroPosition;
    }
    private int[] getFieldPosition(ActionEvent event)
    {
        int[] macroboardPosition = getMicroPosition(event);
        int[] microboardPosition = getMacroPosition(event);
        int fieldXPosition = macroboardPosition[0] * 3 + microboardPosition[0];
        int fieldYPosition = macroboardPosition[1] * 3 + microboardPosition[1];
        int[] fieldPosition = {fieldXPosition, fieldYPosition};
        return fieldPosition;
    }
}
