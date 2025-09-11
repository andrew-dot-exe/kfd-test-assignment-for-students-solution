package com.andrewexe.controllers.console.commands;

import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class AddUserCommand extends AbstractMenuCommand{

    public AddUserCommand( Scanner scanner, LibraryService service) {
        super("Add a user", scanner, service);
    }

    @Override
    public void execute() {
        System.out.println("Enter a name");
        String name = scanner.next();
        System.out.println("Enter a phone");
        String phoneNumber = scanner.next();
        System.out.println("Enter the type of user: [g]uest, [s]tudent, [f]aculty");
        char type = scanner.next().toLowerCase().charAt(0);
        switch (type){
            case 'g':
                service.addGuest(name, phoneNumber);
                break;
            case 's':
                service.addStudent(name, phoneNumber);
                break;
            case 'f':
                service.addFaculty(name, phoneNumber);
        }
    }
}
