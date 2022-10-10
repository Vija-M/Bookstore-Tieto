package vija.accenture.demoZ2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "friend", cascade = {CascadeType.MERGE})
    @JoinColumn(name = "book_id")
    private List<Book> books = new ArrayList<>();
}
