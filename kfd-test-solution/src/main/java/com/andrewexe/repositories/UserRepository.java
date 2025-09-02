package com.andrewexe.repositories;

import com.andrewexe.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private String repositoryFilename = null;
    private Map<Integer, User> users;

    public UserRepository(){
        users = new HashMap<Integer, User>();
    }

    public void loadData(String filename){
        repositoryFilename = filename;
        if(repositoryFilename == null){
            //in-memory
            addUser(new UserStudent(1, "Ivan Ivanov", "+799923320"));
            addUser(new UserStudent(2, "Sergej Ivanov", "+799923321"));
            addUser(new UserFaculty(3, "Andrej Sidorov", "+799923322"));
            addUser(new UserFaculty(4, "Eugene Petrov", "+799923323"));
            addUser(new UserGuest(5, "Ian Petrov", "+799923324"));

        }
    }

    public void addUser(User user) throws IllegalArgumentException{
        if(user.getUserId() != getNextId()){
            throw new IllegalArgumentException("User id not last for user repository.");
        }
        users.put(user.getUserId(), user);
    }

    public void editUser(User newUser){
        users.replace(newUser.getUserId(), newUser);
    }

    public void removeUser(User user) throws IllegalArgumentException{
        users.remove(user.getUserId());
    }

    public List<User> getAllUsers(){
        return new ArrayList<User>(users.values());
    }

    public User getUser(int id){
        return users.get(id);
    }

    public void save(){
        if(repositoryFilename != null){
            System.out.println("saving data to file");
        }
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
}


