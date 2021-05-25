package data;

import java.util.List;
import java.util.Stack;

public class Rule {
    private final String sourceState;
    private final char symbol;
    private final List<String> targetState;
    private final String StackSymbols;
    private final String StackTop;

    public Rule(String sourceState, char symbol, List<String> targetState,String StackS,String StackT) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.targetState = targetState;
        this.StackSymbols = StackS; // O que sera empilhado caso o Topo da pilha for o mesmo que o StackTop
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
        return "("+ sourceState + "," + symbol + "," + StackTop + ")" + " \u2192 " + "(" + targetState + "," + StackSymbols +")" ;
    }
}
