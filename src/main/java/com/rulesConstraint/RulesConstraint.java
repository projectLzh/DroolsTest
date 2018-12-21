package com.rulesConstraint;

import com.pojo.Person;
import com.pojo.School;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;

public class RulesConstraint {
    @Test
    public void contains() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("contains");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("20081");
        School school = new School();
        school.setClassName("2008,2009,2010,2011");
        ks.insert(person);
        ks.insert(school);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void memberOfArray() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("memberOf");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("一班");
        School school = new School();
        school.setClassName("一班");
        school.setClassNameArray(new String[]{"一班", "二班", "三班"});
        ks.insert(person);
        ks.insert(school);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void memberOfList() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("memberOf");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("一班");
        School school = new School();
        List classNameList = new ArrayList();
        classNameList.add("一班");
        classNameList.add("二班");
        classNameList.add("三班");
        school.setClassNameList(classNameList);
        ks.insert(person);
        ks.insert(school);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void memberOfSet() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("memberOf");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("一班");
        School school = new School();
        Set classNameSet = new HashSet();
        classNameSet.add("一班");
        classNameSet.add("二班");
        classNameSet.add("三班");
        school.setClassNameSet(classNameSet);
        ks.insert(person);
        ks.insert(school);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void memberOfMap() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("memberOf");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("一班");
        School school = new School();
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
    public void matches() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("matches");
        Person person = new Person();
        person.setName("zsxxx");
        person.setAge(30);
        person.setClassName("一班");
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }


    @Test
    public void soundslike() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("soundslike");
        Person person = new Person();
        person.setName("fubar");
        person.setAge(30);
        person.setClassName("一班");
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void str() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isStr");
        Person person = new Person();
        person.setName("张小三");
        person.setAge(30);
        person.setClassName("一班");
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }
}
