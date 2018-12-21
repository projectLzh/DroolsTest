package com.rulesAttributes;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class rulesAgendaGroup {
    @Test
    public void testAgendaGroup() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isAgendaGroup");
        ks.getAgenda().getAgendaGroup("ag1").setFocus();//让AgendaGroup分组为ag1的获取焦点
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void testAgendaGroup2() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isAgendaGroup");
        ks.getAgenda().getAgendaGroup("ag6").setFocus();//让AgendaGroup分组为ag6的获取焦点
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void testAgendaGroup3() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isAgendaGroup");
        ks.getAgenda().getAgendaGroup("ag8").setFocus();//让AgendaGroup分组为ag8的获取焦点
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }
}
