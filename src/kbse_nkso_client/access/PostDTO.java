/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import kbse_nkso_client.entities.Comment;
import kbse_nkso_client.entities.Post;

/**
 *
 * @author philippnienhuser
 */
public class PostDTO implements Serializable{

    public static PostDTO toPOJO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Long id;
    private String url;
    private String description;
    private String creator;
    private int totalRating;
    private List<CommentDTO> comments;
    private Map<String,Integer> ratings;

    public PostDTO(){}
    
    public PostDTO(String url, String comment, String creator, int totalRating, Map<String,Integer> ratings){
        this.url = url;
        this.description = comment;
        this.comments = new ArrayList<>();
        this.creator = creator;
        this.totalRating = totalRating;
        this.ratings = ratings;
    }
    
    public PostDTO(Long id, String url,String comment, String creator, int totalRating, Map<String,Integer> ratings, List<CommentDTO> comments){
        this.id =id;
        this.description = comment;
        this.url = url;
        this.comments = comments;
        this.creator = creator;
        this.totalRating = totalRating;
        this.ratings = ratings;
    }
    
    public static PostDTO toPostDTO(Post p){
        if(p == null){
            return null;
        }
        List<CommentDTO> comments = new ArrayList<CommentDTO>();
        for(Comment c: p.getComments()){
            comments.add(CommentDTO.toCommentDTO(c));
        }
        return new PostDTO(p.getId(),p.getUrl(),p.getDescription(), p.getCreator(), p.getTotalRating(), p.getRatings(), comments);
    }
    
    public Post toPost(){
        return PostBuilder.create().url(this.url).comment(this.description).creator(this.creator).totalRating(this.totalRating).ratings(this.ratings).build();
    }
    
    public JsonObject toJsonObject(){
        JsonObjectBuilder js = Json.createObjectBuilder();
        Gson gson = new Gson();
        String mapString = gson.toJson(this.ratings);
        String listString = gson.toJson(this.comments);
        if(this.id != null){
            js.add("id", this.id);
        }
        js.add("url", this.url)
           .add("description", this.description)
           .add("creator", this.creator)
           .add("totalRating", this.totalRating)
           .add("ratings", mapString)
           .add("comments", listString);
        return js.build();
    }
    
    public static PostDTO toPOJO(JsonObject js){
        Gson gson = new Gson();
        
        PostDTO p = new PostDTO();
        if(js.getJsonNumber("id")!= null){
            p.setId(js.getJsonNumber("id").longValue());
        }
        p.setUrl(js.getString("url"));
        p.setDescription(js.getString("description"));
        p.setCreator(js.getString("creator"));
        p.setTotalRating(js.getInt("totalRating"));
        p.setComments(gson.fromJson(js.getString("comments"), new TypeToken<List<CommentDTO>>(){}.getType()));
        p.setRatings(gson.fromJson(js.getString("ratings"),new TypeToken<Map<String, Integer>>() {
		}.getType()));
        return p;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Map<String, Integer> ratings) {
        this.ratings = ratings;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
    
    
    
    
}
