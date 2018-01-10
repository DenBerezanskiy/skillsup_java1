package ua.dp.skillsup.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ua.dp.skillsup.reflection.annotations.Env;
import ua.dp.skillsup.reflection.annotations.Execute;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Map;
import java.util.Set;


/**
 * Created by Denis Berezanskiy on 14.12.17.
 */
public class Executor {
    public static void execute(String packageName)
    {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));

        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);

        for (Class aClass : allClasses)
        {
            for (Method method : aClass.getDeclaredMethods())
            {
                if (method.isAnnotationPresent(Execute.class))
                {
                    try
                    {
                        method.invoke(aClass.newInstance(), parameterMatcher(method));
                    }
                    catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
                    {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    private static String parameterMatcher(Method method)
    {
        String systemParameter = "";

        Annotation annotations[][] = method.getParameterAnnotations();

        for (Annotation annotation[] : annotations)
        {
            for (Annotation parameterAnnotation : annotation)
            {
                String param;

                if (parameterAnnotation instanceof Env)
                {
                    Env env = (Env) parameterAnnotation;
                    param = env.value();
                    systemParameter = System.getenv(param);
                }
            }
        }
        return systemParameter;
    }
}

