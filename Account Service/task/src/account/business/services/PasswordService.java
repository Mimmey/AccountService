package account.business.services;

import account.business.entities.dbentities.User;
import account.business.entities.businesslogicelements.BreachedSet;
import account.business.entities.businesslogicelements.Password;
import account.config.exceptions.badrequestexceptions.PasswordBreachedException;
import account.config.exceptions.badrequestexceptions.PasswordMatchesPreviousException;
import account.config.exceptions.badrequestexceptions.PasswordTooShortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final static int MIN_SIZE = 12;

    @Autowired
    private PasswordEncoder encoder;
    private void checkIfIsInBreachedList(Password password, BreachedSet breachedSet) {
        if (breachedSet.contains(password.getNew_password())) {
            throw new PasswordBreachedException();
        }
    }

    private void checkIfValid(Password password) {
        if (password.getNew_password().length() < MIN_SIZE) {
            throw new PasswordTooShortException(MIN_SIZE);
        }
    }

    private void checkIfPreviousIsSame(Password password, User user) {
        if (encoder.matches(password.getNew_password(), user.getPassword())) {
            throw new PasswordMatchesPreviousException();
        }
    }

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
}
