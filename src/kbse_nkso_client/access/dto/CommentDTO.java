/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import kbse_nkso_client.access.builder.CommentBuilder;
import kbse_nkso_client.entities.Comment;

/**
 *
 * @author chrschae
 */
public class CommentDTO implements Serializable {

    private Long id;
    private String message;
    private String timeStamp;
    private SystemUserDTO creatorId;
    private PostDTO ownerId;

    public CommentDTO() {
    }

    public CommentDTO(String message, SystemUserDTO creatorId, PostDTO ownerId) {
        this.creatorId = creatorId;
        this.timeStamp = new SimpleDateFormat("HHmmss_ddMMyyyy").format(Calendar.getInstance().getTime());
        this.message = message;
        this.ownerId = ownerId;
    }

    public CommentDTO(Long id, String message, SystemUserDTO creatorId, String timeStamp) {
        this.id = id;
        this.message = message;
        this.timeStamp = timeStamp;
        this.creatorId = creatorId;
    }

    public CommentDTO(Long id, String message, SystemUserDTO creatorId, String timestamp, PostDTO ownerId) {
        this.id = id;
        this.creatorId = creatorId;
        this.timeStamp = timestamp;
        this.message = message;
        this.ownerId = ownerId;
    }

    public static CommentDTO toCommentDTO(Comment c) {
        if (c == null) {
            return null;
        }
        return new CommentDTO(c.getId(), c.getMessage(), SystemUserDTO.toSystemUserDTO(c.getAuthor()), c.getTimestamp()/*, PostDTO.toPostDTO(c.getPost())*/);
    }

    public Comment toComment() {
        return CommentBuilder.create().message(this.message)
                .creator(this.creatorId.toSystemUser()).timestamp(this.timeStamp).owner(this.ownerId.toPost()).build();
    }

    public JsonObject toJsonObject() {

        JsonObjectBuilder js = Json.createObjectBuilder();
        if (this.id != null) {
            js.add("id", this.id);
        }
        js.add("message", this.message)
                .add("timestamp", this.timeStamp)
                .add("creatorId", this.creatorId.toJsonObject())
                .add("owner", this.ownerId.toJsonObject());
        return js.build();
    }

    public static CommentDTO toPOJO(JsonObject js) {
        CommentDTO c = new CommentDTO();

        if (js.getJsonNumber("id") != null) {
            c.setId(js.getJsonNumber("id").longValue());
        }
        c.setMessage(js.getString("message"));
        c.setTimeStamp(js.getString("timestamp"));
        c.setCreatorId(SystemUserDTO.toPOJO(js.getJsonObject("creatorId")));
        c.setOwnerId(PostDTO.toPOJO(js.getJsonObject("owner")));
        return c;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public SystemUserDTO getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(SystemUserDTO creatorId) {
        this.creatorId = creatorId;
    }

    public PostDTO getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(PostDTO ownerId) {
        this.ownerId = ownerId;
    }
}
