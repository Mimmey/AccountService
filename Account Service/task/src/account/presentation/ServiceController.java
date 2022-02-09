package account.presentation;

import account.business.entities.dbentities.User;
import account.business.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/admin")
@RestController
public class ServiceController {

    @Autowired
    private AuthService authService;

    @PutMapping("/role")
    public void changeRole() {

    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {
        authService.deleteByIdAdminRestricted(id);
    }

    @GetMapping("/user")
    public List<User> getAllUsersInfo() {
        return authService.getAll();
    }
}
