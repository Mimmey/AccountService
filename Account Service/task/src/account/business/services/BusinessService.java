package account.business.services;

import account.business.businesslogicunits.DateHandler;
import account.business.entities.dbentities.SalaryUnit;
import account.business.entities.dbentities.User;
import account.business.entities.dto.BusinessCardDTO;
import account.business.entities.dto.SalaryUnitDTO;
import account.persistence.SalaryUnitRepository;
import account.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void checkIfValid(SalaryUnitDTO salaryUnitDTO) {
        if (salaryUnitDTO.getSalary() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary must be non-negative!");
        }
    }

    public SalaryUnit createUnit(SalaryUnitDTO dto) {
        checkIfValid(dto);

        Optional<User> optUser = authService.getUserByEmail(dto.getEmployee());
        if(optUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email not found");
        }

        Date period = DateHandler.toDate(dto.getPeriod());
        return new SalaryUnit(dto.getSalary(), optUser.get(), period);
    }

    public List<SalaryUnit> getSalaryUnitsByUser(User user) {
        return salaryUnitRepository.findAllByUser_idOrderByPeriodDesc(user.getId());
    }

    public Optional<SalaryUnit> getSalaryUnitsByUserAndPeriod(User user, Date period) {
        return salaryUnitRepository.findFirstByUser_idAndPeriodOrderByPeriodDesc(user.getId(), period);
    }

    public SalaryUnit saveSalaryUnit(SalaryUnit salaryUnit) {
        try {
            return salaryUnitRepository.save(salaryUnit);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicated salary units!");
        }
    }

    public BusinessCardDTO salaryUnitToBusinessCard(SalaryUnit salaryUnit) {
        return new BusinessCardDTO(salaryUnit, salaryUnit.getUser());
    }

    public void turnSalaryUnit(SalaryUnit base, SalaryUnit template) {
        base.setSalary(template.getSalary());
        base.setPeriod(template.getPeriod());
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
            Optional<SalaryUnit> optBaseUnit = getSalaryUnitsByUserAndPeriod(salaryUnit.getUser(), salaryUnit.getPeriod());
            if (optBaseUnit.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            SalaryUnit baseUnit = optBaseUnit.get();
            salaryUnitRepository.delete(baseUnit);
            turnSalaryUnit(baseUnit, salaryUnit);
            salaryUnitRepository.save(baseUnit);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating!");
        }
    }
}
