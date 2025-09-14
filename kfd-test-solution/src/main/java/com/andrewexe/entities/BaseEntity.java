package com.andrewexe.entities;

public abstract class BaseEntity {
    protected int id;

    public BaseEntity(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
