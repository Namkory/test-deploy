package sharework.logintask.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import sharework.logintask.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createNewUser(UserDto request);
    Page<UserDto> getAllUsers(int page, int size);
}
