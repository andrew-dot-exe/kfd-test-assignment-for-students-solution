package com.andrewexe.services;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;
import com.andrewexe.entities.UserFaculty;
import com.andrewexe.repositories.BookRepository;
import com.andrewexe.repositories.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LibraryService {

    private UserRepository userRepository;
    private BookRepository bookRepository;

    private HashMap<User, Integer> borrowedBooksCount;
    private HashMap<Book, User> borrowedBooks; // поскольку книга у нас может быть только в единственном экземпляре,
                                               // то мы можем использовать ее как ключ
    private HashMap<Book, Date> borrowedBooksDates;

    public LibraryService() {
        this.userRepository = new UserRepository();
        this.bookRepository = new BookRepository();
        this.borrowedBooksCount = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
        this.borrowedBooksDates = new HashMap<>();
    }

    public void borrowBook(User user, Book book)
    {
        if(bookIsBorrowed(book) || !userCanBorrow(user)){
            return;
        }
        borrowedBooks.put(book, user);
        borrowedBooksDates.put(book, new Date(System.currentTimeMillis()));
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

    public void addBook(Long isbn, String name, String author, int pageCount){
        Book book = new Book(isbn, name, author, pageCount);
        this.bookRepository.addBook(book);
    }

    public void addFaculty(String fullname, String phoneNumber){
        this.userRepository.addUser(new UserFaculty( 0, fullname, phoneNumber));
    }

    public HashMap<Book, User> getExpiredBooks() {
        HashMap<Book, User> expiredBooks = new HashMap<>();
        LocalDate currentDate = LocalDate.now();

        for (Book book : borrowedBooks.keySet()) {
            Date borrowDate = borrowedBooksDates.get(book);
            LocalDate borrowLocalDate = borrowDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            long daysBetween = ChronoUnit.DAYS.between(borrowLocalDate, currentDate);
            User owner = borrowedBooks.get(book);
            if (daysBetween > owner.getBorrowDayLimit()) {
                expiredBooks.put(book, owner);
            }
        }
        return expiredBooks;
    }

    public List<User> getUsers(){
        return userRepository.getAllUsers();
    }

    public User getUser(int id){
        return userRepository.getUser(id);
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
