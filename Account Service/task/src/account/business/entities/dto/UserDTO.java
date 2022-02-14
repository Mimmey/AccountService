package account.business.entities.dto;

import account.business.entities.dbentities.Group;
import account.business.entities.dbentities.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    @NotNull
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Lastname is required")
    private String lastname;

    @Email
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "[a-zA-z0-9]+@acme\\.com", message = "Domain should be @acme.com")
    private String email;

    private Set<String> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.roles = new HashSet<>();

        for (Group role : user.getRoles()) {
            roles.add(role.getName());
        }
    }

    public UserDTO() {
        this.roles = new HashSet<>();
    }
}