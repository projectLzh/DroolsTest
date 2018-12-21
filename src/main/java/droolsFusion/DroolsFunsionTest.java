package droolsFusion;

import com.pojo.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsFunsionTest {
    public static void main(String[] args) throws InterruptedException {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("droolsFusion");
        Person person1 = new Person("张三",50);
        Person person2 = new Person("张三",60);
        Person person3 = new Person("张三",70);
        ks.insert(person1);
        Thread.sleep(4000);
        ks.insert(person2);
        ks.insert(person3);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }
}
