package comTwo.sf;

import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.IOException;

public class TestResource {
    @Test
    public void  testPathClasses() throws Exception {
        Resource drl = ResourceFactory.newClassPathResource("rules/isSalience/salience.drl");
        KieHelper helper = new KieHelper();
        helper.addResource(drl, ResourceType.DRL);
        KieSession session = helper.build().newKieSession();
        session.fireAllRules();
        session.dispose();
    }
    @Test
    public  void testFilePath() throws IOException {
        Resource dis = ResourceFactory.newFileResource( "D:\\project\\drools\\src\\main\\resources\\rules\\isSalience\\salience.drl" );
        KieHelper helper = new KieHelper();
        helper.addResource(dis,ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();
        int i = ksession.fireAllRules();
        System.out.println( "		" + i + "次");
        ksession.dispose();
    }

}
