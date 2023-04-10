package vija.tieto.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vija.tieto.bookstore.model.Book;
import vija.tieto.bookstore.model.Cover;
import vija.tieto.bookstore.model.Genre;
import vija.tieto.bookstore.service.BookService;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialCreate(BookService bookService) {
        return (args) -> {
            Book book1 = new Book("Maija un Paija", "John Smith", Genre.FAIRY_TALE, 48, Cover.HARD_COVER, "1A");
            bookService.createBook(book1);

            Book book2 = new Book("Mumu", "Addy Brain", Genre.FANTASY_NOVELS, 204, Cover.HARD_COVER, "2A");
            bookService.createBook(book2);

            Book book3 = new Book("World War 2", "Ann Craft", Genre.HISTORICAL_FICTION, 118, Cover.SOFT_COVER, "1A");
            bookService.createBook(book3);

            Book book4 = new Book("World War 1", "Ann Craft", Genre.HISTORICAL_FICTION, 246, Cover.SOFT_COVER, "1A");
            bookService.createBook(book4);

            Book book5 = new Book("World War 3", "Ann Craft", Genre.FANTASY_NOVELS, 346, Cover.SOFT_COVER, "1A");
            bookService.createBook(book4);

        };
    }
}


