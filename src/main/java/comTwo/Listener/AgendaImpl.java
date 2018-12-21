package comTwo.Listener;

import org.kie.api.event.rule.*;

public class AgendaImpl implements AgendaEventListener {
    @Override
    public void matchCreated(MatchCreatedEvent event) {
        System.out.println("输出matchCreated");
    }

    @Override
    public void matchCancelled(MatchCancelledEvent event) {
        System.out.println("输出matchCancelled"+event.getCause().name());
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        System.out.println("输出beforeMatchFired");
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        System.out.println("输出afterMatchFired");
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        System.out.println("输出agendaGroupPopped");
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
        System.out.println("输出agendaGroupPushed");
    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        System.out.println("输出beforeRuleFlowGroupActivated");
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        System.out.println("输出"+"afterRuleFlowGroupActivated");
    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        System.out.println("输出"+"beforeRuleFlowGroupDeactivated");

    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        System.out.println("输出"+"afterRuleFlowGroupDeactivated");

    }
}
