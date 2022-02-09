package account.business.entities.businesslogicelements.enums;

import java.util.Optional;

public enum RoleOperations {
    GRANT("GRANT"), REMOVE("REMOVE");

    private String name;

    RoleOperations(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Optional<RoleOperations> findByName(String name) {
        for (RoleOperations operation : RoleOperations.values()) {
            if (operation.name.equalsIgnoreCase(name)) {
                return Optional.of(operation);
            }
        }
        return Optional.empty();
    }
}
