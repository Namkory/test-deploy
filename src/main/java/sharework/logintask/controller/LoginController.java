package sharework.logintask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharework.logintask.dto.LoginRequest;
import sharework.logintask.dto.LoginResponse;
import sharework.logintask.services.LoginService;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping()
    public ResponseEntity<LoginResponse> handleLogin(@RequestBody LoginRequest request){
        LoginResponse response = loginService.handleLogin(request);
        return ResponseEntity.ok(response);
    }

}
