package sharework.logintask.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sharework.logintask.dto.LoginRequest;
import sharework.logintask.dto.LoginResponse;
import sharework.logintask.entity.Users;
import sharework.logintask.exception.ResourceNotFoundException;
import sharework.logintask.repository.UserRepo;
import sharework.logintask.util.JwtUtil;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse handleLogin(LoginRequest request) {
        Users user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email does not exist"));

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResourceNotFoundException("Wrong password!");
        }

        String token =jwtUtil.createJwt(user.getEmail());

        LoginResponse response = new LoginResponse();
        response.setJwt(token);
        response.setMessage("Login is successful");

        return response;
    }
}
