package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.Book;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class ListSelectBookCommand extends AbstractMenuCommand{

    public ListSelectBookCommand(Scanner scanner, LibraryService service) {
        super("List books", scanner, service);
    }

    @Override
    public void execute() {
        this.listBooks(service.getAllBooks());
    }

    @Override
    protected Book returnBook(){
        execute();
        System.out.println("Enter the isbn");
        Long isbn = scanner.nextLong();
        return service.getBookIsbn(isbn);
    }
}
