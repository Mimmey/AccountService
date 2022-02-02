package account.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("api/auth/signup")
    public static void register() {

    }

    @PostMapping("api/auth/changepass")
    public static void changePassword() {

    }
}
