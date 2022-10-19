package vija.accenture.demoZ2.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "phoneNr", length = 50, nullable = false, unique = true)
    private int phoneNr;

    @ManyToMany(mappedBy = "friends", cascade = CascadeType.ALL)
    // @JoinColumn(name = "book_id")
    private Set<Book> books = new HashSet<>();

    public Friend(String name, int phoneNr) {
        this.name = name;
        this.phoneNr = phoneNr;
    }
}
