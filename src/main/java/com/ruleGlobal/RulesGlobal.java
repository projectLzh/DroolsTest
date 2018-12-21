package com.ruleGlobal;

import com.pojo.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;

public class RulesGlobal {
    @Test
    public void testGlobal() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isGlobal");
        Person person=new Person();
        person.setAge(2);
        person.setName("zs");
        ks.insert(person);
        ks.setGlobal("count",2017);
        ks.setGlobal("list",new ArrayList<>());
        ks.setGlobal("person",person);
        int count = ks.fireAllRules();
        System.out.println(ks.getGlobal("count"));
        System.out.println(ks.getGlobal("person").toString());
        System.out.println("总执行了"+count+"条规则");
        ks.dispose();
    }
}
