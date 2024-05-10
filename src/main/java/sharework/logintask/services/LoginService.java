package sharework.logintask.services;

import sharework.logintask.dto.LoginRequest;
import sharework.logintask.dto.LoginResponse;

public interface LoginService {
    LoginResponse handleLogin(LoginRequest request);
}
