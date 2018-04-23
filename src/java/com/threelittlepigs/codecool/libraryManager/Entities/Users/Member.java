package com.threelittlepigs.codecool.libraryManager.Entities.Users;

import com.threelittlepigs.codecool.libraryManager.Entities.Book;
import com.threelittlepigs.codecool.libraryManager.Entities.Fine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Member")
public class Member extends User {

    @OneToMany(mappedBy = "rentedByMember")
    List<Book> rentedBooks = new ArrayList<>();

    @OneToMany(mappedBy = "reservedByMember")
    List<Book> reservedBooks = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Fine> fines = new ArrayList<>();

    public Member(String userName, String password, String firstName, String lastName, String email, String dateOfBirth, String address, String phoneNumber){
        super(userName, password, firstName, lastName, email, dateOfBirth, address, phoneNumber);
    }

    public Member() {
    }

    void reserveBook(Book book){
        book.setReservedBy(this);
    }

}
