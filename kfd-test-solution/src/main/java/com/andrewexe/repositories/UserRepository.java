package com.andrewexe.repositories;

import com.andrewexe.entities.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository implements FileStoredRepository{

    private final Map<Integer, User> users;

    public UserRepository(){
        users = new HashMap<>();
    }


    public void addUser(User user) throws IllegalArgumentException{
        if(user.getId() != getNextId()){
            throw new IllegalArgumentException("User id not last for user repository.");
        }
        users.put(user.getId(), user);
    }


    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public User getUser(int id){
        return users.get(id);
    }

    private int getNextId(){
        int max = 0;
        for(int i : users.keySet())
        {
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    @Override
    public void loadFromCsv(String filename) {

    }

    @Override
    public File saveToCsv(String filename) {
        File file = new File(filename);

        return null;
    }
}


