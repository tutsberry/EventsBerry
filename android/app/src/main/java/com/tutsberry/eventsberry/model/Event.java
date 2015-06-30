package com.tutsberry.eventsberry.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tutsberry.com on 16/05/15.
 * @Author Saquieb Ansari
 * @License MIT 2015
 */
public class Event {

    private int id;
    private String code;
    private String name;
    private String description;
    private String starts;
    private String ends;
    private String venue;
    private String createdAt;
    private String updatedAt;
    private JSONObject theme;
    private List<Page> pages = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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

    public JSONObject getTheme() {
        return theme;
    }

    public void setTheme(JSONObject theme) {
        this.theme = theme;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
