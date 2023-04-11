package vija.tieto.bookstore.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vija.tieto.bookstore.exception.BookAlreadyExistsException;
import vija.tieto.bookstore.exception.NotFoundException;
import vija.tieto.bookstore.model.Book;
import vija.tieto.bookstore.repository.BookRepository;
import vija.tieto.bookstore.service.BookService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) throws BookAlreadyExistsException {
        Optional<Book> existingBook = findBookByTitle(book.getTitle());
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("Book with this title already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByPublicationDateDesc();
    }

    @Override
    public void addPriceToBook(Long bookId, BigDecimal price) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + bookId));
        book.setPrice(price);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getId());
    }

}