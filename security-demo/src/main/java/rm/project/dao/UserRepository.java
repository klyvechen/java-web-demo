package rm.project.dao;

import rm.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 會員DAO
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String email);

    List<User> findByPhone(String phone);
}
