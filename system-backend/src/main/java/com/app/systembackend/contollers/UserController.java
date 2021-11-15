package com.app.systembackend.contollers;


import com.app.systembackend.model.User;
import com.app.systembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/registration")
    public User addUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public User getUserById(@PathVariable int id) {

        return userService.getUserById(id);
    }


}
