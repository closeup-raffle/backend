package farmSystem.zerozeronbbang.domains.user.repository;

import farmSystem.zerozeronbbang.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository()
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByEmailAndPassword(String email, String passwd);
}
