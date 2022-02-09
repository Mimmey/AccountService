package account.business.services;

import account.business.entities.businesslogicelements.enums.Roles;
import account.business.entities.dbentities.Group;
import account.business.entities.dbentities.User;
import account.business.entities.businesslogicelements.Password;
import account.persistence.GroupRepository;
import account.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PasswordService passwordService;

    public void encryptAndChangePassword(User user, Password password) {
        passwordService.encryptPassword(password);
        user.setPassword(password.getNew_password());
    }

    public User formatEmail(User user) {
        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAllOrderByIdAsc();
    }

    public void deleteByIdAdminRestricted(long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Group adminGroup = groupRepository.findByName(Roles.ADMINISTRATOR.getName()).get();

        if (optUser.get().getRoles().contains(adminGroup)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't remove ADMINISTRATOR role!");
        }

        userRepository.deleteById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> optUser = userRepository.findByEmailIgnoreCase(email);
        return optUser;
    }

    public Optional<User> register(User user) {
        Optional<User> optUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (optUser.isPresent()) {
            return Optional.empty();
        }

        Group group;
        if (userRepository.existsUser()) {
            group = groupRepository.findByName(Roles.USER.getName()).get();
        } else {
            group = groupRepository.findByName(Roles.ADMINISTRATOR.getName()).get();
        }
        user.addRole(group);

        return Optional.of(userRepository.save(formatEmail(user)));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
