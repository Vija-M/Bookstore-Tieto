package vija.tieto.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "title", length = 50, nullable = false)
    private String title;


    @Column(name = "author", length = 50, nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 25)
    private Genre genre;

    @Column(name = "pages", length = 4)
    private int pages;

    @Enumerated(EnumType.STRING)
    @Column(name = "cover", length = 15)
    private Cover cover;

    @Column(name = "shelf", length = 5, nullable = false)
    private String shelf;


    public Book(String title, String author, Genre genre, int pages, Cover cover, String shelf) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.cover = cover;
        this.shelf = shelf;
    }

}
