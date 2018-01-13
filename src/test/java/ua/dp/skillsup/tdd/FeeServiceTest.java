package ua.dp.skillsup.tdd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Denis Berezanskiy on 13.01.2018.
 */
public class FeeServiceTest
{
BankAccount sender;
BankAccount recipient;
AccountService accountService;
FeeService feeService;
HolidayService holidayService;
WeekendService weekendService;

@Before
    public void before()
{
    sender = new BankAccount(100);
    recipient = new BankAccount(0);
    holidayService = Mockito.mock(HolidayService.class);
    weekendService = Mockito.mock(WeekendService.class);
    feeService = new FeeService(holidayService,weekendService);
    accountService = new AccountService(feeService);
}
@Test
public void transferOnWorkingDays()
{
    Mockito.when(weekendService.isWeekend()).thenReturn(false);
    Mockito.when(holidayService.isHoliday(new Date())).thenReturn(false);

    accountService.transferMoney(sender,recipient,100);
    Assert.assertEquals(0,sender.getAmount(),0.1);
    Assert.assertEquals(99,recipient.getAmount(),0.1);
}
@Test
    public void transferDuringWeekends()
{
    Mockito.when(weekendService.isWeekend()).thenReturn(true);
    Mockito.when(holidayService.isHoliday(new Date())).thenReturn(false);

    accountService.transferMoney(sender,recipient,100);
    Assert.assertEquals(0,sender.getAmount(),0.1);
    Assert.assertEquals(98.5,recipient.getAmount(),0.1);
}
@Test
    public void transferDuringHolidaysOnWorkingDays()
{
    Mockito.when(weekendService.isWeekend()).thenReturn(false);
    Mockito.when(holidayService.isHoliday(new Date())).thenReturn(true);

    accountService.transferMoney(sender,recipient,100);
    Assert.assertEquals(0,sender.getAmount(),0.1);
    Assert.assertEquals(98.5,recipient.getAmount(),0.1);
}
}