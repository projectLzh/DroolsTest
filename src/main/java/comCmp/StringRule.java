package comCmp;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.math.BigDecimal;
import java.util.Date;

public class StringRule {

    public static void main(String[] args) {
        PaymentDaysRuleEngineBO engine = new PaymentDaysRuleEngineBO();
        Resource dis = ResourceFactory.newFileResource("E:\\drools\\src\\main\\resources\\rules\\cmp\\test2.drl");
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();
        Date date = DateUtil.lastNDays(new Date(), 1000);
        Date date1 = DateUtil.lastNDays(new Date(), 1000);
        engine.setEnterpriseRegisteredTime(date);
        engine.setCrmCreateTime(date1);
        engine.setHalfYearAvgAmount(new BigDecimal(1));
        engine.setContinuousMonths(4);
        engine.setEnterpriseRiskScore(35000);
        engine.setEnterpriseClass(2);
        ksession.insert(engine);
        ksession.fireAllRules();
    }
}