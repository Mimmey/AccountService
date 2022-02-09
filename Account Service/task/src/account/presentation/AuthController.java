package account.presentation;

import account.business.entities.dbentities.User;
import account.business.entities.businesslogicelements.BreachedSet;
import account.business.entities.dto.ChangepassDTO;
import account.business.entities.businesslogicelements.Password;
import account.business.services.AuthService;
import account.business.services.PasswordService;
import account.config.exceptions.badrequestexceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("api/auth")
@RestController
public class AuthController {
    @Autowired
    private BreachedSet breachedSet;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordService passwordService;

    @PostMapping("/signup")
    public User register(@Valid @RequestBody User user) {
        Password password = new Password(user.getPassword());
        passwordService.checkAvailability(password, breachedSet);
        authService.encryptAndChangePassword(user, password);
        Optional<User> optUser = authService.register(user);
        if (optUser.isEmpty()) {
            throw new UserAlreadyExistException();
        }

        return optUser.get();
    }

    @PostMapping("/changepass")
    public ChangepassDTO changePassword(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody Password password) {
        User user = authService.getUserByEmail(userDetails.getUsername()).get();
        passwordService.checkAvailability(password, user, breachedSet);
        authService.encryptAndChangePassword(user, password);
        authService.saveUser(user);
        ?return new ChangepassDTO(user.getEmail(), "The password has been updated successfully");
    }
}
