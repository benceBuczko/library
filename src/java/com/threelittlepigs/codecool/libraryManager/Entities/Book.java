package com.threelittlepigs.codecool.libraryManager.Entities;

import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.User;
import com.threelittlepigs.codecool.libraryManager.Enums.Genre;
import com.threelittlepigs.codecool.libraryManager.Enums.Location;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "picture")
    private String picture_url;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false, length = 4000)
    private String description;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    private Location location;

    @Column(nullable = false)
    private String isbn;

    private boolean isAvailable = true;

    @ManyToOne
    private User rentedByMember;

    @ManyToOne
    private User reservedByMember;

    @Transient
    final int daysForRent = 7;

    @Transient
    private Date currentDate = Calendar.getInstance().getTime();
    private Date dueDate = getDueDate();


    public Book() {
    }


    public Book(String title, String author, String picture_url, int year, String description, String publisher,
                Genre genre, Location location, String isbn) {
        this.title = title;
        this.author = author;
        this.picture_url = picture_url;
        this.year = year;
        this.description = description;
        this.publisher = publisher;
        this.genre = genre;
        this.location = location;
        this.isbn = isbn;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean available) {
        isAvailable = available;
    }

    public User getRentedBy() {
        return rentedByMember;
    }

    public void setRentedBy(Member rentedByMember) {
        this.rentedByMember = rentedByMember;
    }

    public User getReservedBy() {
        return reservedByMember;
    }

    public void setReservedBy(Member reservedByMember) {
        this.reservedByMember = reservedByMember    ;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public Date getDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        return calendar.getTime();
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
