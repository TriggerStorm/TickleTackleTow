/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.gui.controller;

import TTT.bll.field.IField;
import TTT.bll.move.IMove;
import TTT.gui.model.GameModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Trigger
 */
public class GameBordController implements Initializable {
    private GameModel gModel;
    private HashMap<Integer, HashMap<Integer, Button>> Btn = new HashMap();
    private final JFXButton[][] jfxButtons = new JFXButton[9][9];
    
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
    @FXML
    private JFXButton JFX_bn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       gModel = new GameModel();
    }    

    @FXML
    private void clickOn(ActionEvent event) {
        gModel.getCurrentPlayer();
        doMove((IMove) JFX_bn.getUserData());
        //playerIcon(gModel.getCurrentPlayer());
        
    }
    
    private boolean doMove(IMove move) {
        int currentPlayer = gModel.getCurrentPlayer();
        boolean validMove = gModel.PlayerMove(move); 
        checkAndLockIfGameEnd(currentPlayer);
        return validMove;
    }
    private void checkAndLockIfGameEnd(int currentPlayer) {
       /* if (gModel.getGameOverState() != GameManager.GameOverState.Active) {
            String[][] macroboard = model.getMacroboard();
            // Lock game
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (macroboard[i][k].equals(IField.AVAILABLE_FIELD)) {
                        macroboard[i][k] = IField.EMPTY_FIELD;
                    }
                }
            }
            if (model.getGameOverState().equals(GameManager.GameOverState.Tie)) {
                Platform.runLater(() -> showWinnerPane("TIE"));
            }
            else {
                Platform.runLater(() -> showWinnerPane(currentPlayer + ""));
            }
        }*/
    }
  
    private List<Node> playerIcon(int currentPlayer){
        List<Node> cross = new ArrayList();
        cross.add(x1);
        cross.add(x2);
        
        List<Node> circle = new ArrayList();
        circle.add(c1);
        circle.add(c2);
        
        if(currentPlayer == 0){
            return cross;
        }
        else if(currentPlayer == 1){
            return circle;
        }
        return cross;
    }
}