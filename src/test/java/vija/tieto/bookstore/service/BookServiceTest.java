package vija.tieto.bookstore.service;

import vija.tieto.bookstore.exception.BookAlreadyExistsException;
import vija.tieto.bookstore.model.Book;
import vija.tieto.bookstore.model.Cover;
import vija.tieto.bookstore.model.Genre;
import vija.tieto.bookstore.repository.BookRepository;
import vija.tieto.bookstore.service.impl.BookServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class BookServiceImplTest {

    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void saveBook() throws BookAlreadyExistsException {
        // Given
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1925, 4, 10),
                new BigDecimal("10.99"), Genre.ROMANCE_NOVELS, Cover.HARD_COVER);
        when(bookRepository.findByTitle("The Great Gatsby")).thenReturn(Optional.empty());
        when(bookRepository.save(book)).thenReturn(book);

        // When
        Book savedBook = bookService.saveBook(book);

        // Then
        assertNotNull(savedBook);
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getPublicationDate(), savedBook.getPublicationDate());
        assertEquals(book.getPrice(), savedBook.getPrice());
        assertEquals(book.getGenre(), savedBook.getGenre());
        assertEquals(book.getCover(), savedBook.getCover());

        verify(bookRepository, times(1)).findByTitle("The Great Gatsby");
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void saveBook_bookAlreadyExists() {
        // Given
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1925, 4, 10),
                new BigDecimal("10.99"), Genre.ROMANCE_NOVELS, Cover.HARD_COVER);
        when(bookRepository.findByTitle("The Great Gatsby")).thenReturn(Optional.of(book));

        // When
        assertThrows(BookAlreadyExistsException.class, () -> bookService.saveBook(book));

        // Then
        verify(bookRepository, times(1)).findByTitle("The Great Gatsby");
        verify(bookRepository, never()).save(book);
    }

    @Test
    void findBookByTitle() {
        // Given
        String title = "The Great Gatsby";
        Book book = new Book(title, "F. Scott Fitzgerald", LocalDate.of(1925, 4, 10),
                new BigDecimal("10.99"), Genre.ROMANCE_NOVELS, Cover.HARD_COVER);
        when(bookRepository.findByTitle(title)).thenReturn(Optional.of(book));

        // When
        Optional<Book> foundBook = bookService.findBookByTitle(title);

        // Then
        assertTrue(foundBook.isPresent());
        assertEquals(title, foundBook.get().getTitle());

        verify(bookRepository, times(1)).findByTitle(title);
    }

    @Test
    void findBookByTitle_bookNotFound() {
        // Given
        String title = "The Great Gatsby";
        when(bookRepository.findByTitle(title)).thenReturn(Optional.empty());

        // When
        Optional<Book> foundBook = bookService.findBookByTitle(title);

        // Then
        assertFalse(foundBook.isPresent());

        verify(bookRepository, times(1)).findByTitle(title);
    }

    @Test
    void getAllBooks() {
        // Given
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1925, 4, 10),
                new BigDecimal("10.99"), Genre.ROMANCE_NOVELS, Cover.HARD_COVER);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", LocalDate.of(1960, 7, 11),
                new BigDecimal("12.99"), Genre.FANTASY_NOVELS, Cover.SOFT_COVER);
        when(bookRepository.findAllByOrderByPublicationDateDesc()).thenReturn(Arrays.asList(book2, book1));

        // When
        List<Book> books = bookService.getAllBooks();

        // Then
        assertNotNull(books);
        assertEquals(2, books.size());

        Book firstBook = books.get(0);
        assertEquals(book2.getTitle(), firstBook.getTitle());
        assertEquals(book2.getAuthor(), firstBook.getAuthor());
        assertEquals(book2.getPublicationDate(), firstBook.getPublicationDate());
        assertEquals(book2.getPrice(), firstBook.getPrice());
        assertEquals(book2.getGenre(), firstBook.getGenre());
        assertEquals(book2.getCover(), firstBook.getCover());

        Book secondBook = books.get(1);
        assertEquals(book1.getTitle(), secondBook.getTitle());
        assertEquals(book1.getAuthor(), secondBook.getAuthor());
        assertEquals(book1.getPublicationDate(), secondBook.getPublicationDate());
        assertEquals(book1.getPrice(), secondBook.getPrice());
        assertEquals(book1.getGenre(), secondBook.getGenre());
        assertEquals(book1.getCover(), secondBook.getCover());

        verify(bookRepository, times(1)).findAllByOrderByPublicationDateDesc();
    }

    @Test
    void getAllBooks_noBooksFound() {
        // Given
        when(bookRepository.findAllByOrderByPublicationDateDesc()).thenReturn(Arrays.asList());

        // When
        List<Book> books = bookService.getAllBooks();

        // Then
        assertNotNull(books);
        assertEquals(0, books.size());

        verify(bookRepository, times(1)).findAllByOrderByPublicationDateDesc();
    }

    @Test
    void addPriceToBook() {
        // Given
        Long bookId = 1L;
        BigDecimal newPrice = new BigDecimal("9.99");
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1925, 4, 10),
                new BigDecimal("10.99"), Genre.ROMANCE_NOVELS, Cover.HARD_COVER);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        // When
        bookService.addPriceToBook(bookId, newPrice);

        // Then
        assertEquals(newPrice, book.getPrice());
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void addPriceToBook_bookNotFound() {
        // Given
        Long bookId = 1L;
        BigDecimal newPrice = new BigDecimal("9.99");
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> bookService.addPriceToBook(bookId, newPrice));
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, never()).save(any(Book.class));
    }

}
