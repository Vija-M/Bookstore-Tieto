package vija.tieto.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vija.tieto.bookstore.exception.BookAlreadyExistsException;
import vija.tieto.bookstore.model.Book;
import vija.tieto.bookstore.service.BookService;
import vija.tieto.bookstore.service.UserService;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private static final int PAGE_SIZE = 50;
    private UserService userService;

        public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllBooks(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        List<Book> books = bookService.getAllBooks();

        int totalPages = (int) Math.ceil((double) books.size() / PAGE_SIZE);

        int startIndex = (page - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, books.size());

        List<Book> pageBooks = books.subList(startIndex, endIndex);

        model.addAttribute("books", pageBooks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "index";
    }


    @GetMapping("/addBook")
    public String addBookForm(Model model, Principal principal) {
        if (!userService.isAdmin(principal)) {
            return "redirect:/books";
        }
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if (!userService.isAdmin(principal)) {
            return "redirect:/books";
        }
        try {
            bookService.saveBook(book);
        } catch (BookAlreadyExistsException e) {
            bindingResult.rejectValue("name", "error.book", e.getMessage());
            return "books/add";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Book added successfully");
        return "redirect:/books";
    }
}


/*
@Controller
public class BookController {
    private final BookService bookService;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String findAllBooks(Model model) {
        final List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "list-books";
    }

    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        final List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "list-book";
    }

    @GetMapping("/add")
    public String showCreateForm(Book book, Model model) {
        model.addAttribute("friends", friendService.findAllFriends());
        return "add-book";
    }

    @RequestMapping("/add-book")
    public String createBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "update-book";
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }
        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @RequestMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.deleteBook(id);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }
}
*/
