package vija.tieto.bookstore;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vija.tieto.bookstore.model.Book;
import vija.tieto.bookstore.model.Cover;
import vija.tieto.bookstore.model.Genre;
import vija.tieto.bookstore.model.User;
import vija.tieto.bookstore.service.BookService;
import vija.tieto.bookstore.service.UserService;

@Configuration
public class MyConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initialCreate() {
        return (args) -> {
            User adminUser = new User();
            adminUser.setLogin("admin");
            adminUser.setPassword(passwordEncoder().encode("adminPassword"));
            adminUser.setAdmin(true);
            userService.saveUser(adminUser);

            User regularUser = new User();
            regularUser.setLogin("user");
            regularUser.setPassword(passwordEncoder().encode("userPassword"));
            regularUser.setAdmin(false);
            userService.saveUser(regularUser);

            Book book1 = new Book("Maija un Paija", "John Smith", LocalDate.of(2017, 1, 13), BigDecimal.valueOf(26.67), Genre.FAIRY_TALE, Cover.HARD_COVER);
            bookService.saveBook(book1);

            Book book2 = new Book("The Lord of the Rings", "J.R.R. Tolkien", LocalDate.of(1954, 7, 29), BigDecimal.valueOf(39.99), Genre.FANTASY_NOVELS, Cover.SOFT_COVER);
            bookService.saveBook(book2);

            Book book3 = new Book("1984", "George Orwell", LocalDate.of(1949, 6, 8), BigDecimal.valueOf(12.99), Genre.FANTASY_NOVELS, Cover.SOFT_COVER);
            bookService.saveBook(book3);

            Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", LocalDate.of(1960, 7, 11), BigDecimal.valueOf(15.99), Genre.FAIRY_TALE, Cover.HARD_COVER);
            bookService.saveBook(book4);

            Book book5 = new Book("The Catcher in the Rye", "J.D. Salinger", LocalDate.of(1951, 7, 16), BigDecimal.valueOf(10.99), Genre.HISTORICAL_FICTION, Cover.SOFT_COVER);
            bookService.saveBook(book5);

            Book book6 = new Book("The Great Gatsby", "F. Scott Fitzgerald", LocalDate.of(1925, 4, 10), Genre.ROMANCE_NOVELS, Cover.HARD_COVER);
            bookService.saveBook(book6);

            Book book7 = new Book("Pride and Prejudice", "Jane Austen", LocalDate.of(1813, 1, 28), Genre.ROMANCE_NOVELS, Cover.SOFT_COVER);
            bookService.saveBook(book7);

            Book book8 = new Book("To the Lighthouse", "Virginia Woolf", LocalDate.of(1927, 5, 5), BigDecimal.valueOf(14.99), Genre.HISTORICAL_FICTION, Cover.SOFT_COVER);
            bookService.saveBook(book8);

            Book book9 = new Book("The Adventures of Huckleberry Finn", "Mark Twain", LocalDate.of(1884, 12, 10), BigDecimal.valueOf(6.99), Genre.THRILLER, Cover.HARD_COVER);
            bookService.saveBook(book9);

            Book book10 = new Book("The Picture of Dorian Gray", "Oscar Wilde", LocalDate.of(1890, 6, 20), BigDecimal.valueOf(11.99), Genre.ROMANCE_NOVELS, Cover.SOFT_COVER);
            bookService.saveBook(book10);

            Book book11 = new Book("The Alchemist", "Paulo Coelho", LocalDate.of(1988, 4, 25), BigDecimal.valueOf(13.99), Genre.THRILLER, Cover.HARD_COVER);
            bookService.saveBook(book11);

            Book book12 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", LocalDate.of(1979, 10, 12), BigDecimal.valueOf(11.99), Genre.SCIENCE_FICTION, Cover.HARD_COVER);
            bookService.saveBook(book12);

            Book book13 = new Book("The Name of the Wind", "Patrick Rothfuss", LocalDate.of(2007, 3, 27), BigDecimal.valueOf(16.99), Genre.FANTASY_NOVELS, Cover.HARD_COVER);
            bookService.saveBook(book13);

            Book book14 = new Book("The Hunger Games", "Suzanne Collins", LocalDate.of(2008, 9, 14), Genre.SCIENCE_FICTION, Cover.SOFT_COVER);
            bookService.saveBook(book14);

            Book book15 = new Book("The Da Vinci Code", "Dan Brown", LocalDate.of(2003, 3, 18), BigDecimal.valueOf(9.99), Genre.THRILLER, Cover.SOFT_COVER);
            bookService.saveBook(book15);

            Book book16 = new Book("Gone Girl", "Gillian Flynn", LocalDate.of(2012, 6, 5), Genre.THRILLER, Cover.HARD_COVER);
            bookService.saveBook(book16);

            Book book17 = new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", LocalDate.of(2005, 8, 23), BigDecimal.valueOf(10.99), Genre.THRILLER, Cover.SOFT_COVER);
            bookService.saveBook(book17);

            Book book18 = new Book("A Game of Thrones", "George R.R. Martin", LocalDate.of(1996, 8, 6), BigDecimal.valueOf(18.99), Genre.FANTASY_NOVELS, Cover.HARD_COVER);
            bookService.saveBook(book18);
        };
    }
}
