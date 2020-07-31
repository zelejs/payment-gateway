package com.jfeat.am.core.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum BasicType {

    BYTE,
    SHORT,
    INT,
    INTEGER,
    LONG,
    DOUBLE,
    FLOAT,
    BOOLEAN,
    CHAR,
    CHARACTER,
    STRING;

    public static final Map<Class<?>, Class<?>> wrapperPrimitiveMap = new HashMap(8);
    public static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap(8);

    private BasicType() {
    }

    static {
        wrapperPrimitiveMap.put(Boolean.class, Boolean.TYPE);
        wrapperPrimitiveMap.put(Byte.class, Byte.TYPE);
        wrapperPrimitiveMap.put(Character.class, Character.TYPE);
        wrapperPrimitiveMap.put(Double.class, Double.TYPE);
        wrapperPrimitiveMap.put(Float.class, Float.TYPE);
        wrapperPrimitiveMap.put(Integer.class, Integer.TYPE);
        wrapperPrimitiveMap.put(Long.class, Long.TYPE);
        wrapperPrimitiveMap.put(Short.class, Short.TYPE);
        Iterator var0 = wrapperPrimitiveMap.entrySet().iterator();

        while(var0.hasNext()) {
            Map.Entry<Class<?>, Class<?>> entry = (Map.Entry)var0.next();
            primitiveWrapperMap.put(entry.getValue(), entry.getKey());
        }

    }
}
