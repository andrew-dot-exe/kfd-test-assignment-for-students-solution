package com.andrewexe.controllers.console;

import com.andrewexe.services.LibraryService;

public class ServiceSingleton {
    private LibraryService _service;

    public LibraryService getService(){
        if(_service == null){
            _service = new LibraryService();
        }
        return _service;
    }
}
