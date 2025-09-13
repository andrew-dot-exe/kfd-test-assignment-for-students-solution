package com.andrewexe.repositories;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class BorrowedBooksRepository implements FileStoredRepository{
    private HashMap<Book, User> borrowedBooks;

    public BorrowedBooksRepository() {
        this.borrowedBooks = new HashMap<>();
    }

    public void put(Book book, User user){
        borrowedBooks.put(book, user);
    }

    public void remove(Book book){
        borrowedBooks.remove(book);
    }

    public HashMap<Book, User> getBorrowedBooks(){
        return this.borrowedBooks;
    }

    public ArrayList<Book> getBorrowedBooksList(){
        return new ArrayList<>(borrowedBooks.keySet());
    }

    @Override
    public void loadFromCsv(String filename) {

    }

    @Override
    public File saveToCsv(String filename) {
        return null;
    }

    public User get(Book book) {
        return borrowedBooks.get(book);
    }
    public boolean bookIsBorrowed(Book book){
        return borrowedBooks.containsKey(book);
    }
}
