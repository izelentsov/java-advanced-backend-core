package com.epam.jmp.rest.controller.v1;


import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.rest.dto.UserListResponse;
import com.epam.jmp.rest.dto.UserResponse;
import com.epam.jmp.rest.entity.User;
import com.epam.jmp.rest.model.UserQuery.FindAllUsersQuery;
import com.epam.jmp.rest.model.UserQuery.FindUserQuery;
import com.epam.jmp.rest.model.UserQuery.FindUsersByLocationQuery;
import com.epam.jmp.rest.service.UserException;
import com.epam.jmp.rest.service.UserQueryService;



@RestController
@RequestMapping("/v1/user")
public class UserQueryController {


    private final UserQueryService userService;


    public UserQueryController(UserQueryService userService) {
        this.userService = userService;
    }



    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) throws UserException {
        User user = userService.findUser(new FindUserQuery(id));
        UserResponse response = new UserResponse(user, Instant.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserListResponse> getUsers(
            @RequestParam(value = "location", required = false) String location) throws UserException {
        List<User> users = (location != null)
                ? userService.findUsersByLocation(new FindUsersByLocationQuery(location))
                : userService.findAllUsers(new FindAllUsersQuery());
        UserListResponse response = new UserListResponse(users, Instant.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
