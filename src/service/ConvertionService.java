package service;

import data.Automaton;
import data.Rule;

import java.util.List;

public class ConvertionService {

    public Automaton automato;


    ConvertionService(Automaton a){
        this.automato = a;

    }


    Automaton emptyToFinal(){

        return this.automato;


    }

    Automaton finalToEmpty(){


        String initialState = "qI" ;

        //ADICIONA UM ESTADO INICIAL NOVO O QUAL APONTA PARA O ESTADO INICIAL ANTIGO, COM UMA TRANSIÇÃO ADICIONANDO O ANTIGO SIMBOLO INICIAL DA PILHA
         automato.addRule(initialState,'_',automato.getInitialState(),"ZX","X");

         automato.addSymbolToStackAlphabet("X"); //Adiciona X ao alfabeto de simbolos da pilha
         automato.addInitialSymbol("X"); //Adiciona X como um novo estado inicial


        String finalState = "qF";
        List<String> finalStates = automato.getFinalStates();

        //Adiciona transições epsilon de todos os estados finais
        //Para o novo "estado final"

        for(int i = 0; i < finalStates.size();i++){
            for(int j = 0 ; j < automato.getStackAlphabetSize();j++){
                automato.addRule(finalStates.get(i),'_',finalState,"_",String.valueOf(automato.getStackAlphabet().charAt(j)));}
        }

         automato.clearFinalState();

         automato.changeInitialState(initialState);




         for (int i = 0; i < automato.getAlphabet().length() ; i++){
             automato.addRule(finalState,'_',finalState,"_",String.valueOf(automato.getAlphabet().charAt(i)));
         }

        return this.automato;


    }

}
