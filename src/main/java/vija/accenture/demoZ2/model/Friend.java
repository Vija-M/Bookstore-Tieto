package vija.accenture.demoZ2.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
    private String phoneNr;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "friends")
    private Set<Book> books = new HashSet<>();

    public Friend(String name, String phoneNr) {
        this.name = name;
        this.phoneNr = phoneNr;
    }
}
