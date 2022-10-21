package vija.accenture.demoZ2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vija.accenture.demoZ2.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%" + " OR b.author LIKE %?1%" + " OR b.genre LIKE %?1%")
    public List<Book> search(String keyword);
}
