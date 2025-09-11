package com.andrewexe.controllers.console.commands;

import com.andrewexe.entities.Book;
import com.andrewexe.entities.User;
import com.andrewexe.services.LibraryService;

import java.util.Scanner;

public class BorrowBookCommand extends AbstractMenuCommand{

    public BorrowBookCommand(Scanner scanner, LibraryService service) {
        super("Borrow a book", scanner, service);
    }

    @Override
    public void execute() {
        ListSelectBookCommand selBook = new ListSelectBookCommand(this.scanner, this.service);
        ListSelectUserCommand selUser = new ListSelectUserCommand(this.scanner, this.service);
        Book book = selBook.returnBook();
        User user = selUser.returnUser();
        confirmInput(user, book);
    }

    private void confirmInput(User user, Book book) {
        if(!service.userCanBorrow(user)){
            System.out.println("This user cannot borrow the book, he must return one.");
            return;
        }
        else if(service.bookIsBorrowed(book)){
            System.out.println("Book has been already borrowed.");
            return;
        }
        System.out.println(String.format("User %s borrowing book %s by for %d days. Confirm? [y,n]",
                user.getFullname(),
                book.getName(),
                user.getBorrowDayLimit()));
        char confirm = scanner.next().charAt(0);
        switch (confirm){
            case 'y':
                service.borrowBook(user, book);
                break;
            case 'n':
                System.out.println("Aborting");
            default:
                System.out.println("Input not valid");
                confirmInput(user, book);
        }
    }
}
