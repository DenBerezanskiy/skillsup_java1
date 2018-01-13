package ua.dp.skillsup.tdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class AccountService {
    @Autowired
    private FeeService feeService;

    public FeeService getFeeService() {
        return feeService;
    }

    public void setFeeService(FeeService feeService) {
        this.feeService = feeService;
    }

    public AccountService(){}

    public AccountService(FeeService feeService){
        this.feeService = feeService;
    }

    public void transferMoney(BankAccount sender,
                              BankAccount recipient, double amount){
        sender.setAmount(sender.getAmount() - amount);
        double fee = feeService.getFee(0);
        recipient.setAmount(recipient.getAmount() +
                amount * (1 - fee));
    }
}
