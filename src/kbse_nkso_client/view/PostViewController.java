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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
import kbse_nkso_client.access.CommentDTO;
import kbse_nkso_client.access.PostDTO;
import kbse_nkso_client.access.SystemUserDTO;
import kbse_nkso_client.controller.ModelController;

/**
 * FXML Controller class
 *
 * @author Philipp
 */
public class PostViewController implements Initializable {

    //@Inject
    ModelController modelctrl = Main.getModelController();

    @FXML private ListView<PostDTO> listViewPosts;
    @FXML private ListView<PostDTO> listViewRatings;
    @FXML private ListView<CommentDTO> listViewComments;
    @FXML private TableView<PostDTO> tableViewRatings;
    @FXML private TableColumn<PostDTO, String> tableColumnUrl;
    @FXML private TableColumn<PostDTO, Integer> tableColumnRating;
    @FXML private TableColumn<PostDTO, Integer> tableColumnTotalRating;

    /////////////////////////////////////////////submitpost
    @FXML private TextField submitURL;
    @FXML private TextArea submitDesc;
    @FXML private TextArea submitComment;
    @FXML private Label userLabel;

    @FXML private Label link;
    @FXML private Label description;

    private PostDTO currentPost;
    private SystemUserDTO currentUser;

    private final ObservableList<Integer> integerList
            = FXCollections.observableArrayList(
                    new Integer(0),
                    new Integer(1),
                    new Integer(2),
                    new Integer(3),
                    new Integer(4),
                    new Integer(5),
                    new Integer(6),
                    new Integer(7),
                    new Integer(8),
                    new Integer(9),
                    new Integer(10));

    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentUser = modelctrl.getCurrentUser();
        modelctrl.refreshState();
        description.setText("");
        link.setText("");
        userLabel.setText("User: " + modelctrl.getInputTextUser());
        refreshListView();
        refreshRatingTable();

        listViewPosts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PostDTO>() {

            @Override
            public void changed(ObservableValue<? extends PostDTO> observable, PostDTO oldValue, PostDTO newValue) {
                selectPost(newValue);
                System.out.println("Selected item: " + newValue.getUrl());
            }
        });
    }

    /**
     * 
     */
    public void refreshListView() {
        ObservableList<PostDTO> data = FXCollections.observableArrayList(modelctrl.getPostList());
        listViewPosts.setItems(data);
        //https://stackoverflow.com/questions/29546036/make-list-view-show-what-i-want-javafx
        listViewPosts.setCellFactory(lv -> new ListCell<PostDTO>() {
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

    /**
     * 
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
                    if(!currentUser.getUsername().equals(t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()).getCreatorId().getUsername())){
                    ((PostDTO) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow())).setTmpRating(t.getNewValue());
                    }else{
                        try {
                            showWarning("Voting for your own Posts is not allowed.");
                            ((PostDTO) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow())).setTmpRating(new Integer("0"));
                        } catch (IOException ex) {
                            System.out.println("error rendering alert window");
                        }
                    }
                });
    }

    /**
     * 
     */
    public void refreshCommentList() {

        ObservableList<CommentDTO> data = FXCollections.observableArrayList(modelctrl.refreshPost(currentPost).getComments());
        listViewComments.setItems(data);
        //https://stackoverflow.com/questions/29546036/make-list-view-show-what-i-want-javafx
        listViewComments.setCellFactory(lv -> new ListCell<CommentDTO>() {
            @Override
            public void updateItem(CommentDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = item.getCreatorId().getUsername() + ": " + item.getMessage();
                    setText(text);
                }
            }
        });
    }
    
    /**
     * 
     */
    @FXML
    public void deletePost(){
        modelctrl.delete(currentPost);
        refreshListView();
    }

    /**
     * 
     */
    @FXML
    public void submitPost() {
        modelctrl.submitLink(submitURL.getText(), submitDesc.getText());
        refreshListView();
        refreshRatingTable();
    }

    /**
     * 
     */
    @FXML
    public void submitComment() {
        modelctrl.submitComment(submitComment.getText());
        refreshCommentList();
    }

    /**
     * 
     */
    @FXML
    public void submitRating() {
        modelctrl.submitRating();
        refreshRatingTable();
    }

    /**
     * 
     * @param e
     * @throws IOException 
     */
    @FXML
    private void showError(Exception e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Action failed");
        alert.setContentText("A wild Exception appeared");

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
     * 
     * @param post 
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
     * 
     * @param message
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

    /**
     * 
     * @throws IOException 
     */
    @FXML
    private void goToUserView() throws IOException {
        Main.showUserView();
    }

    /**
     * 
     */
    class ComboBoxEditingCell extends TableCell<PostDTO, Integer> {

        private ComboBox<Integer> comboBox;

        private ComboBoxEditingCell() {
        }

        /**
         * 
         */
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createComboBox();
                setText(null);
                setGraphic(comboBox);
            }
        }

        /**
         * 
         */
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getTyp().toString());
            setGraphic(null);
        }

        /**
         * 
         * @param item
         * @param empty 
         */
        @Override
        public void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (comboBox != null) {
                        comboBox.setValue(getTyp());
                    }
                    setText(getTyp().toString());
                    setGraphic(comboBox);
                } else {
                    setText(getTyp().toString());
                    setGraphic(null);
                }
            }
        }

        /**
         * 
         */
        private void createComboBox() {
            comboBox = new ComboBox<>(integerList);
            comboBoxConverter(comboBox);
            comboBox.valueProperty().set(getTyp());
            comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            comboBox.setOnAction((e) -> {
                //System.out.println("Committed: " + comboBox.getSelectionModel().getSelectedItem());
                
                commitEdit(comboBox.getSelectionModel().getSelectedItem());
            });
//            comboBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                if (!newValue) {
//                    commitEdit(comboBox.getSelectionModel().getSelectedItem());
//                }
//            });
        }

        /**
         * 
         * @param comboBox 
         */
        private void comboBoxConverter(ComboBox<Integer> comboBox) {
            // Define rendering of the list of values in ComboBox drop down. 
            comboBox.setCellFactory((c) -> {
                return new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            });
        }

        private Integer getTyp() {
            return getItem() == null ? new Integer("0") : getItem();
        }
    }
}
