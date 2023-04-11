package vija.tieto.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    @NotBlank
    @Size(max = 20)
    @Column(name = "login", unique = true, length = 20, nullable = false)
    private String login;

    @NotBlank
    @Size(max = 60)
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "is_admin", columnDefinition = "BIT", nullable = false)
    private boolean isAdmin;

}
