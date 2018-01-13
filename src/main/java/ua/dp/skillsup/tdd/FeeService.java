package ua.dp.skillsup.tdd;

import org.springframework.beans.factory.InitializingBean;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FeeService implements InitializingBean{

    private HolidayService holidayService;
    private double fee;

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    public FeeService() {
    }

    public FeeService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    public double getFee(double paymentAmount) {
        return fee;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("FeeService Initialised with fee " + fee);
    }
}
