package com.rulesFunction;

public class FunctionStatic {

    public static void testStatic1(){
        System.out.println("输出一个无参无返回值的静态方法");
    }
    public static String testStatic2() {
        System.out.println("输出一个无参有返回值的静态方法");
        return "Hello";
    }
    public static void testStatic3(String name){
        System.out.println("输出一个有参无返回值的静态方法，输出参数是"+name);
    }
    public static String testStatic4(String name){
        System.out.println("输出一个有参有返回值的静态方法，输出参数是"+name);
        return name;
    }
}
