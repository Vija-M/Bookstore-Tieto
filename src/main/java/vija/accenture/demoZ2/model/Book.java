package vija.accenture.demoZ2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "books_friends",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")})
    private Set<Friend> friends = new HashSet<Friend>();

    public Book(String title, String author, Genre genre, int pages, Cover cover, String shelf) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.cover = cover;
        this.shelf = shelf;
    }

    public void removeFriends(Friend friend) {
        this.friends.remove(friend);
        friend.getBooks().remove(friend);

    }

    public void addFriends(Friend friend) {
        this.friends.add(friend);
        friend.getBooks().add(this);
    }
}
