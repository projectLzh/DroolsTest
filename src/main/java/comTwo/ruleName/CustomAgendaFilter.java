package comTwo.ruleName;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

import java.util.Set;

public class CustomAgendaFilter  implements AgendaFilter {
    private final Set<String> ruleNamesThatAreAllowedToFire;//传入的rule name
    public CustomAgendaFilter(Set<String> ruleNamesThatAreAllowedToFire) {
        this.ruleNamesThatAreAllowedToFire = ruleNamesThatAreAllowedToFire;
    }
    @Override
    public boolean accept(Match match) {
        return ruleNamesThatAreAllowedToFire.contains(match.getRule().getName());
    }
}
