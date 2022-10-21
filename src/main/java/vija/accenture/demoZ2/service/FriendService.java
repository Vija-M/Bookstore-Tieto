package vija.accenture.demoZ2.service;

import vija.accenture.demoZ2.model.Friend;

import java.util.List;

public interface FriendService {

    List<Friend> findAllFriends();

    Friend findFriendById(Long id);

    void createFriend(Friend friend);

    void updateFriend(Friend friend);

    void deleteFriend(Long id);

}
