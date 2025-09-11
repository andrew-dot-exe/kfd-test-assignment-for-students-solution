package com.andrewexe.controllers.console.commands;

import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class ListBorrowedBooksCommand extends AbstractMenuCommand{

    public ListBorrowedBooksCommand(Scanner scanner, LibraryService service) {
        super("List borrowed books", scanner, service);
    }

    @Override
    public void execute() {
        listBooks(service.getBorrowedBooks());
    }
}
