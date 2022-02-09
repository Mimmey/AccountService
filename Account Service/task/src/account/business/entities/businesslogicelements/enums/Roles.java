package account.business.entities.businesslogicelements.enums;

public enum Roles {
    ADMINISTRATOR("ROLE_ADMINISTRATOR"), USER("ROLE_USER"), ACCOUNTANT("ROLE_ACCOUNTANT");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
