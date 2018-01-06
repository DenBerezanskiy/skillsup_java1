package ua.dp.skillsup.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ua.dp.skillsup.reflection.annotations.Execute;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import static com.google.common.base.StandardSystemProperty.JAVA_HOME;

/**
 * Created by Denis Berezanskiy on 14.12.17.
 */
public class Executor {
    public static void execute(String packageName)
    {
        Reflections reflections = new Reflections(packageName , new SubTypesScanner(false));

        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class );
        for(Class aClass:allClasses)
        {
            for(Method method: aClass.getDeclaredMethods())
            {
                if(method.isAnnotationPresent(Execute.class))
                {
                    try
                    {

                        method.invoke(aClass.newInstance(), JAVA_HOME.toString());

                    }
                    catch (InstantiationException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
