package comTwo.Listener;

import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class TestRuleListener {

    @Test
    public void testRuleIUDListener(){
        Resource drl = ResourceFactory.newClassPathResource("rulesTwo/Listener/isRuleRunTime.drl", TestRuleListener.class);
        KieHelper helper = new KieHelper();
        helper.addResource(drl, ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();
        ksession.addEventListener(new RuleTimeImpl());
        ksession.addEventListener(new AgendaImpl());
        int count = ksession.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ksession.dispose();
    }

    @Test
    public void testRuleAgendaImpl(){
        Resource drl = ResourceFactory.newClassPathResource("rulesTwo/Listener/isRuleRunTime.drl", TestRuleListener.class);
        KieHelper helper = new KieHelper();
        helper.addResource(drl, ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();
        ksession.addEventListener(new AgendaImpl());
        int count = ksession.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ksession.dispose();
    }
}
