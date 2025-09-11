package com.andrewexe.services;

import com.andrewexe.entities.*;
import com.andrewexe.exceptions.UnableToBorrowException;
import com.andrewexe.repositories.BookRepository;
import com.andrewexe.repositories.UserRepository;

import java.io.File;
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


    private final String BOOK_REPO_FILENAME = "books.csv";
    private final String USER_REPO_FILENAME = "users.csv";

    public LibraryService() {
        this.userRepository = new UserRepository();
        this.bookRepository = new BookRepository();
        this.borrowedBooksCount = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
        this.borrowedBooksDates = new HashMap<>();
        bookRepository.loadData(null);
    }

    public void borrowBook(User user, Book book)
    {
        if(userCanBorrow(user)){
            throw new UnableToBorrowException("User has exceed his book borrow limit");
        }
        if(bookIsBorrowed(book)){
            throw new UnableToBorrowException("Book has been already borrowed.");
        }
        borrowedBooks.put(book, user);
        borrowedBooksDates.put(book, new Date(System.currentTimeMillis()));
        setBorrowedBooksCount(user);
    }

    public void returnBook(Book book){
        User user = borrowedBooks.get(book);
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

    public void addStudent(String fullname, String phoneNumber){
        this.userRepository.addUser(new UserStudent( 0, fullname, phoneNumber));
    }

    public void addGuest(String fullname, String phoneNumber){
        this.userRepository.addUser(new UserGuest( 0, fullname, phoneNumber));
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

    public Book getBookIsbn(Long isbn){
        return bookRepository.getBookIsbn(isbn);
    }

    public boolean userCanBorrow(User user){
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

    public boolean bookIsBorrowed(Book book){
        return borrowedBooks.containsKey(book);
    }


    public ArrayList<Book> searchBookAll(String phrase)
    {
        ArrayList<Book> result = new ArrayList<>();
        for(Book book: bookRepository.getAllBooks()){
            if (book.getIsbn().toString().contains(phrase)){
                result.add(book);
                continue;
            }
            if (book.getAuthor().contains(phrase)){
                result.add(book);
                continue;
            }
            if (book.getName().contains(phrase)){
                result.add(book);
            }
        }
        return result;
    }


    public ArrayList<Book> searchBookIsbn(String isbn)
    {
        ArrayList<Book> result = new ArrayList<>();
        for(Book book: bookRepository.getAllBooks()){
            if (book.getIsbn().toString().contains(isbn)){
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> searchBookAuthor(String author)
    {
        ArrayList<Book> result = new ArrayList<>();
        for(Book book: bookRepository.getAllBooks()){
            if (book.getAuthor().contains(author)){
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> searchBookTitle(String title)
    {
        ArrayList<Book> result = new ArrayList<>();
        for(Book book: bookRepository.getAllBooks()){
            if (book.getName().contains(title)){
                result.add(book);
            }
        }
        return result;
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

}
