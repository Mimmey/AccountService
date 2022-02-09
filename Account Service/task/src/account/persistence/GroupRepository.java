package account.persistence;

import account.business.entities.dbentities.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository {
    Optional<Group> findByName(String name);
}
