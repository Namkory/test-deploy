package sharework.logintask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharework.logintask.entity.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
}
