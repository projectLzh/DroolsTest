package com.rulesHello;

import com.pojo.Person;
import jdk.internal.org.objectweb.asm.Handle;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RulesHello {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("testhelloworld");
        ks.addEventListener(new DebugAgendaEventListener());
        ks.addEventListener(new DebugRuleRuntimeEventListener());
        kss.getLoggers().newThreadedFileLogger(ks,"./Threaded",1000);
        kss.getLoggers().newFileLogger(ks,"./droolsLog");
        kss.getLoggers().newConsoleLogger(ks);
        Person person=new Person();
        person.setName("张三");
        person.setAge(30);
        person.setDate(sdf.parse(sdf.format(new Date())));
        person.setLdate(sdf.parse("2018-12-13"));
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println(person.getAge());
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }
}

