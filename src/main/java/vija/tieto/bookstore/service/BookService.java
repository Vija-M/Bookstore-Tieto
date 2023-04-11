package vija.tieto.bookstore.service;

import vija.tieto.bookstore.exception.BookAlreadyExistsException;
import vija.tieto.bookstore.model.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Book saveBook(Book book) throws BookAlreadyExistsException;

    Optional<Book> findBookByTitle(String title);

    void addPriceToBook(Long bookId, BigDecimal price);

    List<Book> searchBooks(String keyword);

    Book findBookById(Long id);

    void updateBook(Book book);

    void deleteBook(Long id);

}