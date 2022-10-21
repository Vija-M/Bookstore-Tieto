package vija.accenture.demoZ2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vija.accenture.demoZ2.model.Book;
import vija.accenture.demoZ2.model.Friend;
import vija.accenture.demoZ2.repository.FriendRepository;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public List<Friend> findAllFriends() {
        return friendRepository.findAll();
    }

    public Friend findFriendById(Long id) {
        Friend friend = friendRepository.findById(id).orElseThrow(() -> new RuntimeException("Friend id is wrong"));
        return friend;
    }

    public void createBook(Friend friend) {
        friendRepository.save(friend);
    }

    public void updateFriend(Friend friend) {
        friendRepository.save(friend);
    }

    public void deleteFriend(Long id) {
        Friend friend = friendRepository.findById(id).orElseThrow(() -> new RuntimeException("Friend id is wrong"));
        friendRepository.deleteById(friend.getId());
    }
}
