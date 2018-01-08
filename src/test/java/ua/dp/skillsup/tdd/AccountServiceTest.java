package ua.dp.skillsup.tdd;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class AccountServiceTest {
    @Test
    public void transferMoney() throws Exception {
        FeeService mock = Mockito.mock(FeeService.class);
        Mockito.when(mock.getFee(Mockito.anyDouble()))
                .thenReturn(0.01);
        AccountService service = new AccountService(
                mock);
        BankAccount sender = new BankAccount(100);
        BankAccount recipient = new BankAccount(0);
        service.transferMoney(sender,
                recipient, 100);
        Assert.assertEquals(0, sender.getAmount(), 0.00001);
        Assert.assertEquals(99, recipient.getAmount(), 0.00001);
        Mockito.verify(mock).getFee(100);
    }

    @Test
    public void transferMoneyWithArgumentCaptor() throws Exception {
        FeeService mock = Mockito.mock(FeeService.class);
        AccountService service = new AccountService(
                mock);
        BankAccount sender = new BankAccount(100);

        ArgumentCaptor<BankAccount> captor = ArgumentCaptor.forClass(BankAccount.class);


        Mockito.verify(service).transferMoney(sender,
                captor.capture(), 100);
        System.out.println(captor);
        Assert.assertEquals(0, sender.getAmount(), 0.00001);
//        Assert.assertEquals(100, recipient.getAmount(), 0.00001);
        Mockito.verify(mock).getFee(100);
    }
}