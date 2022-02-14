package account.business.services;

import account.business.businesslogicunits.DateHandler;
import account.business.entities.dbentities.SalaryUnit;
import account.business.entities.dbentities.User;
import account.business.entities.dto.BusinessCardDTO;
import account.business.entities.dto.SalaryUnitDTO;
import account.config.exceptions.badrequestexceptions.BadRequestExceptionThrower;
import account.config.exceptions.internalservererrorexceptions.InternalServerErrorExceptionThrower;
import account.config.exceptions.notfoundexceptions.NotFoundExceptionThrower;
import account.persistence.SalaryUnitRepository;
import account.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SalaryUnitRepository salaryUnitRepository;
    @Autowired
    private AuthService authService;

    private void checkIfValid(SalaryUnitDTO salaryUnitDTO) {
        if (salaryUnitDTO.getSalary() < 0) {
            BadRequestExceptionThrower.throwNegativeSalaryException();
        }
    }

    private void turnSalaryUnit(SalaryUnit base, SalaryUnit template) {
        base.setSalary(template.getSalary());
        base.setPeriod(template.getPeriod());
    }

    public SalaryUnit createUnit(SalaryUnitDTO dto) {
        checkIfValid(dto);

        Optional<User> optUser = authService.getUserByEmail(dto.getEmployee());
        if (optUser.isEmpty()) {
            NotFoundExceptionThrower.throwEmailNotFoundException();
        }

        Date period = DateHandler.toDate(dto.getPeriod());
        return new SalaryUnit(dto.getSalary(), optUser.get(), period);
    }

    public List<SalaryUnit> getSalaryUnitsByUser(User user) {
        return salaryUnitRepository.findAllByUser_idOrderByPeriodDesc(user.getId());
    }

    public Optional<SalaryUnit> getSalaryUnitByUserAndPeriod(User user, Date period) {
        return salaryUnitRepository.findFirstByUser_idAndPeriodOrderByPeriodDesc(user.getId(), period);
    }

    public SalaryUnit saveSalaryUnit(SalaryUnit salaryUnit) {
        try {
            return salaryUnitRepository.save(salaryUnit);
        } catch (Exception e) {
            BadRequestExceptionThrower.throwDuplicatedSalaryUnitsException();
            return null;
        }
    }

    public BusinessCardDTO salaryUnitToBusinessCard(SalaryUnit salaryUnit) {
        return new BusinessCardDTO(salaryUnit, salaryUnit.getUser());
    }

    public List<BusinessCardDTO> unitListToCardList(List<SalaryUnit> unitList) {
        List<BusinessCardDTO> cardList = new ArrayList<>();

        for (SalaryUnit unit : unitList) {
            cardList.add(new BusinessCardDTO(unit, unit.getUser()));
        }

        return cardList;
    }

    public void updateSalaryUnit(SalaryUnit salaryUnit) {
        try {
            Optional<SalaryUnit> optBaseUnit = getSalaryUnitByUserAndPeriod(salaryUnit.getUser(), salaryUnit.getPeriod());
            if (optBaseUnit.isEmpty()) {
                NotFoundExceptionThrower.throwSalaryUnitNotFoundException();
            }

            SalaryUnit baseUnit = optBaseUnit.get();
            salaryUnitRepository.delete(baseUnit);
            turnSalaryUnit(baseUnit, salaryUnit);
            salaryUnitRepository.save(baseUnit);
        } catch (Exception e) {
            InternalServerErrorExceptionThrower.throwUpdatingSalaryUnitException();
        }
    }
}
