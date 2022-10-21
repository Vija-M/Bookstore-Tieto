package vija.accenture.demoZ2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vija.accenture.demoZ2.model.Friend;
import vija.accenture.demoZ2.service.FriendService;

import java.util.List;
@Controller
public class FriendController {

    @Autowired
    private final FriendService friendService;

    public FriendController (FriendService friendService){
        this.friendService = friendService;
    }

        @RequestMapping("/friends")
        public String findAllFriends(Model model) {
            List<Friend> friends = friendService.findAllFriends();
            model.addAttribute("friends", friends);
            return "list-friends";
        }


    @RequestMapping("/friend/{id}")
    public String findFriendById(@PathVariable("id") Long id, Model model) {
        final Friend friend = friendService.findFriendById(id);
        model.addAttribute("friend", friend);
        return "list-friend";
    }

    @GetMapping("/addFriend")
    public String showCreateForm(Friend friend) {
        return "add-friend";
    }

    @RequestMapping("/add-friend")
    public String createFriend(Friend friend, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-friend";
        }
        friendService.createFriend(friend);
        model.addAttribute("friend", friendService.findAllFriends());
        return "redirect:/friends";
    }

    @GetMapping("/updateFriend/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Friend friend = friendService.findFriendById(id);
        model.addAttribute("friend", friend);
        return "update-friend";
    }

    @RequestMapping("/update-friend/{id}")
    public String updateFriend(@PathVariable("id") Long id, Friend friend, BindingResult result, Model model) {
        if (result.hasErrors()) {
            friend.setId(id);
            return "update-friend";
        }
        friendService.updateFriend(friend);
        model.addAttribute("friend", friendService.findAllFriends());
        return "redirect:/friends";
    }

    @RequestMapping("/remove-friend/{id}")
    public String deleteFriend(@PathVariable("id") Long id, Model model) {
        friendService.deleteFriend(id);
        model.addAttribute("friend", friendService.findAllFriends());
        return "redirect:/friends";
    }

}
