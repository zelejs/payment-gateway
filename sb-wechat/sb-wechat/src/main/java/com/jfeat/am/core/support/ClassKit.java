package com.jfeat.am.core.support;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassKit {

    public static boolean isAssignable(Class<?> targetType, Class<?> sourceType) {
        if (null != targetType && null != sourceType) {
            if (targetType.isAssignableFrom(sourceType)) {
                return true;
            } else {
                Class resolvedPrimitive;
                if (targetType.isPrimitive()) {
                    resolvedPrimitive = (Class)BasicType.wrapperPrimitiveMap.get(sourceType);
                    if (resolvedPrimitive != null && targetType.equals(resolvedPrimitive)) {
                        return true;
                    }
                } else {
                    resolvedPrimitive = (Class)BasicType.primitiveWrapperMap.get(sourceType);
                    if (resolvedPrimitive != null && targetType.isAssignableFrom(resolvedPrimitive)) {
                        return true;
                    }
                }

                return false;
            }
        } else {
            return false;
        }
    }

    public static Method setAccessible(Method method) {
        if (null != method && isNotPublic(method)) {
            method.setAccessible(true);
        }

        return method;
    }

    public static boolean isNotPublic(Class<?> clazz) {
        return !isPublic(clazz);
    }

    public static boolean isNotPublic(Method method) {
        return !isPublic(method);
    }

    public static boolean isPublic(Class<?> clazz) {
        if (null == clazz) {
            throw new NullPointerException("Class to provided is null.");
        } else {
            return Modifier.isPublic(clazz.getModifiers());
        }
    }

    public static boolean isPublic(Method method) {
        if (null == method) {
            throw new NullPointerException("Method to provided is null.");
        } else {
            return isPublic(method.getDeclaringClass());
        }
    }

}
