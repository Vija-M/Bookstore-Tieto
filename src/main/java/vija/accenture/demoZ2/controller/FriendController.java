package vija.accenture.demoZ2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vija.accenture.demoZ2.model.Book;
import vija.accenture.demoZ2.model.Friend;
import vija.accenture.demoZ2.service.BookService;
import vija.accenture.demoZ2.service.FriendService;

import java.util.List;
@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;
        @Autowired
        private BookService bookService;



        @GetMapping("/friends")
        public String findAllFriends(Model model) {
            List<Friend> friends = friendService.findAllFriends();
            model.addAttribute("friends", friends);
            return "friends";
        }

        @GetMapping("/friend/{id}")
        public String findFriend(@PathVariable Long id, Model model) {
            Friend friend = friendService.findFriendById(id);
            model.addAttribute("friend", friend);
            return "list-friend";
        }

        @GetMapping("/delete-friend/{id}")
        public String deleteFriend(@PathVariable Long id, Model model) {
            Friend friend = friendService.findFriendById(id);
            model.addAttribute("friends", friendService.findAllFriends());
            return "friends";
        }

        @GetMapping("/update-friend/{id}")
        public String updateFriend(@PathVariable Long id, Model model) {
            Friend friend = friendService.findFriendById(id);
            model.addAttribute("books", bookService.findAllBooks());
            return "update-friend";
        }

        @PostMapping("/save-update-friend/{id}")
        public String updateFriend(@PathVariable Long id, Friend friend, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "update-friend";
            }
            friendService.updateFriend(friend);
            model.addAttribute("friends", friendService.findAllFriends());
            return "redirect:/friends";
        }

        @GetMapping("/add-friend")
        public String addFriend(Friend friend, Model model) {
            model.addAttribute("books", bookService.findAllBooks());
            return "add-friend";
        }

        @PostMapping("/save-friend")
        public String saveFriend(Friend friend, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "add-friend";
            }
            friendService.createFriend(friend);
            model.addAttribute("books", bookService.findAllBooks());
            return "redirect:/friends";
        }
    }


