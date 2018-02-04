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

/**
 *
 * @author Philipp
 */
public class Main extends Application {
    
    private Stage primaryStage;
    private static BorderPane mainLayout;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("KBSE_Nienhueser_Koschmann_Schaefer_Oldemeier");
        showMainView();
        showUserView();
        
        
    }
    
    private void showMainView() throws IOException {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(Main.class.getResource("view/MainView.fxml"));
       mainLayout = loader.load();
       
       Scene scene = new Scene(mainLayout);
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
    public static void showUserView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("userview/UserView.fxml"));
        BorderPane userView = loader.load();
        mainLayout.setCenter(userView);
    }
    
    public static void showPostView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("postview/PostView.fxml"));
        BorderPane postView = loader.load();
        mainLayout.setCenter(postView);
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 

}