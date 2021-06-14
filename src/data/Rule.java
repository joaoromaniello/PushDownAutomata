package data;

public class Rule {
    private final String sourceState; //Estado inicial
    private final char symbol; //Simbolo lido
    private final String targetState; //Proximo estado
    private final String stackSymbols;  //O que sera empilhado
    private final String stackTop; //Topo lido da pilha

    public Rule(String sourceState, char symbol, String targetState,String StackS,String StackT) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.targetState = targetState;
        this.stackSymbols = StackS; // O que sera empilhado caso o Topo da pilha for o mesmo que o StackTop
        this.stackTop = StackT;

    }

    public String getSourceState() {
        return sourceState;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getTargetStates() {
        return targetState;
    }

    public String getStackTop(){
        return this.stackTop;
    }

    public char setSymbol(char symbol) {
        return symbol = symbol;
    }

    @Override
    public String toString() {
        return "("+ sourceState + "," + symbol + "," + stackTop + ")" + " \u2192 " + "(" + targetState + "," + stackSymbols +")" ;
    }
}
