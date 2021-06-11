package service;

import data.Automaton;

import java.util.List;

public class ConvertionService {

    public Automaton automato;

    public ConvertionService(Automaton a) {
        this.automato = a;
    }

    public Automaton emptyToFinal() {

        System.out.println("Automato original");
        System.out.println(automato.toString());

        Automaton newAut = automato;

        String initialState = "qI";

        //Gera uma lista com todos os estados
        List<String> allStates = newAut.getStates();

        //ADICIONA UM ESTADO INICIAL NOVO O QUAL APONTA PARA O ESTADO INICIAL ANTIGO, COM UMA TRANSIÇÃO ADICIONANDO O ANTIGO SIMBOLO INICIAL DA PILHA
        newAut.addRule(initialState, '_', newAut.getInitialState(), "ZX", "X");

        newAut.addSymbolToStackAlphabet("X"); //Adiciona X ao alfabeto de simbolos da pilha
        newAut.changeInitialSymbol("X"); //Adiciona X como um novo estado inicial

        String finalState = "qF";

        //Adiciona transições epsilon de todos os estados " (qn,_,X) -> (qf_X) "
        //Para o novo "estado final"
        for (int i = 0; i < allStates.size(); i++) {
            if (!allStates.get(i).equals(finalState) && !allStates.get(i).equals(initialState))
                newAut.addRule(allStates.get(i), '_', finalState, "X", "X");
        }

        // muda o novo estado inicial
        newAut.changeInitialState(initialState);

        //Cria o estado final sendo o estado criado
        newAut.changeFinalState(finalState);

        System.out.println("Automato modificado");
        System.out.println(newAut.toString());
        return newAut;
    }

    public Automaton finalToEmpty() {

        Automaton newAut = automato;

        String initialState = "qI";

        //ADICIONA UM ESTADO INICIAL NOVO O QUAL APONTA PARA O ESTADO INICIAL ANTIGO, COM UMA TRANSIÇÃO ADICIONANDO O ANTIGO SIMBOLO INICIAL DA PILHA
        newAut.addRule(initialState, '_', newAut.getInitialState(), "ZX", "X");

        newAut.addSymbolToStackAlphabet("X"); //Adiciona X ao alfabeto de simbolos da pilha
        newAut.changeInitialSymbol("X"); //Adiciona X como um novo estado inicial


        String finalState = "qF";
        List<String> finalStates = newAut.getFinalStates();

        //Adiciona transições epsilon de todos os estados finais
        //Para o novo "estado final"

        for (int i = 0; i < finalStates.size(); i++) {
            for (int j = 0; j < newAut.getStackAlphabetSize(); j++) {
                newAut.addRule(finalStates.get(i), '_', finalState, "_", String.valueOf(newAut.getStackAlphabet().charAt(j)));
            }
        }

        newAut.clearFinalState();
        newAut.changeInitialState(initialState);

        for (int i = 0; i < newAut.getAlphabet().length(); i++) {
            newAut.addRule(finalState, '_', finalState, "_", String.valueOf(newAut.getAlphabet().charAt(i)));
        }

        return newAut;
    }
}
