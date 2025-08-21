package com.andrewexe.entities;

public class Book {
    private Long isbn;
    private String name;
    private String author;
    private int pageCount;

    public Book(Long isbn, String name, String author, int pageCount) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.pageCount = pageCount;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }
}
