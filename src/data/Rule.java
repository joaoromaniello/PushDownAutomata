package data;

import java.util.List;
import java.util.Stack;

public class Rule {
    private final String sourceState;
    private final char symbol;
    private final List<String> targetState;
    private final char StackSymbol;
    private final char StackTop;
    public Rule(String sourceState, char symbol, List<String> targetState,char StackS,char StackT) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.targetState = targetState;
        this.StackSymbol = StackS; // O que sera empilhado caso o Topo da pilha for o mesmo que o StackTop
        this.StackTop = StackT;

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

    public char getStackTop(Stack a){
         char poppedChar = (char) a.pop();

        a.push(poppedChar);

        return poppedChar; //retorna o topo da pilha


    }

    public char setSymbol(char symbol) {
        return symbol = symbol;
    }

    @Override
    public String toString() {
        return "("+ sourceState + "," + symbol + "," + StackSymbol + ")" + " \u2192 " + targetState ;
    }
}
