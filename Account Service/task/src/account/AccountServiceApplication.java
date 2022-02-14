package account;

import account.config.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountServiceApplication {
    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}