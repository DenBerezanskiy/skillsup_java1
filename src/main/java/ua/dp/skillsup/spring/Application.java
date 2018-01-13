package ua.dp.skillsup.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.dp.skillsup.tdd.AccountService;
import ua.dp.skillsup.tdd.BankAccount;

import javax.annotation.PostConstruct;

/**
 * Created by skillsup on 19.12.17.
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("Start");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("Context initialised");
        Application application =
                (Application)context.getBean("application");
        application.doStuff();

    }

    private BankAccount sender;
    private BankAccount recipient;
    private double amount;
    private AccountService accountService;

    public void doStuff(){
        accountService.transferMoney(
                sender, recipient, 100);

        System.out.println(sender);
        System.out.println(recipient);
    }

    public void setSender(BankAccount sender) {
        this.sender = sender;
    }

    public void setRecipient(BankAccount recipient) {
        this.recipient = recipient;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Application initialised");
    }
}
