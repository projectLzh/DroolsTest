package com.rulesAttributes;

import com.pojo.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class rulesNoLoop {
    @Test
    public void testNoLoop1() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isNoLoop");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        person.setClassName("一班");
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }
}
