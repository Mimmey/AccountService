package account.config;

import account.business.entities.businesslogicelements.enums.Roles;
import account.business.entities.dbentities.Group;
import account.persistence.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class DataLoader {

    private GroupRepository groupRepository;

    @Autowired
    public DataLoader(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        createRoles();
    }

    private void createRoles() {
        try {
            groupRepository.save(new Group(Roles.ADMINISTRATOR.getName()));
            groupRepository.save(new Group(Roles.ACCOUNTANT.getName()));
            groupRepository.save(new Group(Roles.USER.getName()));
        } catch (Exception e) {

        }
    }
}