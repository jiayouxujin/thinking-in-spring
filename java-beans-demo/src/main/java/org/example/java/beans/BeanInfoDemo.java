package org.example.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * {@link java.beans.BeanInfo}
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo= Introspector.getBeanInfo(Person.class,Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {

                    //允许添加属性编辑器
                    //GUI string->PropertyType
                    // name->string
                    Class<?>propertyType=propertyDescriptor.getPropertyType();
                    String propertyName=propertyDescriptor.getName();
                    if ("age".equals(propertyName)){
                        //string ->integer
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                    }
                });
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value=Integer.valueOf(text);
            setValue(value);
        }
    }
}
