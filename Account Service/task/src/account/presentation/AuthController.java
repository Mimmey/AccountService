package account.presentation;

import account.business.entities.User;
import account.business.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("api/auth/signup")
    public User register(@Valid @RequestBody User user) {
        Optional<User> optUser = authService.register(user);
        if (optUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already registered");
        }

        return optUser.get();
    }

    @PostMapping("api/auth/changepass")
    public void changePassword() {

    }
}
