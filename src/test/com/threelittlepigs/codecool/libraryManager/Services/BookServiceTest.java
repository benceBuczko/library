package com.threelittlepigs.codecool.libraryManager.Services;

import com.threelittlepigs.codecool.libraryManager.Enums.Genre;
import com.threelittlepigs.codecool.libraryManager.Enums.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BookServiceTest {
    static BookService bookS;

    @BeforeAll
    static void init() {
        bookS = new BookService();
    }

    @Test
    public void testGetBookById() {
        assertNotNull(bookS.getBookById(1));
    }

    @Test
    public void testGetBookByTitle() {
        //assertNotNull(bookS.getBookByTitle("Kis 1"));
    }

    @Test
    public void testChangeTitle() {
        bookS.getBookById(1).setTitle("newTitle");
        assertEquals("newTitle", bookS.getBookById(1).getTitle());
    }

    @Test
    public void testChangeAuthor() {
        bookS.getBookById(1).setAuthor("newAuthor");
        assertEquals("newAuthor", bookS.getBookById(1).getAuthor());
    }

    @Test
    public void testChangePicture() {
        bookS.getBookById(1).setPicture_url("newUrl");
        assertEquals("newUrl", bookS.getBookById(1).getPicture_url());
    }

    @Test
    public void testChangeYear() {
        bookS.getBookById(1).setYear(1000000);
        assertEquals(1000000, bookS.getBookById(1).getYear());
    }

    @Test
    public void testChangeDescription() {
        bookS.getBookById(1).setDescription("newDescription");
        assertEquals("newDescription", bookS.getBookById(1).getDescription());
    }

    @Test
    public void testChangePublisher() {
        bookS.getBookById(1).setPublisher("newPublisher");
        assertEquals("newPublisher", bookS.getBookById(1).getPublisher());
    }

    @Test
    public void testChangeGenre() {
        Genre oldGenre = bookS.getBookById(1).getGenre();
        Genre newGenre = Genre.Christian;
        if (oldGenre != newGenre) {
            bookS.getBookById(1).setGenre(newGenre);
        } else {
            newGenre = Genre.Educational;
            bookS.getBookById(1).setGenre(newGenre);
        }
        assertEquals(newGenre, bookS.getBookById(1).getGenre());
    }

    @Test
    public void testChangeLocation() {
        Location oldLoc = bookS.getBookById(1).getLocation();
        Location newLoc = Location.TERRARIUM;
        if (oldLoc != newLoc) {
            bookS.getBookById(1).setLocation(newLoc);
        } else {
            newLoc = Location.AQUARIUM;
            bookS.getBookById(1).setLocation(newLoc);
        }
        assertEquals(newLoc, bookS.getBookById(1).getLocation());
    }

    @Test
    public void testChangeIsbnNum() {
        bookS.getBookById(1).setIsbn("newIsbn");
        assertEquals("newIsbn", bookS.getBookById(1).getIsbn());
    }

    @Test
    public void testChangeAvailability() {
        Boolean newBool;
        Boolean oldBool;
        if (bookS.getBookById(1).isAvailable()) {
            oldBool = true;
            newBool = false;
            bookS.getBookById(1).setAvailability(newBool);
        } else {
            oldBool = false;
            newBool = true;
            bookS.getBookById(1).setAvailability(newBool);
        }
        assertNotEquals(oldBool, newBool);
    }

}
