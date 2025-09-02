package com.andrewexe.controllers.console;

import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class LibraryConsoleCommand implements MenuCommand {

    LibraryService service;
    Scanner scanner;

    public LibraryConsoleCommand(LibraryService service, Scanner scanner){
        this.service = service;
        this.scanner = scanner;
    }


    @Override
    public String getName() {
        return "";
    }

    @Override
    public Object execute() {
        return new Object();
    }
}
