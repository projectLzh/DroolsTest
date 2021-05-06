package ruleAdd;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class DynamicRuleService {

    public static final String RESOURCE_PATH = "src/main/resources/";
    public static final String DEFAULT_GROUP_ID = "com.kaw.rule";

    /**
     * 函数调用型规则中，每个规则文件的 ruleCode+ version 对应一个 KieContainer
     * 后续需要考虑缓存容量
     */
    protected Map<ReleaseId, KieContainer> containerMapper = new ConcurrentHashMap<>();

    private static DynamicRuleService INSTANCE = new DynamicRuleService();

    private DynamicRuleService() {
    }

    public static DynamicRuleService getInstance() {
        return INSTANCE;
    }

    /**
     * 获取规则会话，用来调用规则
     * @param ruleCode
     * @param version
     * @return
     */
    public KieSession getRuleSession(String ruleCode, String version) {
        return getRuleSession(DEFAULT_GROUP_ID, ruleCode, version);
    }

    /**
     * 获取规则会话，用来调用规则
     * @param ruleCode
     * @param version
     * @return
     */
    public KieSession getRuleSession(String groupId, String ruleCode, String version) {
        KieContainer container = getRuleContainer(groupId, ruleCode, version);
        return container.newKieSession();
    }

    /**
     * 编译规则内容；会覆盖上次编译
     *
     * @param content
     */
    public void buildRuleContent(String ruleCode, String version, String content) {
        buildRuleContent(DEFAULT_GROUP_ID, ruleCode, version, content);
    }
    /**
     * 编译规则内容；会覆盖上次编译
     *
     * @param content
     */
    public void buildRuleContent(String groupId ,String ruleCode, String version, String content) {
        KieServices kService = KieServices.Factory.get();
        ReleaseId releaseId = kService.newReleaseId(groupId, ruleCode, version);
        ;
        KieFileSystem fileSystem = kService.newKieFileSystem();
        fileSystem.generateAndWritePomXML(releaseId);

        String path = DynamicRuleService.RESOURCE_PATH + releaseId.getGroupId() + "/" + releaseId.getArtifactId() + "-"
                + releaseId.getVersion() + ".drl";
        System.out.println("### build rule content : " + path);
        fileSystem.write(path, content);
        KieBuilder kbuilder = kService.newKieBuilder(fileSystem);
        kbuilder.buildAll();

    }

    private ReentrantLock initContainerLock = new ReentrantLock();
    private KieContainer getRuleContainer(String groupId, String ruleCode, String version) {
        KieServices kService = KieServices.Factory.get();
        ReleaseId releaseId = kService.newReleaseId(groupId, ruleCode, version);

        KieContainer kContainer = containerMapper.get(releaseId);

        if (kContainer == null) {
            initContainerLock.lock();
            try {
                if (kContainer == null) {
                    kContainer = kService.newKieContainer(ruleCode, releaseId);
                    containerMapper.put(releaseId, kContainer);
                }
            } finally {
                initContainerLock.unlock();
            }
        }
        return kContainer;
    }
}
