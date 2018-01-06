package ua.dp.skillsup.reflection;

import ua.dp.skillsup.reflection.annotations.Doc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by skillsup on 12.12.17.
 */
public class MetaProg {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = new User();
        user.setName("John Doe");
        user.setAmount(200);

        System.out.println("Methods:");
        Class clazz = user.getClass();
        for(Method method: clazz.getDeclaredMethods()){
            Class[] parameterTypes = method.getParameterTypes();
            System.out.println(method.getName() + Arrays.toString(parameterTypes));

            Doc annotation = method.getAnnotation(Doc.class);
            if (annotation != null) {
                System.out.println(annotation.value());
            }
        }

        System.out.println("\nFields:");

        for(Field field: clazz.getDeclaredFields()){
            field.setAccessible(true);
            System.out.println(field.toString() + " = " + field.get(user));
        }

        Method method = clazz.getDeclaredMethod("getUserCardLevel");
        Object result = method.invoke(user);
        System.out.println(method.getName() + " method's result is " + result);

        Method foo = clazz.getDeclaredMethod("foo");
        System.out.println(foo.invoke(null));
    }

    @Override
    @Deprecated
    public String toString() {
        return "MetaProg{}";
    }
}
