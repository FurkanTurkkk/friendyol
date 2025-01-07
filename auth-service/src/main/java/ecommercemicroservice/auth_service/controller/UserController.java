package ecommercemicroservice.auth_service.controller;

import ecommercemicroservice.auth_service.model.User;
import ecommercemicroservice.auth_service.request.RequestForCreateUser;
import ecommercemicroservice.auth_service.service.UserService;
import org.example.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody RequestForCreateUser request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable("email")String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }
}
