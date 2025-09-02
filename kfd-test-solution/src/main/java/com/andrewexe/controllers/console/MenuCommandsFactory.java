package com.andrewexe.controllers.console;

import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

public class MenuCommandsFactory {
    public static LibraryMenuCommand listUsers(LibraryService service){
        return new LibraryMenuCommand(service) {
            @Override
            public Object executeCommand() {
                return null;
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public void execute() {
                for(User user: service.getUsers()){
                    System.out.println(user.toString());
                }
            }
        };
    }
}
