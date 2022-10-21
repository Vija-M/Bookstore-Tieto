package vija.accenture.demoZ2.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vija.accenture.demoZ2.exception.NotFoundException;
import vija.accenture.demoZ2.model.Book;
import vija.accenture.demoZ2.model.Friend;
import vija.accenture.demoZ2.repository.FriendRepository;
import vija.accenture.demoZ2.service.FriendService;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Friend> findAllFriends() {
        return friendRepository.findAll();


    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Friend findFriendById(Long id) {
        return friendRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Friend not found with ID %d", id)));
    }

    @Override
    public void createFriend(Friend friend) {
        friendRepository.save(friend);
    }

    @Override
    public void updateFriend(Friend friend) {
        friendRepository.save(friend);
    }

    @Override
    public void deleteFriend(Long id) {
        final Friend author = friendRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Friend not found with ID %d", id)));

        friendRepository.deleteById(author.getId());
    }

}
