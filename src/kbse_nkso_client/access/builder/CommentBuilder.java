/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access.builder;


import kbse_nkso_client.entities.Comment;
import kbse_nkso_client.entities.Post;
import kbse_nkso_client.entities.SystemUser;


/**
 *
 * @author chrschae
 */
public class CommentBuilder {

    private long id;
    private String message;
    private String timeStamp;
    private SystemUser creatorId;
    private Post ownerId;

    private CommentBuilder(){}
    
    /**
     *
     * @return
     */
    public static CommentBuilder create() {
        return new CommentBuilder();
    }

    /**
     *
     * @return
     */
    public Comment build() {
        Comment res = new Comment();
        res.setId(this.id);
        res.setMessage(this.message);
        res.setTimestamp(this.timeStamp);
        res.setAuthor(this.creatorId);
        //res.setPost(this.ownerId);
        return res;
    }

    /**
     *
     * @param id
     * @return
     */
    public CommentBuilder id(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param message
     * @return
     */
    public CommentBuilder message(String message) {
        this.message = message;
        return this;
    }

    /**
     *
     * @param timestamp
     * @return
     */
    public CommentBuilder timestamp(String timestamp) {
        this.timeStamp = timestamp;
        return this;
    }

    /**
     *
     * @param creator
     * @return
     */
    public CommentBuilder creator(SystemUser creator) {
        this.creatorId = creator;
        return this;
    }
    
    /**
     *
     * @param owner
     * @return
     */
    public CommentBuilder owner(Post owner){
        this.ownerId = owner;
        return this;
    }

}
