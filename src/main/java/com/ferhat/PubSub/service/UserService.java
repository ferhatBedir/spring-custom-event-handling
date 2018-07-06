package com.ferhat.PubSub.service;


import com.ferhat.PubSub.entity.User;
import com.ferhat.PubSub.publisher.UserEvent;
import com.ferhat.PubSub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {


    private ApplicationEventPublisher publisher;
    private SmartValidator smartValidator;
    private UserRepository userRepository;

    @Autowired
    public UserService(ApplicationEventPublisher publisher, SmartValidator smartValidator, UserRepository userRepository) {
        this.publisher = publisher;
        this.smartValidator = smartValidator;
        this.userRepository = userRepository;
    }


    public void addUserList(List<User> newUserList, HttpServletResponse httpServletResponse) throws IOException {
        if (newUserList == null) {
            System.out.println("User list is empty.");
        } else {
            for (User user : newUserList) {
                User newUser = userRepository.findByUserId(user.getUserId());
                if (newUser == null) {
                    checkData(user, httpServletResponse);
                } else {
                    System.out.println("NewUser already register to Database.");
                }
            }
        }
    }

    public void editUser(User user) {
        User editUser = userRepository.findByUserId(user.getUserId());
        if (editUser != null) {
            userRepository.save(user);
            System.out.println("User updated.");
            UserEvent userEvent = new UserEvent(this, user);
            publisher.publishEvent(userEvent);
        } else {
            System.out.println("User already register to Database.");
        }
    }

    public User getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public List<User> getUserByUserName(String userName) {
        return userRepository.findOneByUserFirstNameIgnoreCase(userName);
    }

    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    public void deleteUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            userRepository.delete(user);
        } else {
            System.out.println("User not found in Database.");
        }
    }

    public void checkData(User user, HttpServletResponse httpServletResponse) throws IOException {
        DataBinder binder = new DataBinder(user);
        binder.setValidator(smartValidator);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        if (bindingResult.hasErrors()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please check parameters.");
        } else {
            userRepository.save(user);
            System.out.println("User registered to Database.");
        }

    }
}
