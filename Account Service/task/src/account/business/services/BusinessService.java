package account.business.services;

import account.business.entities.User;
import account.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        Optional<User> optUser = userRepository.findByEmailIgnoreCase(email);
        return optUser;
    }
}
