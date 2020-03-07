package com.example.anuin.utils.model;

public class Users {

    private int id;
    private String name;
    private String email;
    private String username;
    private String token;
    private String deleted;
    private String actived;
    private String type;
    private String member_image;
    private String unextimage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMember_image() {
        return member_image;
    }

    public void setMember_image(String member_image) {
        this.member_image = member_image;
    }

    public String getUnextimage() {
        return unextimage;
    }

    public void setUnextimage(String unextimage) {
        this.unextimage = unextimage;
    }
}
