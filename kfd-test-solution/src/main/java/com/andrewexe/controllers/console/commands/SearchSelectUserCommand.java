package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class SearchSelectUserCommand extends AbstractMenuCommand{

    public SearchSelectUserCommand(Scanner scanner, LibraryService service) {
        super("Search a user", scanner, service);
    }

    @Override
    public void execute() {
        System.out.println("Print a phrase to find a user");
        String phrase = scanner.next();
        this.listUser(service.searchUser(phrase));
    }

    @Override
    protected User returnUser() {
        execute();
        System.out.println("Enter the id of user");
        int id = scanner.nextInt();
        return service.getUser(id);
    }
}
