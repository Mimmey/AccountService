package account.business.entities.businesslogicelements;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Password {
    @NotBlank
    @Size
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String new_password;

    public Password(String password) {
        this.new_password = password;
    }

}
