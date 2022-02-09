package account.business.entities.businesslogicelements;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class BreachedSet {

    private Set<String> breachedSet = new HashSet<>(Arrays.asList("PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
            "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
            "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember"));

    public boolean contains(String s) {
        for (String unit : breachedSet) {
            if (unit.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
