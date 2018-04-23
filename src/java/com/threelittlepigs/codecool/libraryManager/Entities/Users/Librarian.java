package com.threelittlepigs.codecool.libraryManager.Entities.Users;

import com.threelittlepigs.codecool.libraryManager.Entities.Book;
import com.threelittlepigs.codecool.libraryManager.Entities.Fine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Librarian")
public class Librarian extends User {

    public Librarian(String userName, String password, String firstName, String lastName, String email, String dateOfBirth, String address, String phoneNumber){
        super(userName,password,firstName,lastName, email, dateOfBirth, address, phoneNumber);
    }

    public Librarian() {
    }

    public void addBook(Book book){
    }

    public void removeBook(Book book){
    }

    public void rentBook(Book book, Member member){
        book.setRentedBy(member);
        book.setAvailability(false);
    }

    public void returnBook(Book book){
        book.setRentedBy(null);
        book.setAvailability(true); // redundant, we could calculate this from setRentedBy Not null value
    }

    public void payFine(Fine fine){
        fine.setStatus(false);
    }
}
