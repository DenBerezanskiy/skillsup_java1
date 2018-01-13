package ua.dp.skillsup.tdd;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by Denis Berezanskiy on 13.01.2018.
 */
public class WeekendService
{
    LocalDate localDate;
    public boolean isWeekend()
    {
        if(localDate.getDayOfWeek()== DayOfWeek.WEDNESDAY ||
                localDate.getDayOfWeek()==DayOfWeek.SATURDAY)
        {
            return true;
        }
        return false;
    }
}
