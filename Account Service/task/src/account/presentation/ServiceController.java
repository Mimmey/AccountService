package account.presentation;

import account.business.entities.dbentities.User;
import account.business.entities.dto.RoleOperationDTO;
import account.business.entities.dto.UserDTO;
import account.business.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/admin")
@RestController
public class ServiceController {

    @Autowired
    private AuthService authService;

    @PutMapping("/role")
    public void changeRole(@RequestBody RoleOperationDTO roleOperation) {
        authService.changeRoles(roleOperation);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {
        authService.deleteByIdAdminRestricted(id);
    }

    @GetMapping("/user")
    public List<UserDTO> getAllUsersInfo() {
        List<User> users = authService.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }
}
