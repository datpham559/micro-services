package datpt.spring.controller;

import datpt.spring.entity.User;
import datpt.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloWorld(){
        return new ResponseEntity<>("hihi", HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User result = userService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
