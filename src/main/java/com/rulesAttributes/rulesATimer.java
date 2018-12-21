package com.rulesAttributes;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.TimedRuleExecutionOption;

public class rulesATimer {
    public static void main(String[] args) throws InterruptedException {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSessionConfiguration ksconf = KieServices.Factory.get().newKieSessionConfiguration();
        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        ksconf.setOption( TimedRuleExecutionOption.YES );
        KieBase kieBase = kc.newKieBase("isTimer", kieBaseConfiguration);
        KieSession kieSession = kieBase.newKieSession(ksconf, null);
        kieSession.fireAllRules();
        Thread.sleep(10000);
        kieSession.dispose();
    }
}
