/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import kbse_nkso_client.Main;
import kbse_nkso_client.access.dto.CommentDTO;
import kbse_nkso_client.access.dto.PostDTO;
import kbse_nkso_client.access.dto.SystemUserDTO;
import kbse_nkso_client.controller.ModelController;
import kbse_nkso_client.util.ComboBoxEditingCell;

/**
 * FXML Controller class
 *
 * @author Philipp
 */
public class PostViewController implements Initializable {

    private PostDTO currentPost;
    private SystemUserDTO currentUser;

    @FXML
    private Label description;
    @FXML
    private Label link;

    @FXML
    private ListView<CommentDTO> listViewComments;
    @FXML
    private ListView<PostDTO> listViewPosts;

    private ModelController modelctrl = ModelController.getInstance();

    @FXML
    private TextArea submitComment;
    @FXML
    private TextArea submitDesc;
    @FXML
    private TextField submitURL;
    @FXML
    private TableColumn<PostDTO, Integer> tableColumnRating;
    @FXML
    private TableColumn<PostDTO, Integer> tableColumnTotalRating;
    @FXML
    private TableColumn<PostDTO, String> tableColumnUrl;
    @FXML
    private TableView<PostDTO> tableViewRatings;
    @FXML
    private Label userLabel;

    /**
     * Calls deletePost in ModelController and refreshes the displayed List of
     * Posts in the UI
     */
    @FXML
    public void deletePost() {
        modelctrl.delete(currentPost);
        refreshListViewPosts();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentUser = modelctrl.getCurrentUser();
        modelctrl.refreshState();
        description.setText("");
        link.setText("");
        userLabel.setText("User: " + modelctrl.getInputTextUser());
        refreshListViewPosts();
        refreshRatingTable();

        listViewPosts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PostDTO>() {

            @Override
            public void changed(ObservableValue<? extends PostDTO> observable, PostDTO oldValue, PostDTO newValue) {
                selectPost(newValue);
            }
        });
    }

    /**
     * Fetches current State of data from ModelController, and refreshes the UI
     * list Displaying the comment-data
     */
    public void refreshCommentList() {

        ObservableList<CommentDTO> data = FXCollections.observableArrayList(modelctrl.refreshPost(currentPost).getComments());
        listViewComments.setItems(data);
        listViewComments.setCellFactory(lv -> new ListCell<CommentDTO>() {
            @Override
            public void updateItem(CommentDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text;
                    text = /*new SimpleDateFormat("HHmm_ddMMyy").parse(item.getTimeStamp()) + " " +*/ item.getCreatorId().getUsername() + ": " + item.getMessage();
                    setText(text);
                }
            }
        });
    }

    /**
     * Fetches current State of data from ModelController, and refreshes the UI
     * list Displaying the post-data
     */
    public void refreshListViewPosts() {
        ObservableList<PostDTO> data = FXCollections.observableArrayList(modelctrl.getPostList());
        listViewPosts.setItems(data);
        listViewPosts.setCellFactory(lv -> new ListCell<PostDTO>() {
            @Override
            public void updateItem(PostDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Hyperlink link = new Hyperlink();
                    link.setText(item.getUrl());
                    link.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            System.out.println("This link is clicked");
                            
                        }
                    });
                    setGraphic(link);
                }
            }
        });
    }

    /**
     * Fetches current State of data from ModelController, and refreshes the UI
     * table Displaying the rating-data The Column "Your Rating" accepts input
     * through a EditingCell displaying a Combobox, whose listener is set in
     * this method.
     */
    public void refreshRatingTable() {
        tableViewRatings.setEditable(true);
        ObservableList<PostDTO> data = FXCollections.observableArrayList(modelctrl.getPostList());
        tableViewRatings.setItems(data);

        tableColumnUrl.setCellValueFactory(
                new PropertyValueFactory<PostDTO, String>("url"));

        tableColumnTotalRating.setCellValueFactory(
                new PropertyValueFactory<PostDTO, Integer>("totalRating"));

        Callback<TableColumn<PostDTO, Integer>, TableCell<PostDTO, Integer>> comboBoxCellFactory
                = (TableColumn<PostDTO, Integer> param) -> new ComboBoxEditingCell();

        tableColumnRating.setCellValueFactory(
                new PropertyValueFactory<PostDTO, Integer>("tmpRating"));

        tableColumnRating.setCellFactory(comboBoxCellFactory);

        tableColumnRating.setOnEditCommit(
                (TableColumn.CellEditEvent<PostDTO, Integer> t) -> {
                    if (!currentUser.getUsername().equals(t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()).getCreatorId().getUsername())) {
                        ((PostDTO) t.getTableView().getItems()
                                .get(t.getTablePosition().getRow())).setTmpRating(t.getNewValue());
                    } else {
                        try {
                            showWarning("Voting for your own Posts is not allowed.");
                            ((PostDTO) t.getTableView().getItems()
                                    .get(t.getTablePosition().getRow())).setTmpRating(new Integer("0"));
                        } catch (IOException ex) {
                            System.out.println("Error rendering alert window");
                        }
                    }
                });
    }

    /**
     * Calls submitComment() in ModelController to submit a new Comment to
     * selected Post. Called from the "Add" Button in the "Comments" tab in
     * PostView
     */
    @FXML
    public void submitComment() {
        try {
            modelctrl.submitComment(submitComment.getText());
        } catch (Exception e) {
            try {
                showError(e, "No Post selected,\n"
                        + "please Select a Post first to submit your Comment");
            } catch (IOException ex) {
                System.out.println("Error rendering alert window");
            }
        }
        refreshCommentList();
    }

    /**
     * Calls submitLink() in ModelController to submit a New post to the
     * database Called from the "Submit" Button in the "Submit" tab in PostView
     */
    @FXML
    public void submitPost() {
        modelctrl.submitLink(submitURL.getText(), submitDesc.getText());
        refreshListViewPosts();
        refreshRatingTable();
    }

    /**
     * Calls submitRating() in ModelController to submit Collected Rating to the
     * database Called from the "Submit" Button in the "Rating" tab in PostView
     */
    @FXML
    public void submitRating() {
        modelctrl.submitRating();
        refreshRatingTable();
    }

    /**
     * Calls showUserView() in Main to render the Login-View in the Center of
     * the Main Borderpane
     *
     * @throws IOException
     */
    @FXML
    private void goToUserView() throws IOException {
        Main.showUserView();
    }

    /**
     * Sets selected Post as currentPost and sets the corresponding labels in
     * the "Submit" tab. Refreshes tableViewRatings and corresponding
     * listViewComments.
     *
     * @param post instance of PostDTO the user selected(clicked on) from
     * ListViewPosts
     */
    @FXML
    private void selectPost(PostDTO post) {
        this.currentPost = post;
        this.description.setText(post.getDescription());
        this.link.setText(post.getUrl());
        refreshCommentList();
        refreshRatingTable();
    }

    /**
     * Creates a new Alert Window in case an Exception occured, and prints the
     * stacktrace to TextArea
     *
     * @param e the Exception this function prints into new Alert WIndow
     * @param message message to be printed in the Alert
     * @throws IOException
     */
    @FXML
    private void showError(Exception e, String message) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Action failed");
        alert.setContentText(message);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exception = sw.toString();

        Label label = new Label("Stacktrace:");

        TextArea text = new TextArea(exception);
        text.setEditable(false);
        text.setWrapText(true);

        text.setMaxWidth(Double.MAX_VALUE);
        text.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(text, Priority.ALWAYS);
        GridPane.setHgrow(text, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(text, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    /**
     * Creates new Alert Window in case incorrect Userinput and generates a
     * message defining the problem
     *
     * @param message message to be printed in the Alert
     * @throws IOException
     */
    @FXML
    private void showWarning(String message) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Incorrect Input");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
