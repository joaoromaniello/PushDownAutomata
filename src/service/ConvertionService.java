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

     //int counter  = automato.getStates().size() ; // cria um contador com o tamanho do automato
     String finalState = "qF";
        List<String> finalStates = automato.getFinalStates();

        //adiciona transições epsilon de todos os estados finais
        //para o estado pf ("final")

        for(int i = 0; i < finalStates.size();i++){
            automato.addRule(finalStates.get(i),'_',finalState,"X","X");
        }

        String initialState = "qI" ;

        //ADICIONA UM ESTADO INICIAL NOVO O QUAL APONTA PARA O ESTADO INICIAL ANTIGO, COM UMA TRANSIÇÃO ADICIONANDO O ANTIGO SIMBOLO INICIAL DA PILHA
         automato.addRule(initialState,'_',automato.getInitialState(),"ZX","X");

         automato.addSymbolToStackAlphabet("X"); //Adiciona X ao alfabeto de simbolos da pilha
         automato.addInitialSymbol("X"); //Adiciona X como um novo estado inicial


         automato.clearFinalState();

         automato.changeInitialState(initialState);




         for (int i = 0; i < automato.getAlphabet().length() ; i++){
             automato.addRule(finalState,'_',finalState,"_",String.valueOf(automato.getAlphabet().charAt(i)));
         }



        return this.automato;


    }

}
