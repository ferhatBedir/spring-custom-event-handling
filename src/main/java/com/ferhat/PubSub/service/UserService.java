package com.ferhat.pubsub.service;


import com.ferhat.pubsub.entity.Department;
import com.ferhat.pubsub.entity.User;
import com.ferhat.pubsub.model.UserModel;
import com.ferhat.pubsub.publisher.UserEvent;
import com.ferhat.pubsub.repository.DepartmentRepository;
import com.ferhat.pubsub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private ApplicationEventPublisher publisher;
    private SmartValidator smartValidator;
    private UserRepository userRepository;
    private DepartmentService departmentService;

    @Autowired
    public UserService(ApplicationEventPublisher publisher,
                       SmartValidator smartValidator,
                       UserRepository userRepository,
                       DepartmentService departmentService) {
        this.publisher = publisher;
        this.smartValidator = smartValidator;
        this.userRepository = userRepository;
        this.departmentService = departmentService;
    }


    public void addUserList(List<User> newUserList, HttpServletResponse httpServletResponse) {
        newUserList.forEach(user -> {
            User temp = userRepository.findByUserId(user.getUserId());
            if (temp == null) {
                try {
                    checkData(user, httpServletResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addDepartment(user);
            } else {
                System.out.println("NewUser already register to Database.");
            }
        });
    }

    private void addDepartment(User user) {
        Department department = departmentService.getDepartmentByDepartmentId(user.getDepartmentId());
        department.setUser(user);
        departmentService.editDepartment(department);
    }

    public void editUser(User user) {
        User editUser = userRepository.findByUserId(user.getUserId());
        if (editUser != null) {
            userRepository.save(user);
            System.out.println("User updated.");
            UserEvent userEvent = new UserEvent(this, user);
            publisher.publishEvent(userEvent);
        } else {
            System.out.println("User not found.");
        }
    }

    public User getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public List<User> getUserByUserName(String userName) {
        return userRepository.findOneByUserFirstNameIgnoreCase(userName);
    }

    public List<UserModel> getUserModelByUserName(String userName) {
        List<User> users = userRepository.findOneByUserFirstNameIgnoreCase(userName);
        if (users == null || users.isEmpty()) {
            throw new NullPointerException("User List is null or empty.");
        }
        List<UserModel> userModels = new ArrayList<>();
        users.forEach(user -> userModels.add(convertToModel(user)));
        return userModels;
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

    public void deleteAllUser() {
        userRepository.deleteAll();
        System.out.println("All users deleted.");
    }

    private UserModel convertToModel(User entity) {
        UserModel model = new UserModel();
        model.setUserFirstName(entity.getUserFirstName());
        model.setUserLastName(entity.getUserLastName());
        model.setUserEmail(entity.getUserEmail());
        return model;
    }

    private void checkData(User user, HttpServletResponse httpServletResponse) throws IOException {
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