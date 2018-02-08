/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.controller;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import kbse_nkso_client.Main;
import kbse_nkso_client.access.CommentDTO;
import kbse_nkso_client.access.PostDTO;
import kbse_nkso_client.controller.RestFrontendController;

/**
 *
 * @author philippnienhuser
 */
@Named(value = "viewmodel")
public class ModelController implements Serializable{

    RestFrontendController restctrl;
    
    private List<PostDTO> postList;
    private String inputTextUser;
    private String inputTextComment;
    private boolean changeUserBool = false;
    private boolean changeUebernehmenBool = false;
    private int inputTextNumber =0;
    private String comment = "Comment";
    private String url = "www.google.de";
    private int[] ratingCollector;
    
    private int test;
    private Long currentPostId;
    private PostDTO currentPost;
    
    public ModelController(){
        this.restctrl = Main.getRestctrl();
        this.inputTextUser = "User";
        refreshState();
        currentPost = postList.get(0);
        ratingCollector = new int[postList.size()];
    }
    
    public void changeUser(){
        
    }
    
    public void submitLink(String url, String description){
        PostDTO post = new PostDTO(url,description,inputTextUser,0,new HashMap<String,Integer>());
        post.getComments().add(new CommentDTO(description, this.inputTextUser, post));
        post.getRatings().put(inputTextUser, 0);
        restctrl.addPost(post);
        refreshState();
    }
    
    public void delete(PostDTO p){
        restctrl.deletePost(p.getId());
        refreshState();
    }
    
    public void submitComment(String message, String user, PostDTO currentPost){
        CommentDTO comment = new CommentDTO(message, user, currentPost);
        restctrl.addComment(currentPostId, comment);
        refreshState();
    }
    
    public void refreshState(){
        this.postList = restctrl.refreshState();
        ratingCollector = new int[postList.size()];
        inputTextNumber = 0;
    }
    
    /**
     * Get user's rating on index i on the current rendered list of posts
     * @param i
     * @return rating
     */
    public int getPersonalRating(int i){
        //int rating = this.postList.get(i).getRatings().get(inputTextUser);
        return 0;
    }
    
    public boolean renderInputForRating(int i){
        if(inputTextUser.equals(postList.get(i).getCreator()))
            return false;
        return true;
    }
    
    public void submitRating(){
        if (validate()==true){//method stub
        for(int i =0;i<ratingCollector.length;i++){
            if(ratingCollector[i]!=0)
                this.postList.get(i).getRatings().put(inputTextUser, new Integer(ratingCollector[i]));
            restctrl.updateRatings(this.postList);
        }
        refreshState();
        }
        
    }
    
    /**
     * fetches rating input for post at pos i 
     * @param i 
     */
    public void addRating(int i){
        ratingCollector[i] = inputTextNumber;
    }

        /*--------------------------------------------------------------------------
    getter
    --------------------------------------------------------------------------*/

    public String getInputTextUser() {
        return inputTextUser;
    }

    public void setInputTextUser(String inputTextUser) {
        this.inputTextUser = inputTextUser;
    }

    public String getInputTextComment() {
        return inputTextComment;
    }

    public void setInputTextComment(String inputTextComment) {
        this.inputTextComment = inputTextComment;
    }

    public int getInputTextNumber() {
        return inputTextNumber;
    }

    public void setInputTextNumber(int inputTextNumber) {
        this.inputTextNumber = inputTextNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isChangeUserBool() {
        return changeUserBool;
    }

    public void setChangeUserBool(boolean changeUserBool) {
        this.changeUserBool = changeUserBool;
    }

    public boolean isChangeUebernehmenBool() {
        return changeUebernehmenBool;
    }

    public void setChangeUebernehmenBool(boolean changeUebernehmenBool) {
        this.changeUebernehmenBool = changeUebernehmenBool;
    }

    public List<PostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }

    public int[] getRatingCollector() {
        return ratingCollector;
    }

    public void setRatingCollector(int[] ratingCollector) {
        this.ratingCollector = ratingCollector;
    }

    private boolean validate() {
        int n1 =0;
        for(int i =0; i<ratingCollector.length; i++){
            n1 += ratingCollector[i];
            
            System.out.println("test" + n1);
            if(ratingCollector[i]<0 || ratingCollector[i]>10 || n1>10){
                return false;
            }
        }
        return true;      
    }
}
