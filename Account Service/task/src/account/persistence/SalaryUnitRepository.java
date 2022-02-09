package account.persistence;

import account.business.entities.dbentities.SalaryUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryUnitRepository extends CrudRepository<SalaryUnit, Long> {
    SalaryUnit save(SalaryUnit salaryUnit);
    List<SalaryUnit> findAllByUser_idOrderByPeriodDesc(long id);
    Optional<SalaryUnit> findFirstByUser_idAndPeriodOrderByPeriodDesc(long user_id, Date period);
    void delete(SalaryUnit salaryUnit);
}
