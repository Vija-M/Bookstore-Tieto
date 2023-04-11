package vija.tieto.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vija.tieto.bookstore.model.Book;
import vija.tieto.bookstore.model.Cover;
import vija.tieto.bookstore.model.Genre;
import vija.tieto.bookstore.service.BookService;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialCreate(BookService bookService) {
        return (args) -> {
            Book book1 = new Book("Maija un Paija", "John Smith", LocalDate.of(2017, 1, 13), BigDecimal.valueOf(26.67), Genre.FAIRY_TALE, Cover.HARD_COVER);
            bookService.saveBook(book1);

            Book book2 = new Book("Mumu", "Addy Brain", LocalDate.of(2022, 3, 4), BigDecimal.valueOf(120.83), Genre.FANTASY_NOVELS, Cover.HARD_COVER);
            bookService.saveBook(book2);

            Book book3 = new Book("World War 2", "Ann Craft",LocalDate.of(2021, 4, 23), BigDecimal.valueOf(6.23), Genre.HISTORICAL_FICTION, Cover.SOFT_COVER);
            bookService.saveBook(book3);

            Book book4 = new Book("World War 1", "Ann Craft", LocalDate.of(2011, 4, 23), BigDecimal.valueOf(15.23), Genre.HISTORICAL_FICTION, Cover.SOFT_COVER);
            bookService.saveBook(book4);

            Book book5 = new Book("World War 3", "Ann Craft", LocalDate.of(2023, 5, 23), BigDecimal.valueOf(21.23), Genre.FANTASY_NOVELS, Cover.SOFT_COVER);
            bookService.saveBook(book5);

            Book book6 = new Book("memories 3", "Alex Craft", LocalDate.of(2023, 5, 23), Genre.FANTASY_NOVELS, Cover.SOFT_COVER);
            bookService.saveBook(book6);

        };
    }
}


