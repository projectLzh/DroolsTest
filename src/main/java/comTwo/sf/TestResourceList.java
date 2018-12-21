package comTwo.sf;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestResourceList {
    private static final String RULES_PATH = "rules";
    private static List<File> getRuleFiles() throws IOException {
        List<File> list = new ArrayList<File>();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        File rootDir = new File(filePath);
        File[] files = rootDir.listFiles();
        for (File itemFile : files) {
            if (itemFile.isDirectory() && itemFile.getName().equals(RULES_PATH)) {
                for (File f : itemFile.listFiles()) {
                    if (f.getName().endsWith(".drl")) {
                        list.add(f);
                    }
                }
            }
        }
        return list;
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        ReleaseId rid = ks.newReleaseId("org.drools", "kiemodulemodel","1.0");
        kfs.generateAndWritePomXML(rid);
        KieModuleModel kModuleModel = ks.newKieModuleModel();
        kModuleModel.newKieBaseModel("kiemodulemodel")
                .newKieSessionModel("ksession");
        kfs.writeKModuleXML(kModuleModel.toXML());
        System.out.println(kModuleModel.toXML());
        for (File file : getRuleFiles()) {
            kfs.write("src/main/resources/" + file.getName(),ResourceFactory.newClassPathResource(RULES_PATH + File.separator + file.getName(), "UTF-8"));
        }
        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n"+ kb.getResults().toString());
        }
        KieContainer kContainer = ks.newKieContainer(rid);
        kContainer.updateToVersion( rid );
        KieSession kieSession = kContainer.newKieSession("ksession");
        kieSession.fireAllRules();
    }

}
