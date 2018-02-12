/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import kbse_nkso_client.Main;
import kbse_nkso_client.controller.ModelController;
import kbse_nkso_client.util.HostServicesControllerFactory;

/**
 * FXML Controller class
 *
 * @author Philipp
 */
public class UserViewController implements Initializable {
    
    ModelController ctrl = ModelController.getInstance();
    
    @FXML TextField inputTextUser;
    @FXML TextField inputTextFName;
    @FXML TextField inputTextLName;
    @FXML TextField inputTextEMail;
    
    private final HostServices hostServices;

    /**
     *
     * @param hostServices
     */
    public UserViewController(HostServices hostServices) {
        this.hostServices = hostServices ;
    }
    
    /**
     * Triggered through the "continue" Button on apllication startup.
     * Sets user variables and calls changeUser() in ModelController.
     * @throws IOException 
     */
    @FXML
    private void changeUser() throws IOException{
        ctrl.setInputTextUser(inputTextUser.getText());
        ctrl.setInputTextFName(inputTextFName.getText());
        ctrl.setInputTextLName(inputTextLName.getText());
        ctrl.setInputTextEMail(inputTextEMail.getText());
        ctrl.changeUser();
        showPostView();
    }
    
    /**
     * Setup FXML and set the "PostView" to be the center of parenting BorderPane in our "MainView"
     * @throws IOException
     */
    public void showPostView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/PostView.fxml"));
        loader.setControllerFactory(new HostServicesControllerFactory(hostServices));
        BorderPane postView = loader.load();
        Main.getMainLayout().setCenter(postView);
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
