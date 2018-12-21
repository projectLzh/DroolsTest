package comTwo.sf.kjar;

import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class DynamicDrlTest {
    private static final String RULESFILE_NAME = "rules.drl";
    /**
     * 规则文件内容（可以从数据库中加载）
     */
    private static final String ruleCoutext = "package rules rule \"Hello World 2\" when eval(true) then System.out.println(\"Test, Drools!\"); end";

    public static void main(String[] args) throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        //指定kjar包
        final ReleaseId releaseId = kieServices.newReleaseId("rule", "test", "1.0.0");
        // 创建初始化的kjar
        InternalKieModule kJar = DroolsUtils.createKieJar(kieServices, releaseId,
                new ResourceWrapper(ResourceFactory.newByteArrayResource(ruleCoutext.getBytes()), RULESFILE_NAME));
        KieRepository repository = kieServices.getRepository();
        repository.addKieModule(kJar);
        KieContainer kieContainer = kieServices.newKieContainer(releaseId);
        KieSession session = kieContainer.newKieSession();
        try {
            session.fireAllRules();
        } catch (Exception e) {
        } finally {
            session.dispose();
        }
        System.out.println("------------");
    }
}
