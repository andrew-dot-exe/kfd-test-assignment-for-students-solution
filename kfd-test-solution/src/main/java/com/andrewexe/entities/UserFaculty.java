package com.andrewexe.entities;

public class UserFaculty extends User {

    public UserFaculty( int id, String fullname, String phoneNumber) {
        super( id, fullname, phoneNumber);
        this.userType = UserType.FACULTY;
    }

    @Override
    public int getBookLimit() {
        return 10;
    }

    @Override
    public int getBorrowDayLimit() {
        return 30;
    }
}
