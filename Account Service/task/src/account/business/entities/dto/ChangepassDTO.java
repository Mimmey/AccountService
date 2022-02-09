package account.business.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangepassDTO {
    private String email;
    private String status;
}
