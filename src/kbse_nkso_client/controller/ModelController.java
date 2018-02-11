/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kbse_nkso_client.access.dto.CommentDTO;
import kbse_nkso_client.access.dto.PostDTO;
import kbse_nkso_client.access.dto.RatingDTO;
import kbse_nkso_client.access.dto.SystemUserDTO;
import kbse_nkso_client.rest.RESTClient;

/**
 *
 * @author philippnienhuser
 */
public class ModelController implements Serializable {

    private static ModelController instance = new ModelController();

    public static ModelController getInstance() {
        return instance;
    }

    private PostDTO currentPost;
    private SystemUserDTO currentUser;
    private String inputCommentMessage;
    private String inputCommentUser;
    private String inputTexTURL;
    private String inputTextDescription;
    private String inputTextEMail;
    private String inputTextFName;
    private String inputTextLName;
    private int inputTextNumber;
    private String inputTextUser;
    private List<PostDTO> postList;
    private List<SystemUserDTO> userList;
    RESTClient ctrl;

    Long postId;
    String username;

    private ModelController() {
        this.ctrl = RESTClient.getInstance();
        refreshState();
    }

    /**
     * Creates a new User and forwards it to the RESTClient, which will return
     * the persisted or updated user to be set as currentUserr. Called from
     * changeUser() in UserViewController.
     */
    public void changeUser() {
        refreshState();
        SystemUserDTO user = null;
        SystemUserDTO systemUser = null;
        systemUser = ctrl.getSystemUser(this.inputTextUser);
        if (systemUser == null) {
            user = new SystemUserDTO(this.inputTextUser, this.inputTextFName, this.inputTextLName, this.inputTextEMail);
            ctrl.addSystemUser(user);
        } else {
            user = new SystemUserDTO(this.inputTextUser, this.inputTextFName, this.inputTextLName, this.inputTextEMail);
            ctrl.updateSystemUser(user);
        }
        refreshState();
        this.currentUser = user;
    }

    /**
     * Calls the deletePost(long id) function in RESTClient, to initiate a
     * DELETE request. Called from deletePost in PostViewController.
     *
     * @param p Post to be deleted from the database
     */
    public void delete(PostDTO p) {
        refreshState();
        ctrl.deletePost(p.getId());
        refreshState();
    }

    /**
     * Calls getPost(long postId) in RESTClient to refresh the data before
     * rendering the ListViewComments in PostViewController.
     *
     * @param currentPost the post to be refreshed
     * @return the refreshed post
     */
    public PostDTO refreshPost(PostDTO currentPost) {
        postId = currentPost.getId();
        this.currentPost = ctrl.getPost(postId);
        return this.currentPost;
    }

    /**
     * Refreshes the data which includes all posts and users.
     */
    public void refreshState() {
        if (this.currentPost != null) {
            postId = this.currentPost.getId();
            this.currentPost = ctrl.getPost(postId);
        }
        if (this.currentUser != null) {
            username = this.currentUser.getUsername();
            this.currentUser = ctrl.getSystemUser(username);
        }
        this.postList = ctrl.getPostList();
        this.userList = ctrl.getUserList();

        inputTextNumber = 0;
        //ctrl.refreshState();
    }

    /**
     * Creates a new CommentDTO Object to be persisted. Calls
     * addComment(CommentDTO, PostDTO, SystemUserDTO) in RESTClient to initiate
     * a POST request.
     *
     * @param message the String containing the comments Message
     */
    public void submitComment(String message) {
        refreshState();
        CommentDTO comment = new CommentDTO(message, currentUser, currentPost);
        ctrl.addComment(comment, currentPost, currentUser);
        refreshState();
    }

    /**
     * Creates a new PostDTO Object to be persisted. Calls addPost(PostDTO,
     * SystemUserDTO) in RESTClient to initiate a new Post request.
     *
     * @param url
     * @param description
     */
    public void submitLink(String url, String description) {
        PostDTO post = new PostDTO(url, description, currentUser, 0, new ArrayList<>());
        ctrl.addPost(post, currentUser);
        refreshState();
    }

    /**
     * Calls deleteRating(SystemUserDTO) in RESTClient to delete every previous
     * rating of the user before committing a new rating and proceeds to call
     * addRatingg() for every individual Rating this user added since last data
     * refresh.
     */
    public void submitRating() {
        if (validate() == true) {
            //delete every previous rating for this user
            ctrl.deleteRating(currentUser.getUsername());
            //add individual ratings for this submit
            for (PostDTO p : postList) {
                if (p.getTmpRating() != null) {
                    ctrl.addRating(p, new RatingDTO(p.getTmpRating(), currentUser, p), currentUser);
                }
            }
        }
        // fetching the data state from db resets all the previous tmpRatings to 0
        refreshState();
    }

    /**
     * Counts all the user submitted individual ratings for this Rating submit,
     * and checks i their sum is in bound.
     *
     * @return true if sum of ratings is allowed, false if not
     */
    public boolean validate() {
        int res = 0;
        for (PostDTO p : postList) {
            if (p.getTmpRating() != null) {
                res += p.getTmpRating().intValue();
            }
        }
        if (res <= 10 && res >= 0) {
            return true;
        }
        return false;
    }

    public PostDTO getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(PostDTO currentPost) {
        this.currentPost = currentPost;
    }

    public SystemUserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SystemUserDTO currentUser) {
        this.currentUser = currentUser;
    }

    public String getInputCommentMessage() {
        return inputCommentMessage;
    }

    public void setInputCommentMessage(String inputCommentMessage) {
        this.inputCommentMessage = inputCommentMessage;
    }

    public String getInputCommentUser() {
        return inputCommentUser;
    }

    public void setInputCommentUser(String inputCommentUser) {
        this.inputCommentUser = inputCommentUser;
    }

    public String getInputTexTURL() {
        return inputTexTURL;
    }

    public void setInputTexTURL(String inputTexTURL) {
        this.inputTexTURL = inputTexTURL;
    }

    public String getInputTextDescription() {
        return inputTextDescription;
    }

    public void setInputTextDescription(String inputTextDescription) {
        this.inputTextDescription = inputTextDescription;
    }

    public String getInputTextEMail() {
        return inputTextEMail;
    }

    public void setInputTextEMail(String inputTextEMail) {
        this.inputTextEMail = inputTextEMail;
    }

    public String getInputTextFName() {
        return inputTextFName;
    }

    public void setInputTextFName(String inputTextFName) {
        this.inputTextFName = inputTextFName;
    }

    public String getInputTextLName() {
        return inputTextLName;
    }

    public void setInputTextLName(String inputTextLName) {
        this.inputTextLName = inputTextLName;
    }

    public int getInputTextNumber() {
        return inputTextNumber;
    }

    public void setInputTextNumber(int inputTextNumber) {
        this.inputTextNumber = inputTextNumber;
    }

    public String getInputTextUser() {
        return inputTextUser;
    }

    public void setInputTextUser(String inputTextUser) {
        this.inputTextUser = inputTextUser;
    }

    public List<PostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }
}
