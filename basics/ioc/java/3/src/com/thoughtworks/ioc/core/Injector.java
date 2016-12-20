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
            Constructor validConstructor = getInjectedConstructor(clazz);
            if (validConstructor != null) {
                Parameter[] parameters = validConstructor.getParameters();
                Object[] parameterValues = new Object[parameters.length];
                for (int i = 0; i < parameters.length; ++i) {
                    System.out.println(parameters[i].getType());
                    if (getInjectedConstructor(parameters[i].getType()) == null) {
                        throw new NoInjectedConstructorFoundException(parameters[i].getType(), clazz);
                    }
                    parameterValues[i] = parameters[i].getType().newInstance();
                }
                obj = validConstructor.newInstance(parameterValues);
            }
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
        Constructor validConstructor = null;
        int annotatedCount = 0;
        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                validConstructor = constructor;
                annotatedCount += 1;
            }
            if (annotatedCount > 1) {
                throw new TooManyInjectedConstructorException(clazz);
            }
        }
        if (constructors.length == 1) {
            Constructor defaultConstructor = constructors[0];
            if(defaultConstructor.getParameterCount() == 0) {
                return defaultConstructor;
            }
        }
        return validConstructor;
    }
}
