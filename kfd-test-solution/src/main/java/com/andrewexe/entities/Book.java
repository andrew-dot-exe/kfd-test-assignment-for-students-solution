package com.andrewexe.entities;

public class Book {
    private Long Isbn;
    private String Name;
    private String Author;
    private int pageCount;

    public Book(Long isbn, String name, String author, int pageCount) {
        Isbn = isbn;
        Name = name;
        Author = author;
        this.pageCount = pageCount;
    }

    public Long getIsbn() {
        return Isbn;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public int getPageCount() {
        return pageCount;
    }
}
