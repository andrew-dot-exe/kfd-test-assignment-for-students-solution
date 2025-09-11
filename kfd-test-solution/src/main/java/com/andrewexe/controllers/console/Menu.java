package com.andrewexe.controllers.console;

import com.andrewexe.controllers.console.commands.MenuCommand;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuCommand {
    private String title;
    private List<MenuCommand> items = new ArrayList<>();
    private Scanner scanner;

    public Menu(String title, Scanner scanner) {
        this.title = title;
        this.scanner = scanner;
    }

    public void addItem(MenuCommand item) {
        items.add(item);
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("\n=== " + title + " ===");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getTitle());
            }
            System.out.println("0. Back");
            System.out.print("Choose: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 0) {
                    break;
                }

                if (choice > 0 && choice <= items.size()) {
                    items.get(choice - 1).execute();
                } else {
                    System.out.println("Not found");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter the number");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    @Override
    public String getTitle() {
        return "";
    }


    @Override
    public String toString() {
        return title;
    }
}