package account.business.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteUserDTO {
    private String user;
    private String status;
}
