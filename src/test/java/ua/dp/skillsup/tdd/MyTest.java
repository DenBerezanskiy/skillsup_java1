package ua.dp.skillsup.tdd;

import org.junit.Ignore;
import org.junit.Test;

public class MyTest {

    @Test
    @Ignore("Will be fixed in version 2.0")
    public void fooTest(){
        String s = null;
        System.out.println(s.charAt(0));
    }

    @Test
    public void fooTest1(){
        System.out.println("i'm a test1!");
    }
}
