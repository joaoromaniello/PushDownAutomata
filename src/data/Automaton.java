package data;


import service.ConversionService;

import java.util.List;

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

    public void changeInitialState(String newState){

        for (String state : this.states) {
            if (state.equalsIgnoreCase(newState)) { //Caso esse estado pertencer ao automato
                this.initialState = newState;

                break;
            }
        }
    }

    public void addNewState(String newState){
          this.states.add(newState);

    }

    public void addRule(String base, char simbolo, String proximo , String SimboloPilha, String Topo){

        int aux1 = 0;  //Variavel para evitar que entre mais vezes na condição
        int aux2 = 0; //Variavel para evitar que entre mais vezes na condição

        for(int i = 0 ; i < this.getStates().size();i++){
            String simboloAtual = this.getStates().get(i);
            if(simboloAtual.contentEquals(base)) {//O estado base da regra  existe nos estado do automato
                aux1 = 1;
            }
            if(simboloAtual.equals(proximo)) { //O proximo estado da regra   existe nos estado do automato
                aux2 = 1;
            }
        }

        if(aux1 == 0)
            this.addNewState(base); //adiciona o estado à lista de estados

        if(aux2 == 0)
            this.addNewState(proximo); //adiciona o estado à lista de estados

        Rule newRule = new Rule(base,simbolo,proximo,SimboloPilha,Topo); //Cria uma regra nova

        this.rules.add(newRule); //adiciona essa regra ao conjunto de regras
    }

    public void clearFinalState(){
        this.finalStates.clear();
    }

    public void addSymbolToStackAlphabet(String newSymbol){
        this.stackAlphabet = newSymbol.concat(this.stackAlphabet);
    }

    public void changeInitialSymbol(String newSymbol){
        this.initialSymbol = newSymbol;
    }

    public int getStackAlphabetSize(){
        return stackAlphabet.length();
    }

    public String getStackAlphabet(){
        return this.stackAlphabet;
    }

    public Automaton pdaTransformation(){

        int aux = -1;  //Variavel para saber qual o tipo do automato o qual estamos fazendo o processamento

        if(this.getFinalStates().size() == 0)
            aux = 0; //Automato por pilha vazia
        else
            aux = 1;  //Automato por estado final

        //caso o automato seja um automato por estado final
        if(aux == 1){
            ConversionService b = new ConversionService(this);
            return b.finalToEmpty();
        }

        //caso o automato seja um automato por pilha vazia
        else if(aux == 0){
            ConversionService b = new ConversionService(this);
            return b.emptyToFinal();
        }

        else
            return this;
    }

    public boolean isFinalState(String a){

        for(int i = 0; i<this.finalStates.size();i++){
            if(a.equals(this.finalStates.get(0)));
            return true;
        }
        return false;
    }

    public int identifyType(){
        int aux;  //Variavel para saber qual o tipo do automato o qual estamos fazendo o processamento

        if(this.getFinalStates().size() == 0)
            aux = 0; //Automato por pilha vazia

        else
            aux = 1;  //Automato por estado final

        return aux;
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

    public void changeFinalState(String a) {
        //Limpa o antigo estado final
        this.clearFinalState();

        for (String state : this.states) {//Caso esse estado pertencer ao automato
            if (state.equalsIgnoreCase(a)) { //
                this.finalStates.add(a);
                break;
            }
        }
    }
}