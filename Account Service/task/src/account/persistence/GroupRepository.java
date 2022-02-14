package account.persistence;

import account.business.entities.dbentities.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    Optional<Group> findByName(String name);
    List<Group> findAll();
}
