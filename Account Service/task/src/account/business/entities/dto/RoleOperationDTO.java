package account.business.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RoleOperationDTO {
    @NotBlank
    private String user;
    @NotBlank
    private String role;
    @NotBlank
    private String operation;
}
