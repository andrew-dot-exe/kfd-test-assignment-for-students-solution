package com.andrewexe.controllers.console;

import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleCommandsFactory {

    public static LibraryConsoleCommand borrowBookCommand(LibraryService service, Scanner scanner){
        return new LibraryConsoleCommand(service, scanner){
            @Override
            public String getName() {
                return "Borrow a book";
            }

            @Override
            public Object execute() {
                return null;
            }
        };
    }


    public static LibraryConsoleCommand getAllUsers(LibraryService service, Scanner scanner){
        return new LibraryConsoleCommand(service, scanner){
            @Override
            public String getName() {
                return "List users";
            }

            @Override
            public Object execute() {
                ListUser(service);
                printUserMenu();

                return null;
            }
        };
    }

    private static void ListUser(LibraryService service){
        List<User> users = service.getUsers();
        for(User user : users){
            System.out.println(user.toString());
        }
    }

    private static void printUserMenu(){
        System.out.println("s: Select user");
        System.out.println("a: Add user");
        System.out.println("e: Edit user");
        System.out.println("r: Remove user");
        System.out.println("q: Quit");
    }
}
