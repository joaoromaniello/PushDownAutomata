package data;

public class Rule {
    private final String sourceState; //Estado inicial
    private final char symbol; //Simbolo lido
    private final String targetState; //Proximo estado
    private final String targetStack; //O que sera empilhado
    private final String stackTop; //Topo lido da pilha

    public Rule(String sourceState, char symbol, String targetState, String StackS, String StackT) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.targetState = targetState;
        this.targetStack = StackS; // O que sera empilhado caso o Topo da pilha for o mesmo que o StackTop
        this.stackTop = StackT;
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

    public String getStackTop() {
        return this.stackTop;
    }

    public String getTargetStack() {
        return targetStack;
    }

    @Override
    public String toString() {
        if (symbol == '_' && !(targetStack.equalsIgnoreCase("_")))
            return "(" + sourceState + "," + '\u025b' + "," + stackTop + ")" + " \u2192 " + "(" + targetState + "," + targetStack + ")";

        if (!(symbol == '_') && (targetStack.equalsIgnoreCase("_")))
            return "(" + sourceState + "," + symbol + "," + stackTop + ")" + " \u2192 " + "(" + targetState + "," + "\u025b" + ")";

        if (symbol == '_' && targetStack.equalsIgnoreCase("_"))
            return "(" + sourceState + "," + '\u025b' + "," + stackTop + ")" + " \u2192 " + "(" + targetState + "," + "\u025b" + ")";

        else
            return "(" + sourceState + "," + symbol + "," + stackTop + ")" + " \u2192 " + "(" + targetState + "," + targetStack + ")";
    }
}
