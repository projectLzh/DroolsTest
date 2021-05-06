package com.pi;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laizhihui on 2020/8/3
 */
public class PatternListDemo {

    public static void main(String[] args) {
        List<SystemListDemo> systemList = new ArrayList<>();
        systemList.add(new SystemListDemo("卵巢"));
        systemList.add(new SystemListDemo("子宫"));
        systemList.add(new SystemListDemo("女性"));
        systemList.add(new SystemListDemo("输卵管"));
        systemList.add(new SystemListDemo("甲胎蛋白"));


        List<UserListDemo> userList = new ArrayList<>();
        userList.add(new UserListDemo("子宫内膜"));
        userList.add(new UserListDemo("胎且不包含甲胎蛋白"));
        userList.add(new UserListDemo("氨基酸"));
        DemoTest demoTest = new DemoTest();
        demoTest.setSystemList(systemList);
        demoTest.setUserList(userList);


        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("rulePatternSession");
        ks.setGlobal("al",new ArrayList<>());
        ks.insert(demoTest);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        List al = (List) ks.getGlobal("al");


        ks.dispose();
    }
}

