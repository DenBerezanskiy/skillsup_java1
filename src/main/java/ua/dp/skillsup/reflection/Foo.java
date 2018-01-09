package ua.dp.skillsup.reflection;

import ua.dp.skillsup.reflection.annotations.Env;
import ua.dp.skillsup.reflection.annotations.Execute;

/**
 * Created by Denis Berezanskiy on 15.12.17.
 */

public class Foo{
    @Execute
    public void bar(@Env("JAVA_HOME") String javaHome){

        System.out.println(javaHome);
    }
}