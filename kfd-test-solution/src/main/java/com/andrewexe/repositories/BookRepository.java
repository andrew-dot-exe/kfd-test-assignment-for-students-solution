package com.andrewexe.repositories;



import com.andrewexe.entities.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository implements FileStoredRepository{
    private Map<Integer, Book> books;

    public BookRepository(){
        books = new HashMap<Integer, Book>();
    }


    public void addBook(Book book) throws IllegalArgumentException{
        books.put(book.getId(), book);
    }

    public List<Book> getAllBooks(){
        return new ArrayList<Book>(books.values());
    }

    public Book getBook(int id){
        return books.get(id);
    }

    public ArrayList<Book> findBook(String phrase){
        phrase = phrase.toLowerCase();
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books.values()){
            String isbn = book.getIsbn().toString();
            String bookNameLower = book.getName().toLowerCase();
            String bookAuthorLower = book.getAuthor().toLowerCase();

            if(isbn.contains(phrase) ||bookAuthorLower.contains(phrase) || bookNameLower.contains(phrase)){
                result.add(book);
            }
        }
        return result;
    }


    @Override
    public void loadFromCsv(String filename) {

    }

    @Override
    public File saveToCsv(String filename) {
        return null;
    }
}
