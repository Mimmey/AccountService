package account.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @GetMapping("api/empl/payment")
    public static void getPayments() {

    }

    @PostMapping("api/acct/payments")
    public static void uploadPayroll() {

    }

    @PutMapping("api/acct/payments")
    public static void updatePayment() {

    }
}
