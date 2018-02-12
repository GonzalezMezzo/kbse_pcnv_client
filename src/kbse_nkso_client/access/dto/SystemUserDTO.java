/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access.dto;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import kbse_nkso_client.access.builder.SystemUserBuilder;
import kbse_nkso_client.entities.SystemUser;

/**
 *
 * @author nolde
 */
public class SystemUserDTO implements Serializable {

    private Long id;
    private String username;
    private String fname;
    private String lname;
    private String email;
    private AvatarDTO avatar;

    /**
     *
     */
    public SystemUserDTO() {
        this.id = -1L;
    }

    /**
     *
     * @param id
     * @param username
     * @param fname
     * @param lname
     * @param email
     * @param avatar
     */
    public SystemUserDTO(Long id, String username, String fname, String lname, String email, AvatarDTO avatar) {
        this.id = id;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.avatar = avatar;
    }

    /**
     *
     * @param username
     * @param fname
     * @param lname
     * @param email
     * @param avatar
     */
    public SystemUserDTO(String username, String fname, String lname, String email, AvatarDTO avatar) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.avatar = avatar;
    }

    /**
     *
     * @param username
     * @param fname
     * @param lname
     * @param email
     */
    public SystemUserDTO(String username, String fname, String lname, String email) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.avatar = new AvatarDTO();
    }

    /**
     *
     * @param u
     * @return
     */
    public static SystemUserDTO toSystemUserDTO(SystemUser u) {
        if (u == null) {
            return null;
        }

        //conversation to come
        return new SystemUserDTO(u.getId(), u.getUsername(), u.getFname(), u.getLname(), u.getEmail(), AvatarDTO.toAvatarDTO(u.getAvatar()));
    }

    /**
     *
     * @return
     */
    public SystemUser toSystemUser() {
        return SystemUserBuilder.create().username(this.username).firstname(this.fname).lastname(this.lname).email(this.email).avatar(this.avatar.toAvatar()).build();
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObjectBuilder js = Json.createObjectBuilder();
        Gson gson = new Gson();
        String byteArrayString = gson.toJson(this.avatar, AvatarDTO.class);

        if (this.id != null) {
            js.add("id", this.id);
        }

        js.add("username", this.username).add("firstname", this.fname).add("lastname", this.lname).add("email", this.email).add("avatar", byteArrayString);
        return js.build();
    }

    /**
     *
     * @param js
     * @return
     */
    public static SystemUserDTO toPOJO(JsonObject js) {
        Gson gson = new Gson();
        SystemUserDTO u = new SystemUserDTO();

        if (js.getJsonNumber("id") != null) {
            u.setId(js.getJsonNumber("id").longValue());
        }
        u.setUsername(js.getString("username"));
        u.setFname(js.getString("firstname"));
        u.setLname(js.getString("lastname"));
        u.setEmail(js.getString("email"));
        AvatarDTO t = gson.fromJson(js.getString("avatar"), AvatarDTO.class);
        u.setAvatar(gson.fromJson(js.getString("avatar"), AvatarDTO.class));
        return u;
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
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     *
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @param lname
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public AvatarDTO getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     */
    public void setAvatar(AvatarDTO avatar) {
        this.avatar = avatar;
    }

}
