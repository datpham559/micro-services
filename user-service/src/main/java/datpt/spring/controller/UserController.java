package datpt.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("user/hello")
    public ResponseEntity<String> getHelloWorld(){
        return new ResponseEntity<>("hihi", HttpStatus.OK);
    }
}
