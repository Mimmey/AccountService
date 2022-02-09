package account.business.entities.businesslogicelements.enums;

public enum RoleOperations {
    GRANT("GRANT"), REMOVE("REMOVE");

    private String name;

    RoleOperations(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
