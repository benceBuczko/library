package com.threelittlepigs.codecool.libraryManager.Controllers.Implementations;

import com.threelittlepigs.codecool.libraryManager.Entities.Book;
import com.threelittlepigs.codecool.libraryManager.Entities.Fine;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.Librarian;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.User;
import com.threelittlepigs.codecool.libraryManager.Services.BookService;
import com.threelittlepigs.codecool.libraryManager.Services.FineService;
import com.threelittlepigs.codecool.libraryManager.Services.UserService;
import com.threelittlepigs.codecool.libraryManager.Utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Controller
@Scope("session")
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private FineService fineService;

    @Autowired
    JSONUtil jsonUtil;

    private User currentUser = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderBooks(Model model) {
        List<Book> books = bookService.findAll();
        List<Book> booksToRender = new ArrayList<>();
        String title = null;
        for(Book book : books){
            if (book.getTitle().equals(title)) continue;
            title = book.getTitle();
            booksToRender.add(book);
        }
        model.addAttribute("books", booksToRender);
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        if (currentUser != null && currentUser instanceof Librarian) {
            List<Fine> fines = fineService.findAll();
            model.addAttribute("fines", fines);
            model.addAttribute("books", books);
            return "indexAdmin";
        }
        model.addAttribute("books", booksToRender);
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String renderSearch(HttpServletRequest request, Model model){
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        if (currentUser != null && currentUser instanceof Librarian) {
            List<Book> books = bookService.getBooksByIsbnOrTitleIsContaining(request.getParameter("search"));
            model.addAttribute("books", books);
            return "indexAdmin";
        } else {
            List<Book> books = bookService.getBooksByTitleOrAuthorIsContaining(request.getParameter("search"));
            List<Book> booksToRender = getBooksToRender(books);
            model.addAttribute("books", booksToRender);
            return "index";
        }
    }

    private List<Book> getBooksToRender(List<Book> books) {
        List<Book> booksToRender = new ArrayList<>();
        String title = null;
        for(Book book : books){
            if (book.getTitle().equals(title)) continue;
            title = book.getTitle();
            booksToRender.add(book);
        }
        return booksToRender;
    }

    @RequestMapping(value = "/books/{title}", method = RequestMethod.GET)
    public String renderBook(@PathVariable("title") String title, Model model) {
        List<Book> books = bookService.getBooksByTitle(title);
        model.addAttribute("librarian", currentUser instanceof Librarian);
        model.addAttribute("books", books);
        model.addAttribute("admin", currentUser instanceof Librarian);
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        return "book";
    }

    @RequestMapping(value = "/reserve/{isbn}", method = RequestMethod.GET)
    public String reserveBook(@PathVariable("isbn") String isbn, Model model){
        Book book = bookService.getBookByIsbn(isbn);
        book.setReservedBy((Member)currentUser);
        book.setAvailability(false);
        bookService.saveBook(book);
        return "redirect:/books/" + book.getTitle();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Map<String, String> logData, ModelMap model, HttpServletResponse response) {
        Map<String, String> error = new HashMap<>();
        currentUser = userService.loginUser(logData, error);
        if (succeededToCreateUser(model, response)) return jsonUtil.toJson(userService.generateUserData(currentUser));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return jsonUtil.toJson(error);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        currentUser = null;
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String registration(@RequestBody Map<String, String> regData, ModelMap model, HttpServletResponse response) {
        Map<String, String> error = new HashMap<>();
        currentUser = userService.registrateMember(regData, error);
        if (succeededToCreateUser(model, response)) return jsonUtil.toJson(userService.generateUserData(currentUser));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return jsonUtil.toJson(error);
    }

    private boolean succeededToCreateUser(ModelMap model, HttpServletResponse response) {
        if (currentUser != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            model.put("userName", currentUser.getUserName());
            model.put("id", currentUser.getId());
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/userprofile/{id}", method = RequestMethod.GET)
    public String renderUserInfo(@PathVariable("id") String id, Model model) {
        if (!String.valueOf(currentUser != null ? currentUser.getId() : 0).equals(id)) {
            return "redirect:/";
        }
        System.out.println(currentUser.getPicture());
        List<Book> rentedBooks = bookService.getBookByRentedByMemberId(currentUser);
        List<Book> reservedBooks = bookService.getBookByReservedByMemberId(currentUser);
        List<Fine> fines = fineService.getFinesByMemberId(Long.valueOf(id), true);
        model.addAttribute("user", currentUser);
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        model.addAttribute("rentedBooks", rentedBooks);
        model.addAttribute("reservedBooks", reservedBooks);
        model.addAttribute("fines", fines);
        return "userinfo";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestBody Map<String, String> fileData) {
        System.out.println(fileData);
        currentUser.setPicture(fileData.get("url"));
        userService.saveUser(currentUser);
        return jsonUtil.toJson(currentUser.getPicture());
    }

    @RequestMapping(value = "/editbook", method = RequestMethod.POST)
    public String renderEditBookInfo(@RequestParam  Map<String, String> bookData ,Model model) {
        Book book = bookService.getBookById(Long.parseLong(bookData.get("bookId")));
        model.addAttribute("books", book);
        return "editbook";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String saveBookEdit(@RequestParam  Map<String, String> bookData) {
        String formerIsbn = bookData.get("formerIsbn");
        String title = bookData.get("title");
        String author = bookData.get("author");
        String description = bookData.get("description");
        Book book = bookService.getBookByIsbn(formerIsbn);
        String isbn = bookData.get("newIsbn");
        bookService.updateBookInfo(book, title, author, description, isbn);
        return "redirect:adminBookView/" + isbn;
    }

    @RequestMapping(value = "/adminBookView/{isbn}", method = RequestMethod.GET)
    public String renderEditBookInfo(@PathVariable  String isbn ,Model model) {
        if (!(currentUser instanceof Librarian)) {
            return "redirect:/";
        }
        Book book = bookService.getBookByIsbn(isbn);
        User userRentedBy = book.getRentedBy();
        User userReservedBy = book.getReservedBy();
        model.addAttribute("book", book);
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        model.addAttribute("reservedByName", userReservedBy != null ? userReservedBy.getUserName() : null);
        model.addAttribute("rentedByName", userRentedBy != null ? userRentedBy.getUserName() : null);
        model.addAttribute("reservedById", userReservedBy != null ? userReservedBy.getId() : null);
        model.addAttribute("rentedById", userRentedBy != null ? userRentedBy.getId() : null);
        return "adminbookview";
    }

    @RequestMapping(value = "/rentBook", method = RequestMethod.POST)
    @ResponseBody
    public String rentBook(@RequestBody Map<String, String> bookData) {
        bookService.adminRentBook(bookData, userService);
        return "Success";
    }

    @RequestMapping(value={"/cancelReservation", "/returnBook"}, method = RequestMethod.POST)
    @ResponseBody
    public String cancelReservation(@RequestBody Map<String, String> bookData) {
        bookService.adminBookReturnCancelUpdate(bookData);
        return "Success";
    }


    @RequestMapping("/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("statusCode", "404");
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        return "error";
    }

    @RequestMapping("/500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(Model model) {
        model.addAttribute("statusCode", "500");
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        return "error";
    }

    @RequestMapping("/405")
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String methodNotAllowed(Model model) {
        model.addAttribute("statusCode", "405");
        model.addAttribute("user_id", currentUser != null ? currentUser.getId() : 0 );
        model.addAttribute("userName", currentUser != null ? currentUser.getUserName() : "");
        return "error";
    }

    @RequestMapping(value="/pay/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String payment(@PathVariable("id") String id, ModelMap model, HttpServletResponse response){
        Fine fine = fineService.getFineById((parseInt(id)));
        fine.setStatus(false);
        fineService.saveFine(fine);
        return jsonUtil.toJson("success");
    }
}
