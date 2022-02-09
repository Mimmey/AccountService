package account.business.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryUnitDTO {
    @NotBlank
    private String employee;
    @NotBlank
    private String period;
    @NotNull
    private long salary;
}
