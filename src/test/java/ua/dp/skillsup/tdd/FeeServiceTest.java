package ua.dp.skillsup.tdd;

import org.junit.Before;
import org.mockito.Mockito;

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
}