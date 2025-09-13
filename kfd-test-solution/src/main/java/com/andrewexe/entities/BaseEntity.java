package com.andrewexe.entities;

public abstract class BaseEntity {
    protected int id;

    abstract String getInfo();

    public BaseEntity(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
