/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access;

import kbse_nkso_client.entities.Comment;
import kbse_nkso_client.entities.Post;



/**
 *
 * @author chrschae
 */
public class CommentBuilder {

    private long id;
    private String message;
    private String timeStamp;
    private String creator;
    private Post owner;

    private CommentBuilder(){}
    
    public static CommentBuilder create() {
        return new CommentBuilder();
    }

    public Comment build() {
        Comment res = new Comment();
        res.setId(this.id);
        res.setMessage(this.message);
        res.setTimestamp(this.timeStamp);
        res.setCreator(this.creator);
        res.setOwner(this.owner);
        return res;
    }

    public CommentBuilder id(long id) {
        this.id = id;
        return this;
    }

    public CommentBuilder message(String message) {
        this.message = message;
        return this;
    }

    public CommentBuilder timestamp(String timestamp) {
        this.timeStamp = timestamp;
        return this;
    }

    public CommentBuilder creator(String creator) {
        this.creator = creator;
        return this;
    }
    
    public CommentBuilder owner(Post owner){
        this.owner = owner;
        return this;
    }

}
