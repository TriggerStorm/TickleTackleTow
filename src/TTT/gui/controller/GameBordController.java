/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        // TODO
    }    

    @FXML
    private void clickOn(ActionEvent event) {
    }
    
}
