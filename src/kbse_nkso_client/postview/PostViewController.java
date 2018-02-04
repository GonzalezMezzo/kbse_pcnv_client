/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.postview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import kbse_nkso_client.Main;

/**
 * FXML Controller class
 *
 * @author Philipp
 */
public class PostViewController implements Initializable {

    @FXML
    private void goToUserView() throws IOException{
        Main.showUserView();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
