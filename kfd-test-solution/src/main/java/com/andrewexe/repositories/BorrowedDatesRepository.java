package com.andrewexe.repositories;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BorrowedDatesRepository implements FileStoredRepository{

    private final HashMap<Book, Date> borrowedBooksDates;

    public BorrowedDatesRepository() {
        borrowedBooksDates = new HashMap<>();
    }

    public void put(Book book, Date date) {
        borrowedBooksDates.put(book, date);
    }

    public Date get(Book book){
        return borrowedBooksDates.get(book);
    }

    public void remove(Book book){
        borrowedBooksDates.remove(book);
    }


    @Override
    public void loadFromCsv(String filename) {

    }

    @Override
    public File saveToCsv(String filename) {
        return null;
    }
}
