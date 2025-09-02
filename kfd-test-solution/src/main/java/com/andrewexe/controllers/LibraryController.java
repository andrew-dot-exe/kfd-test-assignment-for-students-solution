package com.andrewexe.controllers;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.List;
import java.util.Scanner;

public class LibraryController {
    private LibraryService service;
    private Scanner sc;
    private User selectedUser;

    public void listAll(List<Object> list){
         for (Object item: list){
             System.out.println(item.toString());
         }

    }

    public void selectUser(){


    }

    public void borrowBook(){

    }

}
