package sharework.logintask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sharework.logintask.dto.UserDto;
import sharework.logintask.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto request){
         UserDto response = userService.createNewUser(request);
         return ResponseEntity.ok(response);
    }

//    @GetMapping()
//    public ResponseEntity<List<UserDto>> getAllUser(){
//        List<UserDto> response = userService.getAllUsers();
//        return ResponseEntity.ok(response);
//    }

    @GetMapping()
    public ResponseEntity<Page<UserDto>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Page<UserDto> usersPage = userService.getAllUsers(page, size);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }
}
