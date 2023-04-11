package vija.tieto.bookstore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vija.tieto.bookstore.exception.BookAlreadyExistsException;
import vija.tieto.bookstore.model.Book;
import vija.tieto.bookstore.model.Cover;
import vija.tieto.bookstore.model.Genre;
import vija.tieto.bookstore.service.BookService;
import vija.tieto.bookstore.service.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bookController = new BookController(bookService, userService);
    }

    @Test
    public void testGetAllBooks() {
        // arrange
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book 1", "Author 1", LocalDate.now(), BigDecimal.valueOf(20.00), Genre.ROMANCE_NOVELS, Cover.SOFT_COVER));
        books.add(new Book("Book 2", "Author 2", LocalDate.now(), BigDecimal.valueOf(25.00), Genre.SCIENCE_FICTION, Cover.HARD_COVER));
        when(bookService.getAllBooks()).thenReturn(books);

        // act
        String viewName = bookController.getAllBooks(1, model);

        // assert
        assertEquals("index", viewName);
        verify(model).addAttribute("books", books.subList(0, Math.min(books.size(), 50)));
        verify(model).addAttribute("currentPage", 1);
        verify(model).addAttribute("totalPages", 1);
    }

    @Test
    public void testAddBookForm() {
        // arrange
        Principal principal = mock(Principal.class);
        when(userService.isAdmin(principal)).thenReturn(true);

        // act
        String viewName = bookController.addBookForm(model, principal);

        // assert
        assertEquals("books/add", viewName);
        verify(model).addAttribute(eq("book"), any(Book.class));
    }

    @Test
    public void testAddBook() throws BookAlreadyExistsException {
        // arrange
        Principal principal = mock(Principal.class);
        when(userService.isAdmin(principal)).thenReturn(true);
        Book book = new Book("Book 1", "Author 1", LocalDate.now(), BigDecimal.valueOf(20.00), Genre.ROMANCE_NOVELS, Cover.SOFT_COVER);

        // act
        String viewName = bookController.addBook(book, bindingResult, redirectAttributes, principal);

        // assert
        assertEquals("redirect:/books", viewName);
        verify(bookService).saveBook(book);
        verify(redirectAttributes).addFlashAttribute(eq("successMessage"), any(String.class));
    }
}