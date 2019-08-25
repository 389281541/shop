package com.rainbow.common.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumValidtor implements ConstraintValidator<EnumValidAnnotation, Object> {

    /**
     * 枚举类
     */
    Class<?>[] cls;

    /**
     * 字段是否允许为空，默认不允许
     */
    boolean allowNull;

    @Override
    public void initialize(EnumValidAnnotation constraintAnnotation) {
        cls = constraintAnnotation.target();
        allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    //枚举类验证
                    if (cl.isEnum()) {
                        if(allowNull == true && value == null){
                            return true;
                        }
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("getValue");
                        for (Object obj : objs) {
                            Object code = method.invoke(obj, null);
                            if (value.toString().equals(code.toString())) {
                                return true;
                            }
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } else{
            return true;
        }
        return false;
    }
}