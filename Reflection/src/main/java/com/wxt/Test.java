package com.wxt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Hello world!
 */
public class Test {
    public static void main(String[] args) throws Exception {

        example();
    }

    public static void test(){
        /***最佳实践**************************************************/
        // 1.通过字符串获取Class对象，这个字符串必须带上完整路径名
        Class studentClass = null;
        try {
            studentClass = Class.forName("com.wxt.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2.获取声明的构造方法，传入所需参数的类名，如果有多个参数，用','连接即可
        Constructor studentConstructor = null;
        try {
            studentConstructor = studentClass.getDeclaredConstructor(String.class,Integer.class,Integer.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 如果是私有的构造方法，需要调用下面这一行代码使其可使用，公有的构造方法则不需要下面这一行代码
        studentConstructor.setAccessible(true);

        // 3.使用构造方法的newInstance方法创建对象，传入构造方法所需参数，如果有多个参数，用','连接即可
        Object student = null;
        try {
            student = studentConstructor.newInstance("NameA",23,1);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 4.获取声明的字段，传入字段名
        Field studentAgeField = null;
        try {
            studentAgeField = studentClass.getDeclaredField("studentAge");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        // 如果是私有的字段，需要调用下面这一行代码使其可使用，公有的字段则不需要下面这一行代码
        // studentAgeField.setAccessible(true);
        // 使用字段的set方法设置字段值，传入此对象以及参数值
        try {
            studentAgeField.set(student, 10);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 5.获取声明的函数，传入所需参数的类名，如果有多个参数，用','连接即可
        Method studentShowMethod = null;
        try {
            studentShowMethod = studentClass.getDeclaredMethod("show", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 如果是私有的函数，需要调用下面这一行代码使其可使用，公有的函数则不需要下面这一行代码
        studentShowMethod.setAccessible(true);

        // 6.使用函数的invoke方法调用此函数，传入此对象以及函数所需参数，如果有多个参数，用','连接即可。函数会返回一个Object对象，使用强制类型转换转成实际类型即可
        Object result = null;
        try {
            result = studentShowMethod.invoke(student, "message");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + result);

    }

    public static  void example(){
        /***1.获取Class对象**************************************************/
        //1.第一种方法:是通过类的全路径字符串获取 Class 对象，这也是我们平时最常用的反射获取 Class 对象的方法；
        Class studentClass1 = null;
        try {
            studentClass1 = Class.forName("com.wxt.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2.第二种方法：通过类的class属性，限制条件：需要导入类的包
        Class studentClass2 = Student.class;

        //3.第三种方法:已经有了 Student 对象，不再需要反射
        Class studentClass3 = new Student().getClass();

        /***2.获取字段**************************************************/
        // 1.获取所有声明的字段
        Field[] declaredFieldList = studentClass1.getDeclaredFields();
        for (Field declaredField : declaredFieldList) {
            System.out.println("字段: " + declaredField);
        }
        // 2.获取所有公有的字段
        Field[] fieldList = studentClass1.getFields();
        for (Field field : fieldList) {
            System.out.println("共有字段: " + field);
        }

        /***3.获取构造器**************************************************/
        // 1.获取所有声明的构造方法
        Constructor[] declaredConstructorList = studentClass1.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructorList) {
            System.out.println("构造器: " + declaredConstructor);
        }
        // 2.获取所有公有的构造方法
        Constructor[] constructorList = studentClass1.getConstructors();
        for (Constructor constructor : constructorList) {
            System.out.println("共有构造器: " + constructor);
        }

        // 3.获取指定构造方法
        try {
            Constructor constructor1=studentClass1.getDeclaredConstructor(String.class,Integer.class,Integer.class);
            System.out.println("指定构造器："+constructor1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 3.获取指定共有构造方法
        try {
            Constructor constructor2=studentClass1.getConstructor(String.class);
            System.out.println("指定共有构造器："+constructor2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        /***4.获取非构造方法**************************************************/
        // 1.获取所有声明的函数
        Method[] declaredMethodList = studentClass1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethodList) {
            System.out.println("函数: " + declaredMethod);
        }
        // 2.获取所有公有的函数
        Method[] methodList = studentClass1.getMethods();
        for (Method method : methodList) {
            System.out.println("共有函数: " + method);
        }
    }
}
