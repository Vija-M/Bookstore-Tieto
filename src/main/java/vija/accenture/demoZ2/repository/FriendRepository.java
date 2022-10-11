package vija.accenture.demoZ2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vija.accenture.demoZ2.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}