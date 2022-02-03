package account.presentation;

import account.business.entities.User;
import account.business.services.AuthService;
import account.business.services.BusinessService;
import account.config.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("api/empl/payment")
    public User getPayments(@AuthenticationPrincipal UserDetails details) {
        Optional<User> optUser = businessService.getUserByEmail(details.getUsername());
        if (optUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }

        return optUser.get();
    }

    @PostMapping("api/acct/payments")
    public void uploadPayroll() {

    }

    @PutMapping("api/acct/payments")
    public void updatePayment() {

    }
}
