package account.business.services;

import account.business.entities.User;
import account.config.UserDetailsImpl;
import account.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public Optional<User> register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        Optional<User> optUser = userRepository.findByEmail(user.getEmail());
        if (optUser.isPresent()) {
            return Optional.of(null);
        }

        return Optional.of(userRepository.save(user));
    }

    public Optional<User> authorize(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        return optUser;
    }
}
