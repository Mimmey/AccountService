package account.presentation;

import account.business.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @PostMapping("api/auth/signup")
    public static User register(@Valid @RequestBody User user) {
        return user;
    }

    @PostMapping("api/auth/changepass")
    public static void changePassword() {

    }
}
