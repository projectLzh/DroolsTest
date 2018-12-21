package com.ruleKiesession;

import com.pojo.Person;
import com.pojo.School;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

import java.util.ArrayList;
import java.util.List;

public class RuleKiesession {
    @Test
    public void testkieSessionType() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isKiesession");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }
    @Test
    public void testkieSessionType2() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("kiesession");
        Person person = new Person();
        person.setName("aaaa");
        person.setAge(11);
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("主线程总执行了" + count + "条规则");
        testkieSessionType2();
        //ks.dispose();
    }

    @Test
    public void testStatelessKIESession() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        StatelessKieSession kieSession = kc.newStatelessKieSession("stateless");
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);
        kieSession.execute(person);
    }

    @Test
    public void testStatelessKIESession2() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        StatelessKieSession kieSession = kc.newStatelessKieSession("stateless");
        Person person = new Person();
        person.setName("张三");
        School school=new School();
        school.setClassName("一班");
        kieSession.execute(person);
        kieSession.execute(school);
    }
    @Test
    public void testStatelessKIESession3() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        StatelessKieSession kieSession = kc.newStatelessKieSession("stateless");
        Person person = new Person();
        person.setName("张三");
        School school=new School();
        school.setClassName("一班");
        List cmds = new ArrayList();
        cmds.add( CommandFactory.newInsert(person) );
        cmds.add( CommandFactory.newInsert(school) );
        kieSession.execute( CommandFactory.newBatchExecution( cmds ));
    }


    public static void main(String[] args) throws InterruptedException {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("kiesession2");
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setAge(i);
            ks.insert(person);
            ks.insert(person);
            //int count = ks.fireAllRules();
           /* MyThread myThread = new MyThread(ks, person);
            Thread tempThread = new Thread(myThread);
            tempThread.start();
            Thread.sleep(100);*/
        }
        Person person = new Person();
        person.setName("aaaa");
        person.setAge(11);
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("主线程总执行了" + count + "条规则");
        //ks.dispose();
    }


}

/**
 * 自定义线程查询 Sql 并启动 自定义线程 MyThreadFile
 */
class MyThread implements Runnable {
    private KieSession kieSession;
    private Person person;

    public MyThread(KieSession kieSession, Person person) {
        this.kieSession = kieSession;
        this.person = person;
    }

    public void run() {
        try {
            person.setName(Thread.currentThread().getName());
            kieSession.insert(person);
            int count = kieSession.fireAllRules();
            System.out.println("子线程总执行了" + count + "条规则");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}