/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kbse_nkso_client.rest.RestFrontendController;
import kbse_nkso_client.controller.ModelController;

/**
 *
 * @author Philipp
 */
public class Main extends Application {
    
    private static ModelController modelctrl;
    private static RestFrontendController restctrl;
    private Stage primaryStage;
    private static BorderPane mainLayout;
    
    /**
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        restctrl = new RestFrontendController();
        modelctrl = new ModelController();
        
        this.primaryStage = stage;
        this.primaryStage.setTitle("KBSE_Nienhueser_Koschmann_Schaefer_Oldemeier");
        showMainView();
        showUserView();
    }
    
    /**
     * 
     * @throws IOException 
     */
    private void showMainView() throws IOException {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(Main.class.getResource("view/MainView.fxml"));
       mainLayout = loader.load();
       
       Scene scene = new Scene(mainLayout);
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
    /**
     * 
     * @throws IOException 
     */
    public static void showUserView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/UserView.fxml"));
        BorderPane userView = loader.load();
        mainLayout.setCenter(userView);
    }
    
    /**
     * 
     * @throws IOException 
     */
    public static void showPostView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/PostView.fxml"));
        BorderPane postView = loader.load();
        mainLayout.setCenter(postView);
    }

    public static ModelController getModelController() {
        return modelctrl;
    }

    public static RestFrontendController getRestctrl() {
        return restctrl;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 

}