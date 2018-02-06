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
    
    ModelController modelctrl = Main.getModelController();
    
    @FXML TextField submitUser;
    
    @FXML
    private void changeUser() throws IOException{
        modelctrl.setInputTextUser(submitUser.getText());
        Main.showPostView();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
