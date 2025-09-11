package com.andrewexe.controllers.console.commands;

import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class AddBookCommand extends AbstractMenuCommand{

    public AddBookCommand(Scanner scanner, LibraryService service) {
        super("Add a book", scanner, service);
    }

    @Override
    public void execute() {
        System.out.println("Enter isbn");
        Long isbn = scanner.nextLong();

        System.out.println("Enter author name");
        String author = scanner.next();

        System.out.println("Enter author name");
        String name = scanner.next();

        System.out.println("Enter page count");
        int pageCnt = scanner.nextInt();

        if(service.getBookIsbn(isbn) != null){
            System.out.println("Book already in library.");
            return;
        }

        service.addBook(isbn, name, author, pageCnt);

    }
}
