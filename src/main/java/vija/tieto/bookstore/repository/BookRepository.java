package vija.tieto.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vija.tieto.bookstore.model.Book;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findAllByOrderByPublicationDateDesc();

    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%" + " OR b.author LIKE %?1%" + " OR b.genre LIKE %?1%")
     List<Book> search(String keyword);
}
