package com.andrewexe.controllers.console;

import com.andrewexe.services.LibraryService;

import java.util.*;

public class LibraryMenu implements MenuItem{

    private LinkedHashMap<Character, MenuItem> items;

    public LibraryMenu(){
        this.items = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void execute() {
        for(MenuItem item : items.values()){
            System.out.println(item.getName());
        }
    }

    public void addItem(char shortcut, MenuItem item){
        items.put(shortcut, item);
    }

    private void executeItem(){
        Scanner sc = new Scanner(System.in);
        char shortcut = sc.next().charAt(0);
        MenuItem item = items.get(shortcut);
        item.execute();
    }
}
