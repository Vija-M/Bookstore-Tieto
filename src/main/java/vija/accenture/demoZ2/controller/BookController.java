package vija.accenture.demoZ2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vija.accenture.demoZ2.model.Book;
import vija.accenture.demoZ2.service.BookService;
import vija.accenture.demoZ2.service.FriendService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final FriendService friendService;

    @Autowired
    public BookController(BookService bookService, FriendService friendService) {
        this.bookService = bookService;
        this.friendService = friendService;
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

