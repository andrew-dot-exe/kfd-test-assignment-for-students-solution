package com.andrewexe;


import com.andrewexe.controllers.LibraryController;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        LibraryController controller = new LibraryController(libraryService, new Scanner(System.in));
        controller.run();
    }
}