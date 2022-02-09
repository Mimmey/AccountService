package account.business.services;

import account.business.entities.dbentities.User;
import account.business.entities.businesslogicelements.BreachedSet;
import account.business.entities.businesslogicelements.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PasswordService {

    private final static int MIN_SIZE = 12;

    @Autowired
    private PasswordEncoder encoder;

    public void encryptPassword(Password password) {
        password.setNew_password(encoder.encode(password.getNew_password()));
    }

    public void checkAvailability(Password password, User user, BreachedSet breachedSet) {
        checkIfPreviousIsSame(password, user);
        checkIfIsInBreachedList(password, breachedSet);
        checkIfValid(password);
    }

    public void checkAvailability(Password password, BreachedSet breachedSet) {
        checkIfIsInBreachedList(password, breachedSet);
        checkIfValid(password);
    }

    public void checkIfIsInBreachedList(Password password, BreachedSet breachedSet) {
        if (breachedSet.contains(password.getNew_password())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is in the hacker's database!");
        }
    }

    public void checkIfValid(Password password) {
        if (password.getNew_password().length() < MIN_SIZE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password length must be " + MIN_SIZE + " chars minimum!");
        }
    }

    public void checkIfPreviousIsSame(Password password, User user) {
        if (encoder.matches(password.getNew_password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The passwords must be different!");
        }
    }
}
