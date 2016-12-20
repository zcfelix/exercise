package com.thoughtworks.ioc.core;

import com.thoughtworks.ioc.exception.NoInjectedConstructorFoundException;
import com.thoughtworks.ioc.exception.TooManyInjectedConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class Injector {
    public static <T> T getInstance(Class clazz) {
        Object obj = null;
        try {
            Constructor constructor = getInjectedConstructor(clazz);
            Parameter[] parameters = constructor.getParameters();
            Object[] parameterValues = new Object[parameters.length];
            for (int i = 0; i < parameters.length; ++i) {
                parameterValues[i] = getInstance(parameters[i].getType());
            }
            obj = constructor.newInstance(parameterValues);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    private static Constructor getInjectedConstructor(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        // Only default constructor and no parameters, no need annotation
        if (constructors.length == 1 && constructors[0].getParameterCount() == 0)
            return constructors[0];
        // For many constructors, found the annotated one
        Constructor validConstructor = null;
        int annotatedCount = 0;
        for (Constructor current : constructors) {
            if (current.isAnnotationPresent(Inject.class)) {
                validConstructor = current;
                annotatedCount++;
                if (annotatedCount > 1) {
                    throw new TooManyInjectedConstructorException(clazz);
                }
            }
        }
        // No injected constructor found
        if (validConstructor == null)
            throw new NoInjectedConstructorFoundException(clazz);
        return validConstructor;
    }
}
