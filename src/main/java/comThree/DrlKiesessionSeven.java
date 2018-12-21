package comThree;

import com.pojo.Person;
import com.pojo.School;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.kie.internal.utils.KieHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static comThree.RuleValueFile.myRuleFile1;
import static comThree.RuleValueFile.myRuleFile2;

public class DrlKiesessionSeven {
    private static int thread_num = 200;//线程数,设置同时并发线程数
    private static int client_num = 10;//访问次数

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
        StatelessKieSession kieSession2 = helper1.build(    ).newStatelessKieSession();
        StatelessKieSession kieSession1 = helper1.build(    ).newStatelessKieSession();
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
                    //设计两个List 同时将实体放在集合中
                    List cmds1 = new ArrayList();
                    List cmds2 = new ArrayList();
                    cmds2.add( CommandFactory.newInsert(person,"p2")   );
                    cmds1.add( CommandFactory.newInsert(school,"s1")   );
                    cmds1.add( CommandFactory.newInsert(person,"p1")   );
                    cmds2.add( CommandFactory.newInsert(school,"s2")   );
                    kieSession1.execute(CommandFactory.newBatchExecution(cmds1));
                    kieSession2.execute(CommandFactory.newBatchExecution(cmds2));

                    System.out.println(person.getName());
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
