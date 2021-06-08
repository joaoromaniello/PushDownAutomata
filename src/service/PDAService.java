package service;

import data.Automaton;
import data.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static java.util.Collections.emptySet;

public class PDAService {

    Automaton automaton;

    Stack pilha = new Stack();


    public PDAService(Automaton automaton){
        this.automaton = automaton;
        pilha.push(automaton.getInitialSymbols().charAt(0)); //Adiciona o simbolo inicial à pilha

    }

    public int proccesSequence(String sequence,String actualState,Stack a){

        ////CASO BASE...
        if(automaton.identifyType() == 0){  //por pilha vazia
            if(pilha.isEmpty() && sequence.length() == 0) {  //caso a pilha estiver vazia e a sequencia tiver sido consumida
                return 1; //cadeia valida
            }
        }
        ////CASO BASE...
        if(automaton.identifyType() == 1){  //por estado final
            if(automaton.isFinalState(actualState) && sequence.length() == 0){ //caso o estado atual for final e a sequencia tiver sido consumida
                return 1; //cadeia valida
            }
        }


        return 0;

    }



    public char readStackTop(Stack stack){

       char a = (char) stack.pop();

        stack.push(a);

        return a;


    }


    public List<Rule> getApplicableRules(String State,Automaton a,String sequence){

        char symbol = sequence.charAt(0);

        List<Rule> rules= new ArrayList<>();
        for(int i =0 ; i < a.getRules().size() ; i++){
            if(a.getRules().get(i).getSourceState().equals(State) &&  symbol == a.getRules().get(i).getSymbol() && String.valueOf(readStackTop(pilha)) == a.getRules().get(i).getStackTop() ){ //Encontra regras que começam com o mesmo simbolo
                rules.add(a.getRules().get(i));
            }
        }

        System.out.println(rules.toString());
        return rules;
    }

    public Stack getPdaStack(){

        return pilha;
    }



}
