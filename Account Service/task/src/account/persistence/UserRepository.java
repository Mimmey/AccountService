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
    List<User> findAllOrderByIdAsc();
    boolean existsUser();
    Optional<User> findById(long id);
    void deleteById(long id);
}
