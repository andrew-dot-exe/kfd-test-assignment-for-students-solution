package com.andrewexe.repositories;



import com.andrewexe.entities.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {

    private String repositoryFilename = null;
    private Map<Long, Book> books;

    public BookRepository(){
        books = new HashMap<Long, Book>();
    }

    public void loadData(String filename){
        repositoryFilename = filename;
        if(repositoryFilename == null){
            //in-memory
            addBook(new Book(1L, "War and Peace", "Lev Tolsoi", 2144));
            addBook(new Book(2L, "Idiot", "Fedor Dostoevskii", 640));
            addBook(new Book(3L, "Ruslan And Lyudmila", "Aleksandr Pushkin", 336));
            addBook(new Book(4L, "Postgres User Guide. The Introduction", "P. Puzanov, E.Rogov, I.Levshin", 190));
            addBook(new Book(5L, "All About Scrum", "Claude Orbie", 352));

        }
    }

    public void addBook(Book book) throws IllegalArgumentException{
        books.put(book.getIsbn(), book);
    }

    public void removeBook(Book book) throws IllegalArgumentException{
        books.remove(book.getIsbn());
    }

    public List<Book> getAllBooks(){
        return new ArrayList<Book>(books.values());
    }

    public void save(){
        if(repositoryFilename != null){
            System.out.println("saving data to file");
        }
    }
}
