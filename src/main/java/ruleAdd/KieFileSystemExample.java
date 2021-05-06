package ruleAdd;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.PrintStream;

/**
 * @author laizhihui
 */
public class KieFileSystemExample {

    public void go(PrintStream out) {
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        kfs.write("src/main/resources/rules/test.drl", getRule());

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.


        ReleaseId defaultReleaseId = kr.getDefaultReleaseId();
        KieContainer kContainer = ks.newKieContainer(defaultReleaseId);

        KieSession kSession = kContainer.newKieSession();
        kSession.setGlobal("out", out);

        kSession.insert(new Message("Dave", "aa"));
        kSession.fireAllRules();
    }

    public static void main(String[] args) {
        new KieFileSystemExample().go(System.out);
    }


    private static String getRule() {
        String s = "" +
                "package org.drools.example.api.kiefilesystem \n\n" +
                "import org.drools.example.api.kiefilesystem.Message \n\n" +
                "global java.io.PrintStream out \n\n" +
                "rule \"rule 1\" when \n" +
                "    m : Message( ) \n" +
                "then \n" +
                "    out.println( m.getName() + \": \" +  m.getText() ); \n" +
                "end \n" +
                "rule \"rule 2\" when \n" +
                "    Message( text == \"aa\" ) \n" +
                "then \n" +
                "    insert( new Message(\"HAL\", \"Dave. I read you.\" ) ); \n" +
                "end";

        return s;
    }

}

