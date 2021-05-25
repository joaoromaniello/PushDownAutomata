package data;

import java.util.List;
import java.util.Stack;

public class RuleDTO {
    private final String sourceState;
    private final char symbol;
    private final List<String> targetState;
    private final boolean emptyStateRule;
    private final char StackTop;
    private final char StackSymbol;


    public RuleDTO(String sourceState, char symbol, List<String> targetState, boolean emptyStateRule,char StackTop,char StackSymbol) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.targetState = targetState;
        this.emptyStateRule = emptyStateRule;
        this.StackTop = StackTop;
        this.StackSymbol = StackSymbol;
    }

    public String getSourceState() {
        return sourceState;
    }

    public char getSymbol() {
        return symbol;
    }

    public List<String> getTargetStates() {
        return targetState;
    }

    public boolean getEmptyStateRule() {
        return emptyStateRule;
    }

    public char getStackSymbol(){
        return StackSymbol;
    }

    public char getStackTop(){
        return StackTop;
    }

    @Override
    public String toString() {
        return "RuleDTO{" +
                "sourceState='" + sourceState + '\'' +
                ", symbol=" + symbol +
                "stackTop="+ StackTop +
                ", targetState=" + targetState +
                ", emptyStateRule=" + emptyStateRule +
                '}';
    }

}
