package account.business.services;

import account.business.entities.businesslogicelements.enums.RoleOperations;
import account.business.entities.businesslogicelements.enums.Roles;
import account.business.entities.dbentities.Group;
import account.business.entities.dbentities.User;
import account.business.entities.businesslogicelements.Password;
import account.business.entities.dto.RoleOperationDTO;
import account.config.DataLoader;
import account.config.exceptions.badrequestexceptions.BadRequestExceptionThrower;
import account.config.exceptions.notfoundexceptions.NotFoundExceptionThrower;
import account.persistence.GroupRepository;
import account.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PasswordService passwordService;

    private void grantRole(User user, Group role) {
        List<Group> groupList = user.getRoles();
        Group accountantRole = groupRepository.findByName(Roles.ACCOUNTANT.getName()).get();
        Group administratorRole = groupRepository.findByName(Roles.ADMINISTRATOR.getName()).get();

        if (role.getName().equals(Roles.ADMINISTRATOR.getName())
                && groupList.contains(accountantRole)
                || role.getName().equals(Roles.ADMINISTRATOR.getName())
                && groupList.contains(administratorRole)) {
            BadRequestExceptionThrower.throwAdministratorAccountRolesConflictException();
        }

        user.addRole(role);
    }

    private void removeRole(User user, Group role) {
        if (role.getName().equals(Roles.ADMINISTRATOR.getName())) {
            BadRequestExceptionThrower.throwRemovingAdministratorRoleException();
        }

        List<Group> groupList = user.getRoles();
        if (!groupList.contains(role)) {
            BadRequestExceptionThrower.throwUserDoesntHaveRoleException();
        }

        if (groupList.size() == 1) {
            BadRequestExceptionThrower.throwRemovingOnlyExistingRoleException();
        }

        user.removeRole(role);
    }

    public void changeRoles(RoleOperationDTO roleOperation) {
        Optional<RoleOperations> optOperation = RoleOperations.findByName(roleOperation.getOperation());
        Optional<User> optUser = getUserByEmail(roleOperation.getUser());
        Optional<Group> optGroup = groupRepository.findByName(roleOperation.getRole());

        if (optUser.isEmpty()) {
            NotFoundExceptionThrower.throwUserNotFoundException();
        }

        if (optOperation.isEmpty()) {
            NotFoundExceptionThrower.throwOperationNotFoundException();
        }

        if (optGroup.isEmpty()) {
            NotFoundExceptionThrower.throwRoleNotFoundExceptionError();
        }

        switch(optOperation.get()) {
            case GRANT:
                grantRole(optUser.get(), optGroup.get());
                break;
            case REMOVE:
                removeRole(optUser.get(), optGroup.get());
                break;
            default:
                NotFoundExceptionThrower.throwOperationNotFoundException();
        }
    }

    public void encryptAndChangePassword(User user, Password password) {
        passwordService.encryptPassword(password);
        user.setPassword(password.getNew_password());
    }

    public User formatEmail(User user) {
        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAllByOrderByIdAsc();
    }

    public List<Group> getRoles() {
        return groupRepository.findAll();
    }

    public User deleteByEmailAdminRestricted(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Group adminGroup = groupRepository.findByName(Roles.ADMINISTRATOR.getName()).get();

        if (optUser.get().getRoles().contains(adminGroup)) {
            BadRequestExceptionThrower.throwRemovingAdministratorRoleException();
        }

        return userRepository.deleteByEmail(email);
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
        if (!userRepository.findAllByOrderByIdAsc().isEmpty()) {
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
