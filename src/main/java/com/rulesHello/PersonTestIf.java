package com.rulesHello;

import com.pojo.Person;

public class PersonTestIf {
    public static void main(String[] args) {
        //现实代码肯定是通过其他方法传入的值，这里我们就写死了
        Person person=new Person();
        person.setName("张三");
        person.setAge(30);
        if("张三".equals(person.getName()) && "30".equals(person.getAge())){
            System.out.println("输出 传入的参数中确实有一位叫张三年龄在30岁的人");
            //... 再处理其他业务
        }
    }
}
