package com.andrewexe.controllers;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.List;
import java.util.Scanner;

public class LibraryController {
    private LibraryService service;
    private Scanner sc;
    private User selectedUser;

    public void listAllBooks(){
        List<Book> books = service.getAllBooks();
        for(Book book: books){
            System.out.println(book.toString());
        }
    }

    public void listAllUsers(){
        for(User user: service.getUsers()){
            System.out.println(user.toString());
        }
    }

    public void selectUser(){
        listAllUsers();
        int id = sc.nextInt();

    }

    public void borrowBook(){

    }

}
