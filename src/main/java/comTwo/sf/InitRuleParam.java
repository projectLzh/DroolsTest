package comTwo.sf;

import com.pojo.Person;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel.KieSessionType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


public class InitRuleParam {
    //可自己配置日志功能
    private final static String PATH = "src/main/resources/rules";
    private final static String PATHt = "rules";
    private final static String GROUPID = "com.plus";
    private final static String ARTIFACTID = "plusRules";
    private final static String VERSION = "1.0";

    /**
     * KieServices对象得到一个KieContainer， 然后KieContainer根据session name来新建一个KieSession， 最后通过KieSession来运行规则
     *
     * KieServices : 该接口提供了很多方法，可以通过这些方法访问KIE关于构建和运行的相关对象，
     *               比如说可以获取KieContainer，利用KieContainer来访问KBase和KSession等信息；
     *               可以获取KieRepository对象，利用KieRepository来管理KieModule等。
     * KieServices : 就是一个中心，通过它来获取的各种对象来完成规则构建、管理和执行等操作。
     *
     * KieContainer: KieContainer就是一个KieBase的容器
     *
     * KieBase     : KieBase就是一个知识仓库，包含了若干的规则、流程、方法等，在Drools中主要就是规则和方法，
     * KieBase本身并不包含运行时的数据之类的，如果需要执行规则KieBase中的规则的话，就需要根据KieBase创建KieSession
     *
     * KieSession  : KieSession就是一个跟Drools引擎打交道的会话，其基于KieBase创建， 它会包含运行时数据，包含“事实Fact”，并对运行时数据事实进行规则运算。
     *通过KieContainer创建KieSession是一种较为方便的做法，其实他本质上是从KieBase中创建出来的。
     *               KieSession就是应用程序跟规则引擎进行交互的会话通道。
     *               创建KieBase是一个成本非常高的事情，KieBase会建立知识（规则、流程）仓库，
     *               而创建KieSession则是一个成本非常低的事情，所以KieBase会建立缓存，而KieSession则不必
     */
    public static KieContainer kContainer;
    public static KieServices kieServices;
    public static KieFileSystem kfs;
    public static KieModuleModel kmm;

    // 维护 kiesession
    /**
     * StatelessSession没有持久化上下文，也不提供多少高层的生命周期语义。特别是，无状态session不实现第一级cache,也不和第二级缓存，或者查询缓存交互。
     * 它不实现事务化写，也不实现脏数据检查。用stateless session进行的操作甚至不级联到关联实例。stateless session忽略集合类(Collections)。
     * 通过stateless session进行的操作不触发Hibernate的事件模型和拦截器。
     * 无状态session对数据的混淆现象免疫，因为它没有第一级缓存。无状态session是低层的抽象，和低层JDBC相当接近。
     */
    public static Map<String, StatelessKieSession> mapKiesession = new HashMap<String, StatelessKieSession>();
    static Map<String, String> fileMap = new HashMap<>();
    static KieContainer kc;


