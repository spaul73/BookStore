package com.project.bookstore.models;

public class Book {

    private Long id;
    private String name;
    private String description;
    private String source;
    private String cover;
    private String category;
    private String author;
    private String username;

    public Book(String name, String description, String source, String cover, String category, String author, String username) {
        this(null, name, description, source, cover, category, author, username);
    }
    
    public Book(Long id, String name, String description, String source, String cover, String category, String author, String username) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.source = source;
        this.cover = cover;
        this.category = category;
        this.author = author;
        this.username = username;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}