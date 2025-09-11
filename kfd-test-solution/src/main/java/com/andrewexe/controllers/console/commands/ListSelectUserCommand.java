package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class ListSelectUserCommand extends AbstractMenuCommand{
    public ListSelectUserCommand(Scanner scanner, LibraryService service) {
        super("List users", scanner, service);
    }


    @Override
    public void execute() {
        // just list the users, no prompt
        this.listUser(service.getUsers());
    }

    @Override
    protected User returnUser() {
        this.execute();
        System.out.println("Enter user id");
        int userId = scanner.nextInt();
        return service.getUser(userId);
    }
}
