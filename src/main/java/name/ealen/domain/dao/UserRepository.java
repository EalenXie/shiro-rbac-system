package name.ealen.domain.dao;

import name.ealen.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EalenXie on 2019/3/25 15:11.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
