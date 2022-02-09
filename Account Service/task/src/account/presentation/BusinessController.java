package account.presentation;

import account.business.businesslogicunits.DateHandler;
import account.business.entities.dbentities.SalaryUnit;
import account.business.entities.dbentities.User;
import account.business.entities.dto.BusinessCardDTO;
import account.business.entities.dto.SalaryUnitDTO;
import account.business.entities.dto.StatusResponseDTO;
import account.business.services.AuthService;
import account.business.services.BusinessService;
import account.config.exceptions.notfoundexceptions.SalaryUnitNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
public class BusinessController {
    @Autowired
    private AuthService authService;
    @Autowired
    private BusinessService businessService;

    @GetMapping("/empl/payment")
    public ResponseEntity<Object> getPayments(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false) String period) {
        User user = authService.getUserByEmail(userDetails.getUsername()).get();
        List<SalaryUnit> unitList;
        ResponseEntity<Object> responseEntity;

        if (period == null) {
            unitList = businessService.getSalaryUnitsByUser(user);
            List<BusinessCardDTO> cardList = businessService.unitListToCardList(unitList);
            responseEntity = new ResponseEntity<>(cardList, HttpStatus.OK);
        } else {
            Optional<SalaryUnit> optUnit = businessService.getSalaryUnitByUserAndPeriod(user, DateHandler.toDate(period));
            if (optUnit.isEmpty()) {
                throw new SalaryUnitNotFoundException();
            }

            return new ResponseEntity<>(businessService.salaryUnitToBusinessCard(optUnit.get()), HttpStatus.OK);
        }

        return responseEntity;
    }

    @Transactional
    @PostMapping("/acct/payments")
    public StatusResponseDTO uploadPayroll(@RequestBody @Valid List<SalaryUnitDTO> units) {
        for (SalaryUnitDTO dto : units) {
            SalaryUnit salaryUnit = businessService.createUnit(dto);
            businessService.saveSalaryUnit(salaryUnit);
        }

        ?return new StatusResponseDTO("Added successfully!");
    }

    @PutMapping("/acct/payments")
    public StatusResponseDTO updatePayment(@RequestBody @Valid SalaryUnitDTO dto) {
        SalaryUnit unit = businessService.createUnit(dto);
        businessService.updateSalaryUnit(unit);
        ?return new StatusResponseDTO("Updated successfully!");
    }
}
