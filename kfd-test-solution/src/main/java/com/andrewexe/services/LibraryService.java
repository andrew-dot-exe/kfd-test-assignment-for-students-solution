package com.andrewexe.services;

import com.andrewexe.entities.*;
import com.andrewexe.exceptions.UnableToBorrowException;
import com.andrewexe.repositories.*;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LibraryService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowedBooksRepository borrowedBooksRepository;
    private final BorrowedDatesRepository borrowedDatesRepository;

    private final BorrowedUserCountRepository borrowedUserCountRepository;


    public LibraryService() {
        this.userRepository = new UserRepository();
        this.bookRepository = new BookRepository();
        this.borrowedBooksRepository = new BorrowedBooksRepository();
        this.borrowedDatesRepository = new BorrowedDatesRepository();
        this.borrowedUserCountRepository = new BorrowedUserCountRepository();
    }

    public void borrowBook(User user, Book book)
    {
        if(borrowedUserCountRepository.canUserBorrow(user)){
            throw new UnableToBorrowException("User has exceed his book borrow limit");
        }
        if(borrowedBooksRepository.bookIsBorrowed(book)){
            throw new UnableToBorrowException("Book has been already borrowed.");
        }
        borrowedBooksRepository.put(book, user);
        borrowedDatesRepository.put(book, new Date(System.currentTimeMillis()));
        borrowedUserCountRepository.put(user);
    }

    public void returnBook(Book book){
        User user = borrowedBooksRepository.get(book);
        borrowedUserCountRepository.decrementReturnCount(user);
        borrowedBooksRepository.remove(book);
        borrowedDatesRepository.remove(book);
    }

    public List<Book> getBorrowedBooks(){
        return borrowedBooksRepository.getBorrowedBooksList();
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }


    // TODO: сделать обертку
    public List<Book> getFreeBooks(){
        List<Book> all = getAllBooks();
        all.remove(getBorrowedBooks());
        return all;
    }

    public void addBook(Long isbn, String name, String author, int pageCount){
        Book book = new Book(0, isbn, name, author, pageCount);
        this.bookRepository.addBook(book);
    }

    public void addFaculty(String fullname, String phoneNumber){
        this.userRepository.addUser(new UserFaculty( 0, fullname, phoneNumber));
    }

    public void addStudent(String fullname, String phoneNumber){
        this.userRepository.addUser(new UserStudent( 0, fullname, phoneNumber));
    }

    public void addGuest(String fullname, String phoneNumber){
        this.userRepository.addUser(new UserGuest( 0, fullname, phoneNumber));
    }

    public HashMap<Book, User> getExpiredBooks() {
        HashMap<Book, User> expiredBooks = new HashMap<>();
        LocalDate currentDate = LocalDate.now();

        for (Book book : borrowedBooksRepository.getBorrowedBooks().keySet()) {
            Date borrowDate = borrowedDatesRepository.get(book);
            LocalDate borrowLocalDate = borrowDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            long daysBetween = ChronoUnit.DAYS.between(borrowLocalDate, currentDate);
            User owner = borrowedBooksRepository.get(book);

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


    public ArrayList<Book> searchBookAll(String phrase)
    {
        return bookRepository.findBook(phrase);
    }

     public ArrayList<User> searchUser(String phrase){
         ArrayList< User> result = new ArrayList<>();
         for(User user: userRepository.getAllUsers()){
             if(user.getFullname().contains(phrase)){
                 result.add(user);
                continue;
             }
             if(user.getPhoneNumber().contains(phrase)){
                 result.add(user);
             }
         }
         return result;
     }

     void save(String filename){
     }

}
