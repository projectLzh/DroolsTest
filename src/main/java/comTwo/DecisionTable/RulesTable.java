package comTwo.DecisionTable;

import com.pojo.Person;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.template.ObjectDataCompiler;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RulesTable {
    @Test
    public void testisXls() {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("isXls");
        Person person=new Person();
        person.setName("张三");
        person.setAge(35);
        ks.insert(person);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        ks.dispose();
    }

    @Test
    public void  verificationDT() throws FileNotFoundException {
        File file = new File(
                "D:\\project\\drools\\src\\main\\resources\\rulesTwo\\isXls\\tableXls.xls");
        InputStream is = new FileInputStream(file);
        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        String drl = converter.compile(is, InputType.XLS);
        System.out.println(drl);
    }

    @Test
    public void  verificationDT2() throws FileNotFoundException {
        File file = new File(
                "E:\\drools\\src\\main\\resources\\rulesTwo\\isXls\\tableXlsS.xls");
        InputStream is = new FileInputStream(file);
        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        String drl = converter.compile(is, InputType.XLS);
        System.out.println(drl);
    }

    @Test
    public void testKieHelper() {
        Resource dis = ResourceFactory.newClassPathResource("rulesTwo/isXls/tableXls.xls", RulesTable.class);
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DTABLE);
        KieSession ksession = helper.build().newKieSession();
        int i = ksession.fireAllRules();
        System.out.println( "     " + i + "次");
        ksession.dispose();
    }

}
