package com.ferhat.pubsub.controller;

import com.ferhat.pubsub.entity.User;
import com.ferhat.pubsub.model.UserModel;
import com.ferhat.pubsub.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "User Controller", value = "User Controller Operations")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "This method, Users add to Database." +
            "\nTake List<User>, HttpServletResponse in Parameters." +
            "\nReturn void.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/addlist")
    public void addUserList(@RequestBody List<User> users, HttpServletResponse httpServletResponse) throws IOException {
        if (users == null || users.isEmpty()) {
            System.out.println("please check parameters.");
        } else {
            userService.addUserList(users, httpServletResponse);
        }
    }

    @ApiOperation(value = "This method, User edit and update in Database." +
            "\nTake User, BindingResult and HttpServletResponse in Parameters." +
            "\nReturn void.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/edit")
    public void editUser(@RequestBody @Valid User user, BindingResult bindingResult, HttpServletResponse httpServletResponse) throws IOException {
        if (bindingResult.hasErrors()) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            userService.editUser(user);
        }
    }

    @ApiOperation(value = "This method, User see with UserId." +
            "\nTake URL Parameters.UserId must be in URL Parameters." +
            "\nReturn User.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getuserid")
    public User getUserByUserId(@RequestParam(value = "id") String userId) {
        return userService.getUserById(userId);
    }

    @ApiOperation(value = "This method, Users see with UserName." +
            "\nTake URL Parameters. UserName must be in URL parameters." +
            "\nReturn List<User>.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getusername")
    public List<User> getUserByUserName(@RequestParam(value = "name") String name) {
        return userService.getUserByUserName(name);
    }

    @ApiOperation(value = "This method, UserModels see with UserName." +
            "\nTake URL Parameters. UserName must be in URL Parameters." +
            "\nReturn List<UserModel>.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getmodelusername")
    public List<UserModel> getUserModelByUserName(@RequestParam(value = "name") String userName) {
        return userService.getUserModelByUserName(userName);
    }

    @ApiOperation(value = "This method, All Users see." +
            "\nTake no Parameters." +
            "\nReturn List<User>.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getall")
    public List<User> getAllUser() {
        return userService.getAllusers();
    }

    @ApiOperation(value = "This method, User delete with UserId." +
            "\nTake URL Parameters. UserId must be in Parameters." +
            "\nReturn void.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/deleteuserid")
    public void deleteUserByUserId(@RequestParam(value = "id") String userId) {
        userService.deleteUserByUserId(userId);
    }

    @ApiOperation(value = "This method, All Users delete in Database." +
            "\nTake no Parameters." +
            "\nReturn void.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/deleteall")
    public void deleteAllUsers() {
        userService.deleteAllUser();
    }
}
