package service;

import data.Automaton;

import java.util.List;

public class ConversionService {

    public Automaton automato;

    public ConversionService(Automaton a) {
        this.automato = a;
    }

    public void emptyToFinal() {

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
    }

    public void finalToEmpty() {

        Automaton newAut = automato;

        String initialState = "qI";

        //ADICIONA UM ESTADO INICIAL NOVO O QUAL APONTA PARA O ESTADO INICIAL ANTIGO, COM UMA TRANSIÇÃO ADICIONANDO O ANTIGO SIMBOLO INICIAL DA PILHA
        newAut.addRule(initialState, '_', newAut.getInitialState(), "ZX", "X");

        newAut.addSymbolToStackAlphabet("X"); //Adiciona X ao alfabeto de simbolos da pilha
        newAut.changeInitialSymbol("X"); //Adiciona X como um novo estado inicial


        String finalState = "qF";
        List<String> finalStates = newAut.getFinalStates();

        //Adiciona transições epsilon de todos os estados finais para o novo estado final
        for (int i = 0; i < finalStates.size(); i++) {
            for (int j = 0; j < newAut.getStackAlphabetSize(); j++) {
                newAut.addRule(finalStates.get(i), '_', finalState, "_", String.valueOf(newAut.getStackAlphabet().charAt(j)));
            }
        }

        //Adiciono transições epsilon do estado final para ele mesmo com qualquer simbolo de topo de pilha, desempilhando
        for (int i = 0; i < newAut.getStackAlphabet().length(); i++) {
            newAut.addRule(finalState, '_', finalState, "_", String.valueOf(newAut.getStackAlphabet().charAt(i)));
        }

        newAut.changeInitialState(initialState);
        newAut.clearFinalState();
    }
}
