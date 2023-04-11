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
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "login", unique = true, length = 50, nullable = false)
        private String login;

        @Column(name = "password", length = 50, nullable = false)
        private String password;

        @Column(name = "is_admin", columnDefinition = "BIT", nullable = false)
        private boolean isAdmin;

}
