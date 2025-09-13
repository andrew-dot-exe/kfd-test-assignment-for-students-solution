package com.andrewexe.entities;

public class Book extends BaseEntity{

    private final Long isbn;
    private final String name;
    private final String author;
    private final int pageCount;

    public Book(int id, Long isbn, String name, String author, int pageCount) {
        super(id);
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

    @Override
    public String getInfo() {
        return author + " - " + name + " ISBN: " + isbn + " " + pageCount + "pages";
    }
}
