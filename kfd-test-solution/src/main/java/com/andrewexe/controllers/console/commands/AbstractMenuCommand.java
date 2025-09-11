package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractMenuCommand implements MenuCommand{
    protected final String title;
    protected Scanner scanner;
    protected LibraryService service;

    public AbstractMenuCommand(String title, Scanner scanner, LibraryService service){
        this.title = title;
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public String getTitle(){
        return this.title;
    }


    protected String processInput(String prompt){
        System.out.println(prompt);
        return scanner.next();
    }

    protected Book returnBook(){
        return null;
    }

    protected User returnUser(){
        return null;
    }

    protected void listUser(List<User> users){
        if (users.isEmpty()){
            System.out.println("No data");
            return;
        }
        for(User user: users){
            System.out.println(user.toString());
        }
    }

    protected void listBooks(List<Book> books){
        if (books.isEmpty()){
            System.out.println("No data");
            return;
        }
        for(Book book: books){
            System.out.println(book.toString());
        }
    }

    protected Book listAndSelectBooks(List<Book> books){
        int index = 0;
        if (books.isEmpty()){
            System.out.println("No data");
            return null;
        }
        for(Book book: books){
            System.out.printf("%d. %s%n", index, book.toString());
            index += 1;
        }
        System.out.println("Enter the index of book");
        try{
            int selection = scanner.nextInt();
            return books.get(selection);
        }
        catch (Exception e){
            System.out.println("Index not found");
            return listAndSelectBooks(books);
        }
    }

    protected User listAndSelectUser(List<User> users){
        int index = 0;
        if (users.isEmpty()){
            System.out.println("No data");
            return null;
        }
        for(User user: users){
            System.out.printf("%d. %s%n", index, user.toString());
            index += 1;
        }
        System.out.println("Enter the index of user");
        try{
            int selection = scanner.nextInt();
            return users.get(selection);
        }
        catch (Exception e){
            System.out.println("Index not found");
            return listAndSelectUser(users);
        }
    }
}
