package com.zhaosen.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
@SuppressWarnings({ "rawtypes"})
public class ReflectUtil {
    public ReflectUtil() {
    }

    public static Object constructorNewInstance(String className, Class[] parameterTypes, Object[] initargs) {
        try {
            Constructor var4 = Class.forName(className).getDeclaredConstructor(parameterTypes);
            var4.setAccessible(true);
            return var4.newInstance(initargs);
        } catch (Exception var41) {
            throw new RuntimeException();
        }
    }

    public static Class getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    public static Object getFieldValue(String propertyName, Object obj) {
        try {
            Field var3 = obj.getClass().getDeclaredField(propertyName);
            var3.setAccessible(true);
            return var3.get(obj);
        } catch (Exception var31) {
            throw new RuntimeException();
        }
    }

    public static Object getProperty(String propertyName, Object object) {
        try {
            PropertyDescriptor var4 = new PropertyDescriptor(propertyName, object.getClass());
            Method method = var4.getReadMethod();
            return method.invoke(object, new Object[0]);
        } catch (Exception var41) {
            throw new RuntimeException();
        }
    }

    public static Object getBeanInfoProperty(String propertyName, Object object) {
        try {
            return BeanUtils.getProperty(object, propertyName);
        } catch (Exception var3) {
            throw new RuntimeException();
        }
    }

    public static void setBeanInfoProperty(Object object, String propertyName, String value) {
        try {
            BeanUtils.setProperty(object, propertyName, value);
        } catch (Exception var4) {
            throw new RuntimeException();
        }
    }

    public static Object getPropertyUtilByName(String propertyName, Object object) {
        try {
            return PropertyUtils.getProperty(object, propertyName);
        } catch (Exception var3) {
            throw new RuntimeException();
        }
    }

    public static void setPropertyUtilByName(Object object, String propertyName, Object value) {
        try {
            PropertyUtils.setProperty(object, propertyName, value);
        } catch (Exception var4) {
            throw new RuntimeException();
        }
    }

    public static void setProperties(Object object, String propertyName, Object value) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
        Method methodSet = pd.getWriteMethod();
        methodSet.invoke(object, new Object[]{value});
    }

    public static void setFieldValue(Object obj, String propertyName, Object value) {
        try {
            Field var4 = obj.getClass().getDeclaredField(propertyName);
            var4.setAccessible(true);
            var4.set(obj, value);
        } catch (Exception var41) {
            throw new RuntimeException();
        }
    }

    public static Object methodInvoke(String className, String methodName, Class[] parameterTypes, Object[] values, Object object) {
        try {
            Method var6 = Class.forName(className).getDeclaredMethod(methodName, parameterTypes);
            var6.setAccessible(true);
            return var6.invoke(object, values);
        } catch (Exception var61) {
            throw new RuntimeException();
        }
    }
}
