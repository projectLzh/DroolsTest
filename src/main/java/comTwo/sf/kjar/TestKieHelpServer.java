package comTwo.sf.kjar;

import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;

public class TestKieHelpServer {
    public static void main(String[] args) {
        KieHelper kieHelper=new KieHelper();
        kieHelper.addContent(" package test.aa  rule \"boy_a\"\n " +
                "    when\n" +
                "    then\n" +
                "        System.out.println(\"boy_a\");\n" +
                "        drools.halt();\n" +
                "end\n" +
                "\n" +
                "rule \"boy_b\"\n" +
                "    when\n" +
                "    then\n" +
                "        System.out.println(\"boy_b\");\n" +
                "end " , ResourceType.DRL);
        KieBase build = kieHelper.build();
        build.newKieSession().fireAllRules();
    }
}
