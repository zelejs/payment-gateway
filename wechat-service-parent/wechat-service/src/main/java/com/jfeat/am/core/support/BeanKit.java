package com.jfeat.am.core.support;

import com.jfeat.am.common.exception.ToolBoxException;
import com.jfeat.crud.base.util.StrKit;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BeanKit {

    public static <T> Map<String, Object> beanToMap(T bean) {
        return beanToMap(bean, false);
    }

    public static <T> Map<String, Object> beanToMap(T bean, boolean isToUnderlineCase) {
        if (bean == null) {
            return null;
        } else {
            HashMap map = new HashMap();

            try {
                PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(bean.getClass());
                PropertyDescriptor[] var4 = propertyDescriptors;
                int var5 = propertyDescriptors.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    PropertyDescriptor property = var4[var6];
                    String key = property.getName();
                    if (!key.equals("class")) {
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(bean);
                        if (null != value) {
                            map.put(isToUnderlineCase ? StrKit.toUnderlineCase(key) : key, value);
                        }
                    }
                }

                return map;
            } catch (Exception var11) {
                throw new ToolBoxException(var11);
            }
        }
    }

    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws IntrospectionException {
        return Introspector.getBeanInfo(clazz).getPropertyDescriptors();
    }

    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, BeanKit.CopyOptions.create());
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        copyProperties(source, target, BeanKit.CopyOptions.create().setIgnoreProperties(ignoreProperties));
    }

    public static void copyProperties(Object source, Object target, BeanKit.CopyOptions copyOptions) {
        if (null == copyOptions) {
            copyOptions = new BeanKit.CopyOptions();
        }

        Class<?> actualEditable = target.getClass();
        if (copyOptions.editable != null) {
            if (!copyOptions.editable.isInstance(target)) {
                throw new IllegalArgumentException(StrKit.format("Target class [{}] not assignable to Editable class [{}]", new Object[]{target.getClass().getName(), copyOptions.editable.getName()}));
            }

            actualEditable = copyOptions.editable;
        }

        PropertyDescriptor[] targetPds = null;

        Map sourcePdMap;
        try {
            sourcePdMap = getFieldNamePropertyDescriptorMap(source.getClass());
            targetPds = getPropertyDescriptors(actualEditable);
        } catch (IntrospectionException var15) {
            throw new ToolBoxException(var15);
        }

        HashSet<String> ignoreSet = copyOptions.ignoreProperties != null ? CollectionKit.newHashSet(copyOptions.ignoreProperties) : null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreSet == null || !ignoreSet.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = (PropertyDescriptor)sourcePdMap.get(targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassKit.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            Object value = ClassKit.setAccessible(readMethod).invoke(source);
                            if (null != value || !copyOptions.isIgnoreNullValue) {
                                ClassKit.setAccessible(writeMethod).invoke(target, value);
                            }
                        } catch (Throwable var16) {
                            throw new ToolBoxException(var16, "Copy property [{}] to [{}] error: {}", new Object[]{sourcePd.getName(), targetPd.getName(), var16.getMessage()});
                        }
                    }
                }
            }
        }

    }

    public static class CopyOptions {
        private Class<?> editable;
        private boolean isIgnoreNullValue;
        private String[] ignoreProperties;

        public static BeanKit.CopyOptions create() {
            return new BeanKit.CopyOptions();
        }

        public static BeanKit.CopyOptions create(Class<?> editable, boolean isIgnoreNullValue, String... ignoreProperties) {
            return new BeanKit.CopyOptions(editable, isIgnoreNullValue, ignoreProperties);
        }

        public CopyOptions() {
        }

        public CopyOptions(Class<?> editable, boolean isIgnoreNullValue, String... ignoreProperties) {
            this.editable = editable;
            this.isIgnoreNullValue = isIgnoreNullValue;
            this.ignoreProperties = ignoreProperties;
        }

        public BeanKit.CopyOptions setEditable(Class<?> editable) {
            this.editable = editable;
            return this;
        }

        public BeanKit.CopyOptions setIgnoreNullValue(boolean isIgnoreNullVall) {
            this.isIgnoreNullValue = isIgnoreNullVall;
            return this;
        }

        public BeanKit.CopyOptions setIgnoreProperties(String... ignoreProperties) {
            this.ignoreProperties = ignoreProperties;
            return this;
        }
    }

    public static Map<String, PropertyDescriptor> getFieldNamePropertyDescriptorMap(Class<?> clazz) throws IntrospectionException {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        Map<String, PropertyDescriptor> map = new HashMap();
        PropertyDescriptor[] var3 = propertyDescriptors;
        int var4 = propertyDescriptors.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            PropertyDescriptor propertyDescriptor = var3[var5];
            map.put(propertyDescriptor.getName(), propertyDescriptor);
        }

        return map;
    }
}
