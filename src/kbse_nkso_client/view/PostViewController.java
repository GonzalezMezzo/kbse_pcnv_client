/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javax.inject.Inject;
import kbse_nkso_client.Main;
import kbse_nkso_client.access.CommentDTO;
import kbse_nkso_client.access.PostDTO;
import kbse_nkso_client.controller.Viewmodel;

/**
 * FXML Controller class
 *
 * @author Philipp
 */
public class PostViewController implements Initializable {


    Viewmodel viewmodel = Main.getViewmodel();
    
    @FXML
    private ListView<PostDTO> listView;

    @FXML
    private void goToUserView() throws IOException {
        Main.showUserView();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<PostDTO> data = FXCollections.observableArrayList(viewmodel.getPostList());
        listView.setItems(data);
        //https://stackoverflow.com/questions/29546036/make-list-view-show-what-i-want-javafx
        listView.setCellFactory(lv -> new ListCell<PostDTO>() {
            @Override
            public void updateItem(PostDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = item.getUrl();
            setText(text);
                }
            }
        });
    }

}
