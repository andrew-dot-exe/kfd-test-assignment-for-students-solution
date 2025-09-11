package com.andrewexe.controllers;

import com.andrewexe.controllers.console.commands.*;
import com.andrewexe.controllers.console.Menu;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class LibraryController {
    private LibraryService service;
    private Scanner sc;

    public LibraryController(LibraryService service, Scanner sc) {
        this.service = service;
        this.sc = sc;
    }

    public void run(){
        Menu mainMenu = new Menu("Main menu", sc);
        mainMenu.addItem(new AddUserCommand(sc, service));
        mainMenu.addItem(new AddBookCommand(sc, service));
        mainMenu.addItem(new ListSelectBookCommand(sc, service));
        mainMenu.addItem(new ListSelectUserCommand(sc, service));
        mainMenu.addItem(new ListBorrowedBooksCommand(sc, service));
        mainMenu.addItem(new ListOverdueBooksCommand(sc, service));
        mainMenu.addItem(new BorrowBookCommand(sc, service));
        mainMenu.addItem(new ListSelectUserCommand(sc, service));
        mainMenu.addItem(new ReturnBookCommand(sc, service));
        mainMenu.execute();
    }
}
