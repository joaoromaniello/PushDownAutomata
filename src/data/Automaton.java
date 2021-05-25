package data;


import java.util.List;
import java.util.Stack;

public class Automaton{
    //Constroi o automato e sua sétupla
    private final List<String> states;      //conjunto de estados
    private final String alphabet;          //alfabeto da entrada
    private final String stackAlphabet;     //alfabeto da pulha
    private final List<Rule> rules;         //conjunto de regras
    private final String initialState;      //estado inicial
    private final List<String> finalStates; //estados finais
    private final String initialSymbol;     //simbolo inicial da pilha

    public Automaton(List<String> states, String alphabet, List<Rule> rules, String initialState, List<String> finalStates, String StackAlphabet,String initialSymbol) {
        this.states = states;
        this.alphabet = alphabet;
        this.rules = rules;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.stackAlphabet = StackAlphabet;
        this.initialSymbol = initialSymbol;
    }


    public List<Rule> getRules() {
        return rules;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public List<String> getStates() {
        return states;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public String getInitialSymbol(){
        return initialSymbol;

    }

    @Override
    public String toString() {
        StringBuilder outputRules = new StringBuilder();
        outputRules.append("[\n");
        for (int i = 0; i < rules.size(); i ++) {
            outputRules.append("    ").append(rules.get(i).toString());
            if (i != rules.size()-1) {
                outputRules.append(",");
            }
            outputRules.append("\n");
        }
        outputRules.append("]");


        StringBuilder outputAlphabet = new StringBuilder();
        outputAlphabet.append("[");
        for(int i = 0; i < alphabet.length(); i++) {
            outputAlphabet.append(alphabet.charAt(i));
            if (i != alphabet.length()-1) {
                outputAlphabet.append(", ");
            }
        }
        outputAlphabet.append("]");

        StringBuilder outputStackAlphabet = new StringBuilder();
        for(int i = 0; i < stackAlphabet.length(); i++) {
            outputStackAlphabet.append(stackAlphabet.charAt(i));
            if (i != stackAlphabet.length()-1) {
                outputStackAlphabet.append(", ");
            }
        }


        return "Q: " + states + ",\n" +
                "\u03A3: " + outputAlphabet + ",\n" +
                "\u0393: ["  + outputStackAlphabet + "],\n" +
                "\u03B4: " + outputRules + ",\n" +
                "q\u2080: " + initialState + ",\n" +

                "F: " + finalStates + "\n";
    }

    public void validateSequence(String sequence) throws Exception {
        for (int i = 0; i < sequence.length(); i++) {
            boolean found = false;
            for (int j = 0; j < alphabet.length(); j++) {
                if (sequence.charAt(i) == alphabet.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Elementos da cadeia devem pertencer ao alfabeto!");
            }
        }
    }
}
