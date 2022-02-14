package account.presentation;

import account.business.entities.dbentities.Group;
import account.business.entities.dbentities.User;
import account.business.entities.businesslogicelements.BreachedSet;
import account.business.entities.dto.ChangepassDTO;
import account.business.entities.businesslogicelements.Password;
import account.business.entities.dto.UserDTO;
import account.business.services.AuthService;
import account.business.services.PasswordService;
import account.config.DataLoader;
import account.config.exceptions.badrequestexceptions.BadRequestExceptionThrower;
import org.aspectj.weaver.GeneratedReferenceTypeDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
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
    @Autowired
    private DataLoader dataLoader;

    @PostMapping("/signup")
    public UserDTO register(@Valid @RequestBody User user) {
        Password password = new Password(user.getPassword());
        passwordService.checkAvailability(password, breachedSet);
        authService.encryptAndChangePassword(user, password);
        Optional<User> optUser = authService.register(user);
        if (optUser.isEmpty()) {
            BadRequestExceptionThrower.throwUserExistsException();
        }

        return new UserDTO(optUser.get());
    }

    @PostMapping("/changepass")
    public ChangepassDTO changePassword(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody Password password) {
        User user = authService.getUserByEmail(userDetails.getUsername()).get();
        passwordService.checkAvailability(password, user, breachedSet);
        authService.encryptAndChangePassword(user, password);
        authService.saveUser(user);
        return new ChangepassDTO(user.getEmail(), "The password has been updated successfully");
    }

    @GetMapping("/getroles")
    public List<Group> getRoles() {
        return authService.getRoles();
    }
}
