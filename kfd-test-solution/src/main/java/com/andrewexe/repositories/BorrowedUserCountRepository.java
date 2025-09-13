package com.andrewexe.repositories;

import com.andrewexe.entities.User;

import java.io.File;
import java.util.HashMap;

public class BorrowedUserCountRepository implements FileStoredRepository{
    private final HashMap<User, Integer> borrowedBooksCount;

    public BorrowedUserCountRepository() {
        this.borrowedBooksCount = new HashMap<>();
    }

    public void put(User user){
        if(borrowedBooksCount.containsKey(user)){
            incrementReturnCount(user);
        }
        else {
            borrowedBooksCount.put(user, 1);
        }
    }

    public int GetUserCount(User user){
        if(borrowedBooksCount.containsKey(user)){
            return borrowedBooksCount.get(user);
        }
        return 0;
    }

    public boolean canUserBorrow(User user){
        if(!borrowedBooksCount.containsKey(user)){
            return true;
        }
        return borrowedBooksCount.get(user) < user.getBookLimit();
    }

    public void decrementReturnCount(User user){
        int count = borrowedBooksCount.get(user);
        borrowedBooksCount.replace(user, count - 1);
    }

    private void incrementReturnCount(User user){
        int count = borrowedBooksCount.get(user);
        borrowedBooksCount.replace(user, count + 1);
    }

    @Override
    public void loadFromCsv(String filename) {

    }

    @Override
    public File saveToCsv(String filename) {
        return null;
    }
}
