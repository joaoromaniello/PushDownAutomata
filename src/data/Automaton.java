package data;


import java.util.List;
import java.util.Stack;

public class Automaton{
    //Constroi o automato e sua sétupla
    private final List<String> states;      //conjunto de estados
    private final String alphabet;          //alfabeto da entrada
    private  String stackAlphabet;     //alfabeto da pulha
    private  List<Rule> rules;         //conjunto de regras
    private  String initialState;      //estado inicial
    private  List<String> finalStates; //estados finais
    private  String initialSymbol;     //simbolo inicial da pilha

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

    public String getInitialSymbols(){
        return initialSymbol;

    }

    public String getFinalState(){
        return finalStates.toString();
    }

    public int changeInitialState(String newState){

        for(int i = 0; i< this.states.size();i++){
            if(states.get(i).equalsIgnoreCase(newState)){ //Caso esse estado pertencer ao automato
            this.initialState = newState;
            return 1; //obteve sucesso mudando o estado final
            }


        }
          return 0; //nao obteve sucesso mudando o estado inicial
    }

    public int addNewState(String newState){
          this.states.add(newState);

        return 1;
    }

    public int addRule(String base,char simbolo,String proximo ,String SimboloPilha,String Topo){

        int aux1 = 0;  //Variavel para evitar que entre mais vezes na condição
        int aux2 = 0; //Variavel para evitar que entre mais vezes na condição

        for(int i = 0 ; i < this.getStates().size();i++){
            String simboloAtual = this.getStates().get(i);
            if(simboloAtual.contentEquals(base) == true ) {//O estado base da regra  existe nos estado do automato
                aux1 = 1;
            }
            if( simboloAtual.equals(proximo) == true) { //O proximo estado da regra   existe nos estado do automato
                aux2 = 1;
            }


        }

        if(aux1 == 0)
            this.addNewState(base); //adiciona o estado à lista de estados

        if(aux2 == 0)
            this.addNewState(proximo); //adiciona o estado à lista de estados


        Rule newRule = new Rule(base,simbolo,proximo,SimboloPilha,Topo); //Cria uma regra nova

        this.rules.add(newRule); //adiciona essa regra ao conjunto de regras



      return 1;
    }

    public int clearFinalState(){
        this.finalStates.clear();

        return 1;

    }

    public int addSymbolToStackAlphabet(String newSymbol){

        this.stackAlphabet = newSymbol.concat(this.stackAlphabet);

        return 1;

    }

    public int addInitialSymbol(String newSymbol){

        this.initialSymbol = newSymbol.concat(this.initialSymbol);

        return 1;


    }

    public int getStackAlphabetSize(){
        return stackAlphabet.length();

    }

    public String getStackAlphabet(){
        return this.stackAlphabet;

    }
    @Override
    public String toString(){
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

        StringBuilder outputStackInitialSymbol = new StringBuilder();
        for(int i = 0; i < initialSymbol.length(); i++) {
            outputStackInitialSymbol.append(initialSymbol.charAt(i));
            if (i != initialSymbol.length()-1) {
                outputStackInitialSymbol.append(", ");
            }
        }


        return "Q: " + states + ",\n" +
                "\u03A3: " + outputAlphabet + ",\n" +
                "\u0393: ["  + outputStackAlphabet + "],\n" +
                "\u03B4: " + outputRules + ",\n" +
                "Z0: [" + outputStackInitialSymbol + "],\n" +
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


