package ua.dp.skillsup.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ua.dp.skillsup.tdd.FeeService;

/**
 * Created by skillsup on 19.12.17.
 */
@Component
public class MyProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log(bean);
        return bean;
    }

    private void log(Object bean) {
        System.out.println(bean.getClass().getName());
        if (bean instanceof FeeService){
            FeeService feeService = (FeeService) bean;
            System.out.println("Fee settings:" + feeService.getFee(100));
        }
    }
}
