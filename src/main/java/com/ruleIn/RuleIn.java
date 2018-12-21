package com.ruleIn;

import com.pojo.Person;
import com.pojo.School;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleIn {
    @Test
    public void testIn() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isIn");
        Person person=new Person();
        person.setClassName("三班");
        person.setAge(35);
        ks.insert(person);
        School school=new School();
        school.setClassName("三班");
        ks.insert(school);
        Person person2=new Person();
        person2.setClassName("四班");
        ks.insert(person2);
        int count = ks.fireAllRules();
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }
}
