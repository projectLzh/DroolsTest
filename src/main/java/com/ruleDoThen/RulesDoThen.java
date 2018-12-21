package com.ruleDoThen;

import com.pojo.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RulesDoThen{
    @Test
    public void testDoThen() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isDoThen");
        Person person=new Person();
        person.setName("张小三");
        person.setAge(35);
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void testDoThen2() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isDoThen");
        Person person=new Person();
        person.setName("张小三");
        person.setAge(35);
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        System.out.println("输出Person的name"+person.getName());
        ks.dispose();
    }
}
