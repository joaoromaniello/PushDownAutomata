package service;

import data.Automaton;
import data.Rule;

import java.util.List;

public class ConvertionService {

    public Automaton automato;


    public ConvertionService(Automaton a){
        this.automato = a;

    }


    public Automaton emptyToFinal(){

        return this.automato;


    }

    public Automaton finalToEmpty(){

        Automaton newAut = automato;

        String initialState = "qI" ;

        //ADICIONA UM ESTADO INICIAL NOVO O QUAL APONTA PARA O ESTADO INICIAL ANTIGO, COM UMA TRANSIÇÃO ADICIONANDO O ANTIGO SIMBOLO INICIAL DA PILHA
         newAut.addRule(initialState,'_',newAut.getInitialState(),"ZX","X");

         newAut.addSymbolToStackAlphabet("X"); //Adiciona X ao alfabeto de simbolos da pilha
         newAut.addInitialSymbol("X"); //Adiciona X como um novo estado inicial


        String finalState = "qF";
        List<String> finalStates = newAut.getFinalStates();

        //Adiciona transições epsilon de todos os estados finais
        //Para o novo "estado final"

        for(int i = 0; i < finalStates.size();i++){
            for(int j = 0 ; j < newAut.getStackAlphabetSize();j++){
                newAut.addRule(finalStates.get(i),'_',finalState,"_",String.valueOf(newAut.getStackAlphabet().charAt(j)));}
        }

         newAut.clearFinalState();

         newAut.changeInitialState(initialState);




         for (int i = 0; i < newAut.getAlphabet().length() ; i++){
             newAut.addRule(finalState,'_',finalState,"_",String.valueOf(newAut.getAlphabet().charAt(i)));
         }

        return newAut;


    }

}
