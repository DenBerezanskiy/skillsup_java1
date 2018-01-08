package ua.dp.skillsup.tdd;

import java.util.Date;
import java.util.Random;

public class HolidayService {
    public boolean isHoliday(Date date){
        Random random = new Random();
        return random.nextDouble() > 0.9;
    }
}
