package com.tutsberry.eventsberry.model;

/**
 * Created by tutsberry.com on 16/05/15.
 * @Author Saquieb Ansari
 * @License MIT 2015
 */
public class Page {

    private int id;
    private String title;
    private String icon;
    private String body;
    private String createdAt;
    private String updatedAt;

    public Page(int id, String title, String icon, String body, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
