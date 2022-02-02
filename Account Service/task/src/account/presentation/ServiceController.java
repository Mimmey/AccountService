package account.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    
    @PutMapping("api/admin/role")
    public static void changeRole() {

    }

    @DeleteMapping("api/admin/user")
    public static void deleteUser() {

    }

    @GetMapping("api/admin/user")
    public static void getAllUsersInfo() {

    }
}
