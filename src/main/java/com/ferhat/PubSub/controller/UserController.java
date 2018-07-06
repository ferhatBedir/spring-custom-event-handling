package com.ferhat.PubSub.controller;

import com.ferhat.PubSub.entity.User;
import com.ferhat.PubSub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addlist")
    public void addUserList(@RequestBody List<User> users, HttpServletResponse httpServletResponse) throws IOException {
        userService.addUserList(users, httpServletResponse);
    }

    @PostMapping("/edit")
    public void editUser(@RequestBody @Valid User user, BindingResult bindingResult, HttpServletResponse httpServletResponse) throws IOException {
        if (bindingResult.hasErrors()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            userService.editUser(user);
        }
    }

    @GetMapping("/getuserid")
    public User getUserByUserId(@RequestParam(value = "id") String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/getusername")
    public List<User> getUserByUserName(@RequestParam(value = "name") String name) {
        return userService.getUserByUserName(name);
    }

    @GetMapping("/getall")
    public List<User> getAllUser() {
        return userService.getAllusers();
    }

    @DeleteMapping("/deleteuserid")
    public void deleteUserByUserId(@RequestParam(value = "id") String userId) {
        userService.deleteUserByUserId(userId);
    }
}
