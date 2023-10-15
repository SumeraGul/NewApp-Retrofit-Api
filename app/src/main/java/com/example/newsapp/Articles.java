package com.example.newsapp;

public class Articles {
    private String title;
    private String description;
    private String Url;
    private String UrltoImage;
    private String content;
    public Articles(String title, String description, String url, String urltoImage, String content) {
        this.title = title;
        this.description = description;
        Url = url;
        UrltoImage = urltoImage;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getUrltoImage() {
        return UrltoImage;
    }

    public void setUrltoImage(String urltoImage) {
        UrltoImage = urltoImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
