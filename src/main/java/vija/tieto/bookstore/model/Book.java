package vija.tieto.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, length = 50, nullable = false)
    private String title;

    @Column(name = "author", length = 50, nullable = false)
    private String author;

    @Column(name = "added_at", updatable = false)
    private LocalDate publicationDate;

    @Column(name = "price", length = 10)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 25)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "cover", length = 15)
    private Cover cover;


    public Book(String title, String author, LocalDate publicationDate, BigDecimal price, Genre genre, Cover cover) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.price = price;
        this.genre = genre;
        this.cover = cover;

    }

}
