package th.in.whs.thaisplit.engine;

import java.util.LinkedList;
import java.util.List;

public class RuleEvaluator<E, T> {
    private List<Rule<E, T>> rules;

    public RuleEvaluator(){
        rules = new LinkedList<>();
    }

    public void addRule(Rule<E, T> rule){
        rules.add(rule);
    }

    public T evaluate(E input){
        for(Rule<E, T> rule : rules){
            if(rule.match(input)){
                return rule.activate(input);
            }
        }
        return null;
    }
}
