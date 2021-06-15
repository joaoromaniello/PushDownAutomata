package service;

import data.Automaton;
import data.Rule;
import jdk.swing.interop.SwingInterOpUtils;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static java.util.Collections.emptySet;

public class PDAService {

    Automaton automaton;
    Stack <String> pilha = new Stack();
    Boolean acceptance;

    public PDAService(Automaton automaton){
        this.automaton = automaton;
        pilha.push(String.valueOf(automaton.getInitialSymbols().charAt(0))); //Adiciona o simbolo inicial à pilha

    }

    public void proccesSequence(String sequence,String actualState,Stack a,String baseNode,Stack baseStack,String baseSequence){
        testarCadeia(0,sequence,pilha,automaton.getInitialState());

    }

    public Boolean testarCadeia(int position, String cadeia, Stack <String> pilha,String currentState){

        if(position == cadeia.length() && pilha.isEmpty())
            return true;

        if(pilha.isEmpty())
            return false;

        String topoPilha = pilha.pop();

        List <Rule> regras = getRules(currentState,cadeia.charAt(position),topoPilha);

        for(Rule regra: regras){


        }
    }

    public char readStackTop(Stack stack){

       char a = (char) stack.pop();

        stack.push(a);

        return a;


    }

    public Rule getApplicableRule(String State,Automaton a,String sequence){

        char symbol = sequence.charAt(0);
        int aux = 0;
        List<Rule> rules= new ArrayList<>();
        for(int i =0 ; i < a.getRules().size() ; i++){
            if(a.getRules().get(i).getSourceState().equals(State) &&  symbol == a.getRules().get(i).getSymbol() && String.valueOf(readStackTop(pilha)).equalsIgnoreCase(a.getRules().get(i).getStackTop()) ){ //Encontra regras que começam com o mesmo simbolo
                rules.add(a.getRules().get(i));
                aux = 1;
                break;
            }
        }

        if(aux == 1){
            return rules.get(0);
        }

        else{
            Rule regraZerada = new Rule(null,'@',null,null,null);
            return regraZerada;

        }

    }

    public Rule getApplicableTran(String State,Automaton a, String sequence){
        char symbol = sequence.charAt(0);
        int aux = 0;
        List<Rule> rules= new ArrayList<>();
        for(int i =0 ; i < a.getRules().size() ; i++){
            if(a.getRules().get(i).getSourceState().equals(State)  && String.valueOf(readStackTop(pilha)).equalsIgnoreCase(a.getRules().get(i).getStackTop()) && String.valueOf(a.getRules().get(i).getSymbol()).equalsIgnoreCase("_")){ //Encontra regras de transição
                rules.add(a.getRules().get(i));
                aux = 1;
                break;
            }
        }
        if(aux == 1){
            return rules.get(0);
        }

        else{
            Rule regraZerada = new Rule(null,'@',null,null,null);
            return regraZerada;

        }


    }

    public List <Rule> getRules(String state, char symbol, String topo){

        List <Rule> regras = new ArrayList<>();

        for(int i = 0; i < this.automaton.getRules().size();i++){
            if(this.automaton.getRules().get(i).getSourceState().equalsIgnoreCase(state) &&
                this.automaton.getRules().get(i).getSymbol() == symbol &&
                this.automaton.getRules().get(i).getStackTop().equalsIgnoreCase(topo)){
                regras.add(this.automaton.getRules().get(i));
            }

        }

        return regras;
    }

    public Stack getPdaStack(){

        return pilha;
    }

    public Boolean getAcceptance() {

    return acceptance;
    }


}
