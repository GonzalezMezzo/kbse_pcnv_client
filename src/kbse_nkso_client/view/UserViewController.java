/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import kbse_nkso_client.Main;
import kbse_nkso_client.controller.ModelController;

/**
 * FXML Controller class
 *
 * @author Philipp
 */
public class UserViewController implements Initializable {
    
    ModelController ctrl = Main.getModelController();
    
    @FXML TextField inputTextUser;
    @FXML TextField inputTextFName;
    @FXML TextField inputTextLName;
    @FXML TextField inputTextEMail;
    
    @FXML
    private void changeUser() throws IOException{
        ctrl.setInputTextUser(inputTextUser.getText());
        ctrl.setInputTextFName(inputTextFName.getText());
        ctrl.setInputTextLName(inputTextLName.getText());
        ctrl.setInputTextEMail(inputTextEMail.getText());
        ctrl.changeUser();
        Main.showPostView();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
