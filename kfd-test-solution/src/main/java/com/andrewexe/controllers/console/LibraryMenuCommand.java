package com.andrewexe.controllers.console;

import com.andrewexe.services.LibraryService;

// уже напрямую работает с сервисом
public abstract class LibraryMenuCommand implements MenuItem{
    protected LibraryService service;

    public LibraryMenuCommand(LibraryService service){
        this.service = service;
    }

    public abstract Object executeCommand();
}
