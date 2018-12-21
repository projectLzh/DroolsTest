package comThree;

import com.pojo.Person;
import com.pojo.School;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.conf.MultithreadEvaluationOption;
import org.kie.internal.utils.KieHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static comThree.RuleValueFile.myRuleFile1;
import static comThree.RuleValueFile.myRuleFile2;

public class DrlKiesessionFour {
    private static int thread_num = 200;//线程数,设置同时并发线程数
    private static int client_num = 2;//访问次数

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        KieHelper helper1 = new KieHelper();
        KieHelper helper2 = new KieHelper();
        long end = System.currentTimeMillis();
        System.out.println("输出创建KieHelper用的毫秒是="+(end - start));

        long startAddrule = System.currentTimeMillis();
        //分别将规则myRuleFile2 myRuleFile1 加载到虚拟文件中
        helper1.addContent(myRuleFile1, ResourceType.DRL);
        helper2.addContent(myRuleFile2, ResourceType.DRL);//*******
        long endAddrule = System.currentTimeMillis();
        System.out.println("导入规则所用到的毫秒是="+(endAddrule - startAddrule));

        long startNewKiesession = System.currentTimeMillis();
        KieSession kieSession1 = helper1.build(MultithreadEvaluationOption.YES  ).newKieSession();
        KieSession kieSession2 = helper2.build(MultithreadEvaluationOption.YES ).newKieSession();
        long endNewKiesession = System.currentTimeMillis();
        System.out.println("创建有状态Kiesession所用到的毫秒是="+(endNewKiesession - startNewKiesession));

        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semp = new Semaphore(thread_num);
        for (int index = 0; index < client_num; index++) {
            Runnable run = () -> {
                try {
                    semp.acquire();
                    long startrule = System.currentTimeMillis();
                    Person person = new Person();
                    person.setName("张三");
                    person.setAge((int) (Math.random() * 100));
                    School school = new School();
                    school.setClassName("北大");
                    school.setClassCount("40");
                    kieSession1.insert(person);
                    kieSession1.insert(school);
                    kieSession2.insert(person);
                    kieSession2.insert(school);
                    int s2 = kieSession2.fireAllRules();
                    int s1 = kieSession1.fireAllRules();
                    System.out.println("kieSession1执行规则"+s1);
                    System.out.println("kieSession2执行规则"+s2);
                    System.out.println("修改后的"+person.getName());
                    //kieSession1.dispose();//注释的原因  同一个Kiesession有状态 是线程以外创建的Kiesession 所以必须要注释
                    //kieSession2.dispose();//注释的原因  同一个Kiesession有状态 是线程以外创建的Kiesession 所以必须要注释
                    long endrule = System.currentTimeMillis();
                    System.out.println("规则执行所用到的毫秒是="+(endrule - startrule));
                    System.out.println("=============================牛逼的分割线=========================================");
                    semp.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            exec.execute(run);
        }
        exec.shutdown();
    }
}
