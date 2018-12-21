package comCmp;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.util.ArrayList;
import java.util.List;

public class AA {

    public static void main(String[] args) {
        Resource dis = ResourceFactory.newFileResource("E:\\drools\\src\\main\\resources\\rules\\cmp\\test2.drl");
        KieHelper helper = new KieHelper();
        helper.addResource(dis, ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();
        List<NewReverseModel> list = newReverseModelList();
        ksession.setGlobal("newReverseModelList",list);
        NewReversePass newReversePass = new NewReversePass();
        newReversePass.setBranchOffice("北京总公司");
        newReversePass.setDepartment("计算机一部");
        newReversePass.setMoney(900);
        ksession.insert(newReversePass);
        ksession.fireAllRules();
        System.out.println(newReversePass.getWhiteListType());
    }


    public static List<NewReverseModel> newReverseModelList() {
        List<NewReverseModel> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            NewReverseModel model = new NewReverseModel();
            model.setBranchOffice("北京总公司");
            model.setDepartment("计算机一部");
            model.setWhiteListType("续约");
            model.setMinMoney(500);
            model.setMaxMoney(5000);
            list.add(model);
        }
        return list;
    }
}