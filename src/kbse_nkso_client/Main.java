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
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kbse_nkso_client.controller.ModelController;
import kbse_nkso_client.rest.RESTClient;
import kbse_nkso_client.util.HostServicesControllerFactory;

/**
 *
 * @author Philipp
 */
public class Main extends Application {

    private static BorderPane mainLayout;
    private Stage primaryStage;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Setup FXML and set the "PostView" to be the center of parenting
     * BorderPane in our "MainView"
     *
     * @throws IOException
     */
    public void showPostView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/PostView.fxml"));
        loader.setControllerFactory(new HostServicesControllerFactory(getHostServices()));
        BorderPane postView = loader.load();
        mainLayout.setCenter(postView);
    }

    /**
     * Setup FXML and set the "UserView" to be the center of parenting
     * BorderPane in our "MainView"
     *
     * @throws IOException
     */
    public void showUserView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/UserView.fxml"));
        loader.setControllerFactory(new HostServicesControllerFactory(getHostServices()));
        BorderPane userView = loader.load();
        mainLayout.setCenter(userView);
    }

    public static BorderPane getMainLayout() {
        return mainLayout;
    }

    /**
     * Sets the primary Stage and title for the application
     *
     * @param stage the main application stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("KBSE_Nienhueser_Koschmann_Schaefer_Oldemeier");
        showMainView();
        showUserView();
    }

    /**
     * Setup FXML and create the Scene the whole application is nested in.
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

}
