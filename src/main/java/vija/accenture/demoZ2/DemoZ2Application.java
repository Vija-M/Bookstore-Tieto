package vija.accenture.demoZ2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vija.accenture.demoZ2.model.Book;
import vija.accenture.demoZ2.model.Cover;
import vija.accenture.demoZ2.model.Friend;
import vija.accenture.demoZ2.model.Genre;
import vija.accenture.demoZ2.service.BookService;

@SpringBootApplication
public class DemoZ2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoZ2Application.class, args);
    }

    @Bean
    public CommandLineRunner initialCreate(BookService bookService) {
        return (args) -> {
            Book book1 = new Book("Maija un Paija", "John Smith", Genre.FAIRY_TALE, 48, Cover.HARD_COVER, "1A");
            Friend friend1 = new Friend("Anna", 24434869);
            book1.addFriends(friend1);
            bookService.createBook(book1);

            Book book2 = new Book("Mumu", "Addy Brain", Genre.FANTASY_NOVELS, 204, Cover.HARD_COVER, "2A");
            Friend friend2 = new Friend("Uldis", 25534869);
            book2.addFriends(friend2);
            bookService.createBook(book2);

            Book book3 = new Book("World War 2", "Ann Craft", Genre.HISTORICAL_FICTION, 118, Cover.SOFT_COVER, "1A");
            Friend friend3 = new Friend("Sandis", 26634869);
            book3.addFriends(friend3);
            bookService.createBook(book3);

            Book book4 = new Book("World War 1", "Ann Craft", Genre.HISTORICAL_FICTION, 246, Cover.SOFT_COVER, "1A");
            Friend friend4 = new Friend("Ieva", 26657689);
            book4.addFriends(friend4);
            bookService.createBook(book4);

            Book book5 = new Book("World War 3", "Ann Craft", Genre.FANTASY_NOVELS, 346, Cover.SOFT_COVER, "1A");
            book5.addFriends(friend4);
            bookService.createBook(book4);

        };
    }
}


