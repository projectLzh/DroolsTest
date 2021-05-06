package droolsTestKieSessionPool;

import com.pojo.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieContainerSessionsPool;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DroolsTestPool {
    KieServices kss = KieServices.Factory.get();
    KieContainer kc = kss.getKieClasspathContainer();
    KieContainerSessionsPool kieContainerSessionsPool = kc.newKieSessionsPool(10);
    /**
     * 不使用线程池创建有状态的KieSession 执行 1000000
     * 平均是6s
     */
    @Test
    public void testKieSession() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            KieSession testKieSession = kc.newKieSession("kieSession_rule");
            Person person = new Person();
            person.setName("张三");
            person.setAge(10);
            testKieSession.insert(person);
            testKieSession.dispose();
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }

    /**
     * 将New一个无状态的放在For循环里面创建
     * 不使用线程池创建有状态的KieSession 执行 1000000
     * 平均6.8s
     */
    @Test
    public void testStatelessSession_1() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            StatelessKieSession stateless = kc.newStatelessKieSession("kieSession_rule_stateless");
            Person person = new Person();
            person.setName("张三");
            person.setAge(10);
            stateless.execute(person);
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }

    /**
     * 在For循环外创建无状态KieSession
     * 不使用线程池创建有状态的KieSession 执行 1000000
     * 平均5.6s
     */
    @Test
    public void testStatelessSession_2() {
        StatelessKieSession stateless = kc.newStatelessKieSession("kieSession_rule_stateless");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Person person = new Person();
            person.setName("张三");
            person.setAge(10);
            stateless.execute(person);
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }


    /**
     * 使用线程池创建有状态的KieSession 执行 1000000
     * 平均是2.09s
     */
    @Test
    public void testPoolKieSession() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            KieSession testKieSession = kieContainerSessionsPool.newKieSession("kieSession_rule");
            Person person = new Person();
            person.setName("张三");
            person.setAge(10);
            testKieSession.insert(person);
            testKieSession.dispose();
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }
    /**
     * 在For循环外创建无状态KieSession
     * 使用线程池创建有状态的KieSession 执行 1000000
     * 平均3s
     */
    @Test
    public void testStatelessSessionPool_1() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            StatelessKieSession stateless = kieContainerSessionsPool.newStatelessKieSession("kieSession_rule_stateless");
            Person person = new Person();
            person.setName("张三");
            person.setAge(10);
            stateless.execute(person);
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }


    /**
     * 在For循环外创建无状态KieSession
     * 使用线程池创建有状态的KieSession 执行 1000000
     * 平均2s
     */
    @Test
    public void testStatelessSessionPool_2() {
        StatelessKieSession stateless = kieContainerSessionsPool.newStatelessKieSession("kieSession_rule_stateless");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <10000000; i++) {
            long startTime2 = System.currentTimeMillis();
            Person person = new Person();
            person.setName("张三");
            person.setAge(i);
            person.setDous(1.5);
            stateless.execute(person);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime2)+"每一次计算的结果");
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }

    public static void main(String[] args) {
        Map map=new HashMap<>();
        Map map2=new ConcurrentHashMap<>();
        List a=new CopyOnWriteArrayList();
        List b=new ArrayList();
    }
}
