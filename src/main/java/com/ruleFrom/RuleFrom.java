package com.ruleFrom;

import com.pojo.Person;
import com.pojo.School;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class RuleFrom {
    @Test
    public void testFrom() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isFrom");
        Person person=new Person();
        person.setAge(30);
        person.setName("张三");
        person.setSchool(new School("一班"));
        ks.insert(person);
        ks.insert(new School("一班"));
        int count = ks.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }



    @Test
    public void testFromList() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isFrom");
        Person person=new Person();person.setName("张三");person.setClassName("一班");
        Person person2=new Person();person2.setName("李四");person2.setClassName("一班");
        Person person3=new Person();person3.setName("王五");person3.setClassName("二班");
        Person person4=new Person();person4.setName("赵六");person4.setClassName("一班");
        School school=new School();
        List classnameList=new ArrayList();
        classnameList.add(person);
        classnameList.add(person2);
        classnameList.add(person3);
        classnameList.add(person4);
        school.setClassNameList(classnameList);
        ks.insert(school);
        int count = ks.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }



    /**
     * 用来测试from  function的方法
     * @param name
     * @param integer
     * @return
     */
    public   static List<Object> listfrom(String name,Integer integer) {
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setName(name);
            person.setAge(integer+i);
            list.add(person);
        }
        return  list;
    }

}
