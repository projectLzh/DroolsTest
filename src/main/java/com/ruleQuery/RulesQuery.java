package com.ruleQuery;

import com.pojo.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

public class RulesQuery {
    @Test
    public void testQuery() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isQuery");
        Person person1 = new Person("张三", 35);
        Person person2 = new Person("李四", 30);
        Person person3 = new Person("王五", 50);
        ks.insert(person1);
        ks.insert(person2);
        ks.insert(person3);
        QueryResults queryResults = ks.getQueryResults("person age is 30");
        for (QueryResultsRow q : queryResults) {
            Person p= (Person) q.get("person");
            System.out.println("输出符合查询条件的实体对象的name为"+p.getName());
        }
        ks.dispose();

    }

    @Test
    public void testQuery2() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks =kc.newKieSession("isQuery");
        Person person1 = new Person("张三", 35);
        Person person2 = new Person("李四", 30);
        Person person3 = new Person("王五", 50);
        Person person4 = new Person("张小三", 30);
        ks.insert(person1);
        ks.insert(person2);
        ks.insert(person3);
        ks.insert(person4);
        Object[] objects=new Object[]{"张小三"};
        QueryResults queryResults = ks.getQueryResults("person age is 30 and name is 张小三",objects);
        for (QueryResultsRow q : queryResults) {
            Person p= (Person) q.get("person");
            System.out.println("输出符合查询条件的实体对象的name为"+p.getName());
        }
        ks.dispose();
    }
}
