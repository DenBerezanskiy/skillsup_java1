package ua.dp.skillsup.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ua.dp.skillsup.reflection.annotations.Env;
import ua.dp.skillsup.reflection.annotations.Execute;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.StandardSystemProperty.JAVA_HOME;

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
                        e.printStackTrace();
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
                    Map<String, String> environment = System.getenv();

                    for (String envName : environment.keySet())
                    {
                        if (envName.equals(param))
                        {
                            systemParameter = envName + "=" + environment.get(envName);
                        }
                    }
                }
            }
        }
        return systemParameter;
    }
}

