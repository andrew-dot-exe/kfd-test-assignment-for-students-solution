package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.Book;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class ReturnBookCommand extends AbstractMenuCommand{

    public ReturnBookCommand(Scanner scanner, LibraryService service) {
        super("Return a book", scanner, service);
    }

    @Override
    public void execute() {
        Book borrowedBook = listAndSelectBooks(service.getBorrowedBooks());
        service.returnBook(borrowedBook);
        System.out.println("Book has been returned.");
    }
}
