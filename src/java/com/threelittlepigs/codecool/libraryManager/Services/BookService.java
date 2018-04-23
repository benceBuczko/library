package com.threelittlepigs.codecool.libraryManager.Services;

import com.threelittlepigs.codecool.libraryManager.Entities.Book;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.User;
import com.threelittlepigs.codecool.libraryManager.Enums.Genre;
import com.threelittlepigs.codecool.libraryManager.Enums.Location;
import com.threelittlepigs.codecool.libraryManager.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book getBookById(long id) {
        Book book = bookRepository.findOne(id);
        return book;
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitleOrderByIsbn(title);
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBookByIsbnOrderByIsbn(isbn);
    }

    public List<Book> getBooksByTitleOrAuthorIsContaining(String searchString){
        return bookRepository.getBooksByTitleIsContainingOrAuthorIsContaining(searchString, searchString);
    }

    public List<Book> getBooksByIsbnOrTitleIsContaining(String searchString) {
        return bookRepository.getBooksByIsbnContainingOrTitleContaining(searchString, searchString);
    }

    public void changeTitle(String title, int id) {
        Book book = getBookById(id);
        book.setTitle(title);
    }

    public void changeAuthor(String author, int id) {
        Book book = getBookById(id);
        book.setAuthor(author);
    }

    public void changePicture(String pic_url, int id) {
        Book book = getBookById(id);
        book.setPicture_url(pic_url);
    }

    public void changeYear(int year, int id) {
        Book book = getBookById(id);
        book.setYear(year);
    }

    public void changeDescription(String desc, int id) {
        Book book = getBookById(id);
        book.setDescription(desc);
    }

    public void changePublisher(String publisher, int id) {
        Book book = getBookById(id);
        book.setPublisher(publisher);
    }

    public void changeGenre(Genre genre, int id) {
        Book book = getBookById(id);
        book.setGenre(genre);
    }

    public void changeLocation(Location location, int id) {
        Book book = getBookById(id);
        book.setLocation(location);
    }

    public void changeIsbnNum(String isbn, int id) {
        Book book = getBookById(id);
        book.setIsbn(isbn);
    }

    public void changeAvailability(boolean isAvailable, int id) {
        Book book = getBookById(id);
        book.setAvailability(isAvailable);
    }

    public List<Book> getBookByRentedByMemberId(User user) {
        return bookRepository.getAllByReservedByMemberOrderByIsbn(user);
    }

    public List<Book> getBookByReservedByMemberId(User user) {
        return bookRepository.getAllByRentedByMemberOrderByIsbn(user);
    }

    public void updateBookInfo(Book book, String title, String author, String description, String isbn) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setIsbn(isbn);
        saveBook(book);
    }

    public void adminBookReturnCancelUpdate(Map<String, String> bookData) {
        String isbn = bookData.get("isbn");
        Book book = getBookByIsbn(isbn);
        if (bookData.get("cancel") != null) {
            book.setReservedBy(null);
        } else {
            book.setRentedBy(null);
        }
        book.setAvailability(true);
        saveBook(book);
    }

    public void adminRentBook(Map<String, String> bookData, UserService userService)  {
        String isbn = bookData.get("isbn");
        Book book = getBookByIsbn(isbn);
        long reservedUserId = book.getReservedBy().getId();
        User rentedUser = book.getRentedBy();
        if (rentedUser == null) {
            Member user = (Member) userService.getUserById((int) reservedUserId);
            book.setRentedBy(user);
            book.setReservedBy(null);
            book.setAvailability(true);
            saveBook(book);
        }
    }
}
