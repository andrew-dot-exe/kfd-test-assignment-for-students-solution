package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.HashMap;
import java.util.Scanner;

public class ListOverdueBooksCommand extends AbstractMenuCommand{

    public ListOverdueBooksCommand(Scanner scanner, LibraryService service) {
        super("List overdue books", scanner, service);
    }

    @Override
    public void execute() {
        // will use custom display method
        HashMap<Book, User> overdue = service.getExpiredBooks();
        System.out.println("Book name\t|\tUser");
        for (Book book : overdue.keySet()){
            printOverdueString(book, overdue.get(book));
        }
    }

    private String printOverdueString(Book book, User user){
        return String.format("%s\t|\t%s", book.getName(), user.getFullname());
    }
}
