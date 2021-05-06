package ruleAdd;

import org.kie.api.runtime.KieSession;

public class DynamicRuleDemo {

    public static void main(String[] args) {


        System.out.println("************run rule 11111");
        DynamicRuleService.getInstance().buildRuleContent( "rule-123123123", "1.0.0",getRule11111111()); // 如果规则有变化，则调用此方法编译规则。
        KieSession kSession = DynamicRuleService.getInstance().getRuleSession("rule-123123123", "1.0.0");
        kSession.setGlobal("out", System.out);
        kSession.insert(new Message("Dave", "aa"));
        kSession.fireAllRules();


        System.out.println("************run rule 22222");
        DynamicRuleService.getInstance().buildRuleContent("rule-456456456", "1.0.0", getRule2222222()); // 如果规则有变化，则调用此方法编译规则。
        KieSession kSession2 = DynamicRuleService.getInstance().getRuleSession("rule-456456456", "1.0.0");
        kSession2.setGlobal("out", System.out);
        kSession2.insert(new Message("Dave", "bb"));
        kSession2.fireAllRules();

        System.out.println("------------------------------------------");
        System.out.println("************run rule 11111 again ");
        kSession.insert(new Message("Dave", "aa"));
        kSession.fireAllRules();

        System.out.println("************run rule 22222 again ");
        kSession2.insert(new Message("Dave", "bb"));
        kSession2.fireAllRules();
    }


    private static String getRule11111111() {
        String s ="\n" +
                "package rules;\n" +
                "import ruleAdd.Message;\n global java.io.PrintStream out \n" +
                "rule \"test\"\n" +
                "    when\n" +
                "        $m:Message()\n" +
                "    then\n" +
                "        System.out.println($m);\n" +
                "end\n" +
                "\n" +
                "\n" +
                "rule \"test_A\"\n" +
                "    when\n" +
                "     $m:Message(text == \"aa\")\n" +
                "    then" +
                "      insert(new Message(\"Dave\",\"abc\" )); " +
                "\n" +
                "end\n";

        return s;
    }

    private static String getRule2222222() {
        String s ="package rules;\n" +
                "import ruleAdd.Message;\n global java.io.PrintStream out \n" +
                "rule \"test\"\n" +
                "    when\n" +
                "        $m:Message()\n" +
                "    then\n" +
                "        System.out.println($m);\n" +
                "end\n" +
                "\n" +
                "\n" +
                "rule \"test_B\"\n" +
                "    when\n" +
                "     $m:Message(text == \"bb\")\n" +
                "    then" +
                "      insert(new Message(\"Dave\",\"bcd\" )); " +
                "\n" +
                "end\n";
        return s;
    }
}