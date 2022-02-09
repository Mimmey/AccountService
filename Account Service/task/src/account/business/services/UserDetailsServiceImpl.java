package account.business.services;

import account.business.entities.dbentities.User;
import account.config.UserDetailsImpl;
import account.config.exceptions.unauthorizedexceptions.CannotFindEmailForAuthorizationException;
import account.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByEmailIgnoreCase(username);
        if (optUser.isEmpty()) {
            throw new CannotFindEmailForAuthorizationException(username);
        }

        User user = optUser.get();
        return new UserDetailsImpl(user);
    }
}
