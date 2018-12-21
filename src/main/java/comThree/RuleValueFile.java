package comThree;

public class RuleValueFile {
    //规则文件
    public static final String  myRuleFile1 = "package rules " +   "import  com.pojo.Person;" +    "import  com.pojo.School;" +
            "rule \"myRule1\" " +

            "   when " +
            "        $p:Person(name==\"张三\")" +
            "        $s:School(className==\"北大\")" +
            "   then " +
            "        $p.setName(\"李四\");" +
            "        $s.setClassName(\"清华\");" +
            "        update($p);" +
            "        update($s);" +
            "end                     " +
            "rule \"myRule2\" " +
            "   when " +
            "        $p:Person(name==\"李四\")" +
            "        $s:School(className==\"清华\")" +
            "   then " +
            "        System.out.println(\"规则myRuleFile1被调用\");" +
            "end";
    public static final String myRuleFile2 = "package rules " + "import  com.pojo.Person;" +    "import  com.pojo.School;" +
            "rule \"myRule3\" " +
            //" salience 10" +
            "   when " +
            "        $p:Person(name==\"张三\")" +
            "        $s:School(className==\"北大\")" +
            "   then " +
            "        $p.setName(\"王王\");" +
            "        $s.setClassName(\"张大\");" +
            "        update($p);" +
            "        update($s);" +
            "end                     " +
            "rule \"myRule4\" " +
            "   when " +
            "   $p:Person(name==\"王王\")" +
            "   $s:School(className==\"张大\")" +
            "   then " +
            " System.out.println(\"规则myRuleFile2调用\");" +
            "end";
}
