package vija.accenture.demoZ2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vija.accenture.demoZ2.model.Book;

public interface FriendRepository extends JpaRepository<Book, Long> {
}