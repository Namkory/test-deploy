package sharework.logintask.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sharework.logintask.dto.UserDto;
import sharework.logintask.entity.Users;
import sharework.logintask.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    @Override
    public UserDto createNewUser(UserDto request) {
        Users userEntiry = new Users();
        userEntiry.setName(request.getName());
        userEntiry.setAge(request.getAge());
        userEntiry.setEmail(request.getEmail());

        // Hash the password before saving to the database
        BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();
        String hashedPassword = hashPassword.encode(request.getPassword());
        userEntiry.setPassword(hashedPassword);

        userEntiry.setAddress(request.getAddress());

        userRepo.save(userEntiry);

        UserDto response = new UserDto();
        response.setId(userEntiry.getId());
        response.setName(request.getName());
        response.setAge(request.getAge());
        response.setEmail(request.getEmail());
        response.setAddress(request.getAddress());

        return response;
    }

//    @Override
//    public List<UserDto> getAllUsers() {
//        List<Users> listUserEntity = userRepo.findAll();
//        List<UserDto> listUserDto = new ArrayList<>();
//        for(int i = 0; i < listUserEntity.size(); i++) {
//            Users userEntity = listUserEntity.get(i);
//            UserDto userDto = new UserDto();
//            userDto.setId(userEntity.getId());
//            userDto.setName(userEntity.getName());
//            userDto.setAge(userEntity.getAge());
//            userDto.setEmail(userEntity.getEmail());
//            userDto.setAddress(userEntity.getAddress());
//            listUserDto.add(userDto);
//        }
//
//        return listUserDto;
//    }

    @Override
    public Page<UserDto> getAllUsers(int page, int size) {
        // Tạo một đối tượng Pageable để chỉ định trang và kích thước trang
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng phương thức findAll của repository với Pageable để trả về kết quả phân trang
        Page<Users> usersPage = userRepo.findAll(pageable);

        // Chuyển đổi trang kết quả sang đối tượng Page của UserDto
        Page<UserDto> userDtoPage = usersPage.map(userEntity -> {
            UserDto userDto = new UserDto();
            userDto.setId(userEntity.getId());
            userDto.setName(userEntity.getName());
            userDto.setAge(userEntity.getAge());
            userDto.setEmail(userEntity.getEmail());
            userDto.setAddress(userEntity.getAddress());
            return userDto;
        });

        return userDtoPage;
    }

}
