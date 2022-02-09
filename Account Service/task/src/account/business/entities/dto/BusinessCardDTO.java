package account.business.entities.dto;

import account.business.businesslogicunits.DateHandler;
import account.business.entities.dbentities.SalaryUnit;
import account.business.entities.dbentities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BusinessCardDTO {
    private String name;
    private String lastname;
    private String period;
    private String salary;

    public BusinessCardDTO(SalaryUnit salaryUnit, User user) {
        this.name = user.getName();
        this.lastname = user.getLastname();
        setPeriod(salaryUnit.getPeriod());
        setSalary(salaryUnit.getSalary());
    }

    private void setSalary(long salary) {
        long dollars = salary / 100;
        long cents = salary % 100;
        this.salary = String.format("%d dollar(s) %d cent(s)", dollars, cents);
    }

    private void setPeriod(Date period) {
        this.period = DateHandler.parseString(period);
    }
}
