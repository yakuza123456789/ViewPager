
package com.example.android3_lesosn3.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllPostsModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("group")
    @Expose
    private Integer group;

    public AllPostsModel(String title, String content, int user, Integer group) {
        this.title = title;
        this.group = group;
        this.content = content;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

}
