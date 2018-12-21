package com.ruleExtends;

import com.pojo.Person;
import com.pojo.School;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleExtends {
    @Test
    public void testExtends() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isExtends");
        Person person=new Person();
        person.setName("张小三");
        ks.insert(person);
        School school=new School();
        school.setClassName("一班");
        ks.insert(school);
        int count = ks.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }
}
