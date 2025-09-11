package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.Book;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class SearchSelectBookCommand extends AbstractMenuCommand{
    public SearchSelectBookCommand(Scanner scanner, LibraryService service) {
        super("Search a book", scanner, service);
    }

    @Override
    public void execute() {
        System.out.println("Enter the phrase to search a book");
        String phrase = scanner.next();
        listBooks(service.searchBookAll(phrase));
    }

    @Override
    protected Book returnBook() {
        System.out.println("Enter the phrase to search a book");
        String phrase = scanner.next();
        return listAndSelectBooks(service.searchBookAll(phrase));
    }
}
