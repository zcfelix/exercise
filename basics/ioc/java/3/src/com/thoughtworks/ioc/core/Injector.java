package com.thoughtworks.ioc.core;

import com.thoughtworks.ioc.exception.*;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Injector {
    private Set<Class> registeredClasses;
    private Map<Class, Class> bindClasses;

    public Injector() {
        registeredClasses = new HashSet<>();
        bindClasses = new HashMap<>();
    }

    public <T> T getInstance(Class clazz) {
        if (!registeredClasses.contains(clazz) && !bindClasses.containsValue(clazz))
            throw new ClassNotRegisteredException(clazz);

        Object obj = null;
        try {
            // Inject constructor
            Constructor constructor = getInjectedConstructor(clazz);
            Parameter[] parameters = constructor.getParameters();
            Object[] parameterValues = new Object[parameters.length];
            for (int i = 0; i < parameters.length; ++i) {
                parameterValues[i] = getInstance(parameters[i].getType());
            }
            obj = constructor.newInstance(parameterValues);

            // Inject fields
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    if (Modifier.isFinal(field.getModifiers())) {
                        throw new FinalFieldInjectedException(field);
                    }
                    field.setAccessible(true);
                    field.set(obj, getInstance(field.getType()));
                }
            }

            // Inject methods
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Inject.class)) {
                    if (Modifier.isAbstract(method.getModifiers())) {
                        throw new AbstractMethodInjectionException(method);
                    }
                    if (hasGenericParaType(method) || hasGenericReturnType(method)) {
                        throw new GenericMethodInjectionException(method);
                    }
                    Parameter[] methodParas = method.getParameters();
                    Object[] methodParaValues = new Object[methodParas.length];
                    for (int i = 0; i < methodParas.length; ++i) {
                        methodParaValues[i] = getInstance(methodParas[i].getType());
                    }
                    method.invoke(obj, methodParaValues);
                }
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

    private boolean hasGenericParaType(Method method) {
        Parameter[] parameters = method.getParameters();
        for (Parameter para : parameters) {
            if (TypeVariable.class.isAssignableFrom(para.getParameterizedType().getClass())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasGenericReturnType(Method method) {
        Type returnType = method.getGenericReturnType();
        return TypeVariable.class.isAssignableFrom(returnType.getClass());
    }

    private Constructor getInjectedConstructor(Class clazz) {
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

    public Injector register(Class clazz) {
        registeredClasses.add(clazz);
        return this;
    }

    public void clear() {
        registeredClasses.clear();
    }

    public void bindWith(Class interfaceClass, Class implementClass) {
        bindClasses.put(interfaceClass, implementClass);
    }
}
