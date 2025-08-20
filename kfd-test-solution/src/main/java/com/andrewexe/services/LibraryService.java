package com.andrewexe.services;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;
import com.andrewexe.repositories.BookRepository;
import com.andrewexe.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryService {

    private UserRepository userRepository;
    private BookRepository bookRepository;

    private HashMap<User, Integer> borrowedBooksCount;
    private HashMap<Book, User> borrowedBooks; // поскольку книга у нас может быть только в единственном экземпляре,
                                               // то мы можем использовать ее как ключ

    public LibraryService() {
        this.userRepository = new UserRepository();
        this.bookRepository = new BookRepository();
        this.borrowedBooksCount = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
    }

    public void borrowBook(User user, Book book)
    {
        if(bookIsBorrowed(book) || !userCanBorrow(user)){
            return;
        }
        borrowedBooks.put(book, user);
        setBorrowedBooksCount(user);
    }

    public void returnBook(User user, Book book){
        int newCount = borrowedBooksCount.get(user) - 1;
        borrowedBooksCount.replace(user, newCount);
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks(){
        return new ArrayList<Book>(borrowedBooks.keySet());
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public List<Book> getFreeBooks(){
        List<Book> all = getAllBooks();
        all.remove(getBorrowedBooks());
        return all;
    }

    public List<User> getUsers(){
        return userRepository.getAllUsers();
    }

    private boolean userCanBorrow(User user){
        if(!borrowedBooksCount.containsKey(user)){
            return true;
        }
        return user.getBookLimit() > borrowedBooksCount.get(user);
    }

    private void setBorrowedBooksCount(User user){
        if(borrowedBooksCount.containsKey(user)){
            borrowedBooksCount.replace(user, borrowedBooksCount.get(user) + 1);
        }
        else{
            borrowedBooksCount.put(user, 1);
        }
    }

    private boolean bookIsBorrowed(Book book){
        return borrowedBooks.containsKey(book);
    }

}
