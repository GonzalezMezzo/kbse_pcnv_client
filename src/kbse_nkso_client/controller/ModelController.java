/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.controller;

import kbse_nkso_client.rest.RestFrontendController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kbse_nkso_client.Main;
import kbse_nkso_client.access.CommentDTO;
import kbse_nkso_client.access.PostDTO;
import kbse_nkso_client.access.RatingDTO;
import kbse_nkso_client.access.SystemUserDTO;

/**
 *
 * @author philippnienhuser
 */
public class ModelController implements Serializable {

    RestFrontendController ctrl;

    private List<PostDTO> postList;
    private List<SystemUserDTO> userList;
    private String inputTextUser;
    private String inputTextFName;
    private String inputTextLName;
    private String inputTextEMail;
    private String inputTextDescription;
    private String inputTexTURL;
    private int inputTextNumber;

    private String inputCommentUser;
    private String inputCommentMessage;

    private SystemUserDTO currentUser;
    private PostDTO currentPost;

    Long postId;
    String username;

    /**
     *
     */
    public ModelController() {
        this.ctrl = Main.getRestctrl();
        this.inputTextUser = "User";
        refreshState();
        currentPost = postList.get(0);
    }

    /**
     *
     */
    public void changeUser() {
        refreshState();
        SystemUserDTO user = new SystemUserDTO(this.inputTextUser, this.inputTextFName, this.inputTextLName, this.inputTextEMail);
        ctrl.addSystemUser(user);
        refreshState();
        this.currentUser = user;
    }

    /**
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
     *
     * @param p
     */
    public void delete(PostDTO p) {
        refreshState();
        ctrl.deletePost(p.getId());
        refreshState();
    }

    /**
     *
     * @param message
     */
    public void submitComment(String message) {
        refreshState();
        CommentDTO comment = new CommentDTO(message, currentUser, currentPost);
        ctrl.addComment(comment, currentPost, currentUser);
        refreshState();
    }

    /**
     *
     * @param currentPost
     * @return
     */
    public PostDTO refreshPost(PostDTO currentPost) {
        postId = currentPost.getId();
        this.currentPost = ctrl.getPost(postId);
        return this.currentPost;
    }

    /**
     *
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
        ctrl.refreshState();
    }

    /**
     *
     * @param i
     */
    public void selectPost(PostDTO i) {
        ctrl.refreshState();
        this.postList = ctrl.getPostList();
        this.currentPost = i;
    }

    /**
     * Get user's rating on index i on the current rendered list of posts
     *
     * @param i
     * @return rating
     */
    public int getPersonalRating(int i) {
        //int rating = this.postList.get(i).getRatings().get(inputTextUser);
        return 0;
    }

    /**
     *
     * @param i
     * @return
     */
    public boolean renderInputForRating(int i) {
        if (inputTextUser.equals(postList.get(i).getCreatorId())) {
            return false;
        }
        return true;
    }

    /**
     *
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
        refreshState();
    }

    /**
     *
     * @return
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

    /*--------------------------------------------------------------------------
    getter/setter
    --------------------------------------------------------------------------*/
    public String getInputTexTURL() {
        return inputTexTURL;
    }

    public void setInputTexTURL(String inputTexTURL) {
        this.inputTexTURL = inputTexTURL;
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

    public String getInputTextEMail() {
        return inputTextEMail;
    }

    public void setInputTextEMail(String inputTextEMail) {
        this.inputTextEMail = inputTextEMail;
    }

    public String getInputTextUser() {
        return inputTextUser;
    }

    public void setInputTextUser(String inputTextUser) {
        this.inputTextUser = inputTextUser;
    }

    public String getInputTextDescription() {
        return inputTextDescription;
    }

    public void setInputTextDescription(String inputTextDescription) {
        this.inputTextDescription = inputTextDescription;
    }

    public List<PostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }

    public int getInputTextNumber() {
        return inputTextNumber;
    }

    public void setInputTextNumber(int inputTextNumber) {
        this.inputTextNumber = inputTextNumber;
    }

    public PostDTO getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(PostDTO currentPost) {
        this.currentPost = currentPost;
    }

    public String getInputCommentUser() {
        return inputCommentUser;
    }

    public void setInputCommentUser(String inputCommentUser) {
        this.inputCommentUser = inputCommentUser;
    }

    public String getInputCommentMessage() {
        return inputCommentMessage;
    }

    public void setInputCommentMessage(String inputCommentMessage) {
        this.inputCommentMessage = inputCommentMessage;
    }

    public SystemUserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SystemUserDTO currentUser) {
        this.currentUser = currentUser;
    }
}
