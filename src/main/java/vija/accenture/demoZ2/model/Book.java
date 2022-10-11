package vija.accenture.demoZ2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

 // @Size(max=50)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

}
