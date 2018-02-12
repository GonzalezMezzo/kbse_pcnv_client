/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import kbse_nkso_client.access.builder.RatingBuilder;
import kbse_nkso_client.entities.Rating;

/**
 *
 * @author nolde
 */
public class RatingDTO implements Serializable {

    private Long id;
    private int ratedValue;
    private SystemUserDTO user;
    
    
    private PostDTO post;
    
    /**
     *
     */
    public RatingDTO(){}

    /**
     *
     * @param id
     * @param ratedValue
     * @param user
     */
    public RatingDTO(Long id, int ratedValue, SystemUserDTO user) {
        this.id = id;
        this.ratedValue = ratedValue;
        this.user = user;
        this.post = post;
    }

    /**
     *
     * @param ratedValue
     * @param user
     * @param post
     */
    public RatingDTO(int ratedValue, SystemUserDTO user, PostDTO post) {
        this.ratedValue = ratedValue;
        this.user = user;
        this.post = post;
    }
       
    /**
     *
     * @param r
     * @return
     */
    public static RatingDTO toRatingDTO(Rating r) {
         return new RatingDTO(r.getId(),r.getRatedValue(),SystemUserDTO.toSystemUserDTO(r.getUser()));
    }
    
    /**
     *
     * @return
     */
    public Rating toRating() {
        return RatingBuilder.create().ratedValue(this.ratedValue).user(user.toSystemUser())/*.post(post.toPost())*/.build();
    }

    /**
     *
     * @return
     */
    public SystemUserDTO getUser() {
        return user;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public int getRatedValue() {
        return ratedValue;
    }

    /**
     *
     * @return
     */
    public PostDTO getPost() {
        return post;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param ratedValue
     */
    public void setRatedValue(int ratedValue) {
        this.ratedValue = ratedValue;
    }

    /**
     *
     * @param user
     */
    public void setUser(SystemUserDTO user) {
        this.user = user;
    }

    /**
     *
     * @param post
     */
    public void setPost(PostDTO post) {
        this.post = post;
    }
    
    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObjectBuilder js = Json.createObjectBuilder();
        if (this.id != null) {
            js.add("id", this.id);
        }
        js.add("ratedValue", this.ratedValue)
                .add("user", this.user.toJsonObject())
                .add("post", this.post.toJsonObject());
        return js.build();
    }
    
    /**
     *
     * @param js
     * @return
     */
    public static RatingDTO toPOJO(JsonObject js) {
        RatingDTO r = new RatingDTO();
        
        if (js.getJsonNumber("id") != null) {
            r.setId(js.getJsonNumber("id").longValue());
        }
        r.setRatedValue(js.getInt("ratedValue"));
        r.setUser(SystemUserDTO.toPOJO(js.getJsonObject("user")));
        r.setPost(PostDTO.toPOJO(js.getJsonObject("post")));
        return r;
    }
    
}
