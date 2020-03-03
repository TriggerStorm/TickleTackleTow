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
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import TTT.bll.game.GameManager;
import TTT.bll.move.IMove;
import TTT.bll.move.Move;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

        
        
/**
 * FXML Controller class
 *
 * @author Trigger
 */
public class GameBordController implements Initializable {
    GameModel gModel;
    private final JFXButton[][] JFX_bn = new JFXButton[9][9];
    private final GridPane[][] gridMicros = new GridPane[3][3];
    
    String p0 = null;
    String p1 = null;
    
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
    private GridPane Gp_macro;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Gp_macro.toFront(); // Or the buttons will not work
        createMicroGridPanes();
       gModel = new GameModel();
       if (gModel != null) {
            gModel.removeListener(observable -> update());
        }

        gModel.addListener(observable -> update());
        
    }    

    
    private boolean doMove(IMove move) {
        int currentPlayer = gModel.getCurrentPlayer();
        boolean validMove = gModel.doMove(move); 
        checkAndLockIfGameEnd(currentPlayer);
        return validMove;
    }
    private void checkAndLockIfGameEnd(int currentPlayer) {
       if (gModel.getGameOverState() != GameManager.GameOverState.Active) {
            String[][] macroboard = gModel.getMacroboard();
            // Lock game
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (macroboard[i][k].equals(IField.AVAILABLE_FIELD)) {
                        macroboard[i][k] = IField.EMPTY_FIELD;
                    }
                }
            }
            if (gModel.getGameOverState().equals(GameManager.GameOverState.Tie)) {
                //Platform.runLater(() -> showWinnerPane("TIE"));
            }
            else {
               // Platform.runLater(() -> showWinnerPane(currentPlayer + ""));
            }
        }
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
    
    public void update() {
        //updateConsole();
        Platform.runLater(() -> updateGUI());
    }
    
    private void updateGUI() throws RuntimeException {
        String[][] board = gModel.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (board[i][k].equals(IField.EMPTY_FIELD)) {
                    JFX_bn[i][k].getStyleClass().add("empty");
                }
                else {
                    JFX_bn[i][k].getStyleClass().add("player" + board[i][k]);
                    JFX_bn[i][k].setGraphic(x1);
                }

            }
        }
        String[][] macroBoard = gModel.getMacroboard();
        for (int i = 0; i < macroBoard.length; i++) {
            for (int k = 0; k < macroBoard[i].length; k++) {
                if (gridMicros[i][k] != null) {
                    // Highlight available plays
                    if (macroBoard[i][k].equals(IField.AVAILABLE_FIELD)) {
                        gridMicros[i][k].getStyleClass().add("highlight");
                    }
                    else {
                        gridMicros[i][k].getStyleClass().removeAll("highlight");
                    }

                    // If there is a win
                    if (!macroBoard[i][k].equals(IField.AVAILABLE_FIELD)
                            && !macroBoard[i][k].equals(IField.EMPTY_FIELD)
                            && gridMicros[i][k] != null) {
                      //  setMacroWinner(i, k);
                    }
                }
            }
        }

    }
     private void setMacroWinner(int x, int y) {
        String[][] macroBoard = gModel.getMacroboard();
        Gp_macro.getChildren().remove(gridMicros[x][y]);
        Label lbl = new Label("");
        lbl.setGraphic(x1);
        lbl.getStyleClass().add("winner-label");
        lbl.getStyleClass().add("player" + macroBoard[x][y]);
        lbl.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridMicros[x][y] = null;
        Gp_macro.add(lbl, x, y);
    }
    
    private void createMicroGridPanes() {
        for (int i = 0; i < 3; i++) {
            Gp_macro.addRow(i);
            for (int k = 0; k < 3; k++) {
                GridPane gp = new GridPane();
                for (int m = 0; m < 3; m++) {
                    gp.addColumn(m);
                    gp.addRow(m);
                }
                gridMicros[i][k] = gp;
                for (int j = 0; j < 3; j++) {
                    ColumnConstraints cc = new ColumnConstraints();
                    cc.setPercentWidth(33);
                    cc.setHgrow(Priority.ALWAYS); // allow column to grow
                    cc.setFillWidth(true); // ask nodes to fill space for column
                    gp.getColumnConstraints().add(cc);

                    RowConstraints rc = new RowConstraints();
                    rc.setVgrow(Priority.ALWAYS); // allow row to grow
                    rc.setFillHeight(true);
                    rc.setPercentHeight(33);
                    gp.getRowConstraints().add(rc);
                }

                gp.setGridLinesVisible(true);
                Gp_macro.addColumn(k);
                Gp_macro.add(gp, i, k);
            }
        }
        insertButtonsIntoGridPanes();
    }

    private void insertButtonsIntoGridPanes() {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                GridPane gp = gridMicros[i][k];
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        JFXButton btn = new JFXButton("");
                        btn.setButtonType(JFXButton.ButtonType.FLAT);
                        btn.getStyleClass().add("buttonSMALL");
                        btn.setUserData(new Move(x + i * 3, y + k * 3));
                        btn.setFocusTraversable(false);
                        btn.setOnMouseClicked(
                                event -> {
                                    doMove((IMove) btn.getUserData()); // Player move

                                    boolean isHumanVsBot = p0 != null ^ p1 != null;
                                    if (gModel.getGameOverState() == GameManager.GameOverState.Active && isHumanVsBot) {
                                        int currentPlayer = gModel.getCurrentPlayer();
                                        Boolean valid = gModel.doMove();
                                        checkAndLockIfGameEnd(currentPlayer);
                                    }
                                }
                        );
                        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        gp.add(btn, x, y);
                        JFX_bn[x + i * 3][y + k * 3] = btn;
                    }
                }
            }
        }
    }
}