package com.example.splashscreen;

import com.google.gson.annotations.SerializedName;

public class GithubUser {

    private String login;
    private String name;
    private String followers;
    private String following;
    private String email;
    private boolean expanded;

    @SerializedName("avatar_url")
    private String avatar;



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public GithubUser(String login, String name, String followers, String following, String avatar, String email) {
        this.login = login;
        this.name = name;
        this.followers = followers;
        this.following = following;
        this.avatar = avatar;
        this.email = email;
        this.expanded = false;
    }
}
