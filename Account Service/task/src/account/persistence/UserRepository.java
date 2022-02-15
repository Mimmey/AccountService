package account.persistence;

import account.business.entities.dbentities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User user);
    Optional<User> findByEmailIgnoreCase(String email);
    List<User> findAllByOrderByIdAsc();
    Optional<User> findByEmail(String email);
    User deleteByEmail(String email);
}