    public InitRuleParam() {
        System.out.println("开始初始化ksession");
        long star1 =System.currentTimeMillis();
        try {
            // 动态加载 drl (把 drl 内容读取出来拼接 动态生成 kiesession，相当于从数据库读取字符串 )
            Map<String, String> map = getFile("E:\\drools\\src\\main\\resources\\rules\\isKiesession");
            loadGeneral(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end =System.currentTimeMillis();
        System.err.println("初始总用时间:"+(end-star1));
        System.out.println("初始化成功， 总用时："+(end-star1));

        init();
    }

    /**
     * 测试
     */
    public  void init() {
        System.out.println("(3)  测试...");
        for (final Map.Entry<String, StatelessKieSession> entry : mapKiesession.entrySet()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StatelessKieSession kieSession = null;
                    try {
                        System.out.println("(3) 取出ksession");
                        kieSession = entry.getValue();
                        List<String> myGlobalList = new ArrayList<>(); // 违反规则，global
                        kieSession.setGlobal("myGlobalList", myGlobalList);

                        System.err.println(kieSession);
                        Person person = new Person("张三",30);
                        // 无状态
                        System.out.println("(3) 省略了有状态的insert fireAllRules");
                        /**
                         * 无状态会话不支持迭代调用，调用execute（...）的动作是一个单独的方法，将内部实例化KieSession，
                         * 添加所有用户数据并执行用户命令，调用fireAllRules，然后调用dispose（）
                         */
                        kieSession.execute(person);
                        System.out.println("(3) 获取违反规则长度为："+myGlobalList.size());
                        System.err.println(myGlobalList.size());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     *  把 字符串 动态生成 kiesession
     * @param map
     */
    private  void loadGeneral(Map<String, String> map) {
        System.out.println("(2) 动态生成kiesession...");
        kieServices = KieServices.Factory.get();
        kfs = kieServices.newKieFileSystem();
        // 创建 pom.xml
        ReleaseId rId = kieServices.newReleaseId(GROUPID, ARTIFACTID, VERSION);
        kfs.generateAndWritePomXML(rId);
        System.out.println("(2) 创建pom.xml文件成功");
        // 创建 kmoudle.xml
        kmm = kieServices.newKieModuleModel();
        System.out.println("(2) 创建kmoudle.xml文件成功");

        // 规则写入本地
        if (null != map && map.size() != 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String path = PATH + "/" + entry.getKey() + "/" + entry.getKey() + ".drl";
                kfs.write(path, entry.getValue());
                System.err.println(path);
                kmm.newKieBaseModel(entry.getKey() + "z").addPackage(PATHt + "." + entry.getKey())
                        .newKieSessionModel(entry.getKey()).setType(KieSessionType.STATELESS);
            }
        }
        System.out.println("(2) 组建kmoudle.xml文件成功");

        kfs.writeKModuleXML(kmm.toXML());
        KieBuilder kbd = kieServices.newKieBuilder(kfs);
        kbd.buildAll();
        System.out.println("(2) 编写kmoudle.xml文件成功，内容为："+kmm.toXML());

        kc = kieServices.newKieContainer(rId);

        kc.updateToVersion(rId);

        if (null != map && map.size() != 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                StatelessKieSession kSession = null;
                kSession = kc.newStatelessKieSession(entry.getKey());
                mapKiesession.put(entry.getKey(), kSession);
            }
        }
        System.out.println("(2) 动态生成Ksession,存入mapKiesession成功，长度为："+mapKiesession.size()+"...");
    }

    /**
     * 读取 drl 内容，文件名作为 key ,内容作为 value
     *
     * @param frold
     * @return
     * @throws Exception
     */
    private  Map<String, String> getFile(String frold) throws Exception {
        System.out.println("(1) 读取drl文件， 存入fileMap...");
        File f = new File(frold);
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (!fs.isDirectory()) {
                BufferedReader br = new BufferedReader(new FileReader(new File(fs.toString())));
                StringBuilder result = new StringBuilder();
                String s = null;
                while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                    result.append(System.lineSeparator() + s);
                }
                fileMap.put(fs.getName().replace(".drl", ""), result.toString());
                System.out.println("(1) 存入fileMap,key: "+fs.getName().replace(".drl", "")+"   value:"+result.toString());
            } else {
                System.err.println(fs.getName());
                System.out.println("(1) 改路径不正确："+fs.getName());
            }
        }

        System.out.println("(1) 读取drl文件， 存入fileMap完成切完成, map长度为："+fileMap.size()+"...");
        return fileMap;
    }

    public  static void main(String[] args) {
        new InitRuleParam();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        Collection<String> kieBaseNames = kieClasspathContainer.getKieBaseNames();
        for (String kname : kieBaseNames){
            System.out.println(kname);
        }
    }
}
