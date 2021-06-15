package data;

import java.util.List;
import java.util.Stack;

public class Rule {
    private final String sourceState; //Estado inicial
    private final char symbol; //Simbolo lido
    private final String targetState; //Proximo estado
    private final String StackSymbols;  //O que sera empilhado
    private final String StackTop; //Topo lido da pilha

    public Rule(String sourceState, char symbol, String targetState,String StackS,String StackT) {
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

    public String getTargetState() {
        return targetState;
    }

    public String getStackTop(){
        return this.StackTop;
    }

    public char setSymbol(char symbol) {
        return symbol = symbol;
    }

    public String getStackSymbols() {
        return StackSymbols;
    }
    @Override
    public String toString() {
        if(symbol == '_' && !(StackSymbols.equalsIgnoreCase("_")) )
            return "("+ sourceState + "," + '\u025b' + "," + StackTop + ")" + " \u2192 " + "(" + targetState + "," + StackSymbols +")" ;

        if(!(symbol == '_') && (StackSymbols.equalsIgnoreCase("_")) )
            return "("+ sourceState + "," + symbol + "," + StackTop + ")" + " \u2192 " + "(" + targetState + "," + "\u025b" +")" ;

        if(symbol == '_' && StackSymbols.equalsIgnoreCase("_") )
            return "("+ sourceState + "," + '\u025b' + "," + StackTop + ")" + " \u2192 " + "(" + targetState + "," + "\u025b" +")" ;

        else
            return "("+ sourceState + "," + symbol + "," + StackTop + ")" + " \u2192 " + "(" + targetState + "," + StackSymbols +")" ;
    }
}
