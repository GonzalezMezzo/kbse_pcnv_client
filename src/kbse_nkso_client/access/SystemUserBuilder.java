/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.access;

import kbse_nkso_client.entities.Avatar;
import kbse_nkso_client.entities.SystemUser;

/**
 *
 * @author nolde
 */
public class SystemUserBuilder {

    private long id;
    private String username;
    private String fname;
    private String lname;
    private String email;
    private Avatar avatar;

    private SystemUserBuilder() {
    }

    /**
     *
     * @return
     */
    public static SystemUserBuilder create() {
        return new SystemUserBuilder();
    }

    /**
     *
     * @param id
     * @return
     */
    public SystemUserBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public SystemUserBuilder username(String username) {
        this.username = username;
        return this;
    }
    
    /**
     *
     * @param fname
     * @return
     */
    public SystemUserBuilder firstname(String fname) {
        this.fname = fname;
        return this;
    }
    
    /**
     *
     * @param lname
     * @return
     */
    public SystemUserBuilder lastname(String lname) {
        this.lname = lname;
        return this;
    }
    
    /**
     *
     * @param email
     * @return
     */
    public SystemUserBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    /**
     *
     * @param avatar
     * @return
     */
    public SystemUserBuilder avatar(Avatar avatar) {
        this.avatar = avatar;
        return this;
    }
    
    /**
     *
     * @return
     */
    public SystemUser build() {
        SystemUser res = new SystemUser();
        res.setId(this.id);
        res.setUsername(this.username);
        res.setFname(this.fname);
        res.setLname(this.lname);
        res.setEmail(this.email);
        res.setAvatar(this.avatar);
        return res;
    }
}
