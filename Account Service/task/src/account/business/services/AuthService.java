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
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    public Optional<User> register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        Optional<User> optUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (optUser.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(userRepository.save(user));
    }
}
