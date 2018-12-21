package com.rulesAccumulate;

import com.pojo.Person;
import com.pojo.School;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class RuleAccumulate {

    @Test
    public void testAccumulate(){
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isAccumulate");
        Person person=new Person();person.setName("张三");person.setAge(50);  ks.insert(person);
        Person person2=new Person();person2.setName("李四");person2.setAge(20);  ks.insert(person2);
        Person person3=new Person();person3.setName("王五");person3.setAge(25);  ks.insert(person3);
        Person person4=new Person();person4.setName("赵六");person4.setAge(15);  ks.insert(person4);
        int count = ks.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }

    @Test
    public void testAccumulate2(){
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isAccumulate");
        Person person=new Person();person.setName("张三");person.setAge(50);person.setDous(5.0);  ks.insert(person);
        Person person2=new Person();person2.setName("李四");person2.setAge(20);  ks.insert(person2);
        Person person3=new Person();person3.setName("王五");person3.setAge(25);  ks.insert(person3);
        Person person4=new Person();person4.setName("赵六");person4.setAge(15);  ks.insert(person4);
        int count = ks.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }

    @Test
    public void testAccumulate3(){
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("customize");
        Person person=new Person();person.setName("张三");person.setAge(1);  ks.insert(person);
        Person person2=new Person();person2.setName("李四");person2.setAge(2);  ks.insert(person2);
        Person person3=new Person();person3.setName("王五");person3.setAge(3);  ks.insert(person3);
        Person person4=new Person();person4.setName("赵六");person4.setAge(4);  ks.insert(person4);
        int count = ks.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }
}
