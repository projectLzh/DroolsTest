package com.rulesConstraint;

import com.pojo.Person;
import com.pojo.School;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;

public class RulesCollection {
    @Test
    public void testList() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("collection");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("一班");
        School school = new School();
        List list=new ArrayList();
        list.add("一班");
        list.add("二班");
        list.add("三班");
        school.setClassNameList(list);
        Set classNameSet = new HashSet();
        classNameSet.add("一班");
        classNameSet.add("二班");
        classNameSet.add("三班");
        school.setClassNameSet(classNameSet);
        Map classNameMap = new HashMap();
        classNameMap.put("一班","1");
        classNameMap.put("二班","2");
        classNameMap.put("三班","3");
        school.setClassNameMap(classNameMap);
        ks.insert(person);
        ks.insert(school);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }


    @Test
    public void testInsertList() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("collectionInsert");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("一班");
        School school = new School();
        List list=new ArrayList();
        list.add("一班");
        list.add("二班");
        list.add("三班");
        school.setClassNameList(list);
        Set classNameSet = new HashSet();
        classNameSet.add("一班");
        classNameSet.add("二班");
        classNameSet.add("三班");
        Map classNameMap = new HashMap();
        classNameMap.put("一班","1");
        classNameMap.put("二班","2");
        classNameMap.put("三班","3");
        ks.insert(list);
        ks.insert(classNameSet);
        ks.insert(classNameMap);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }


}
