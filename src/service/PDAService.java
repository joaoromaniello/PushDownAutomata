package service;

import data.Automaton;
import data.EFechoIndex;
import data.Rule;

import java.util.*;

import static java.util.Collections.emptySet;

public class PDAService {

    Automaton automaton;
    Stack<EFechoIndex> eFechoIndices;

    public void belongsToLanguage(String sequence, Automaton automaton) throws Exception {
        this.automaton = automaton;
        eFechoIndices = new Stack<>();
        Set<String> firstEFecho = calculateEfecho(automaton.getInitialState(), emptySet());
        for (String state: firstEFecho) { //Adiciono todos os possíveis estados iniciais lendo a posição 0 da cadeia
            eFechoIndices.add(new EFechoIndex(state, 0));
        }
        processSequence(sequence);
    }

    private Set<String> calculateEfecho(String state, Set<Object> eFechoRequest) {
        Set<String> eFechoResponse = new HashSet<>(emptySet());
        eFechoResponse.add(state);
        for (Rule rule: automaton.getRules()) {
            if (rule.getSourceState().equals(state) && rule.getSymbol() == '_') { // Encontro regras com transição vazia a partir do estado
                for (String target: rule.getTargetStates()) {
                    if (!eFechoRequest.contains(target)) { // Se ainda não tenho no eFecho calculo recursivamente os próximos itens
                        eFechoResponse.addAll(calculateEfecho(target, eFechoRequest));
                    }
                }
            }
        }
        return eFechoResponse;
    }

    private void processSequence(String sequence) throws Exception {
//        if (eFechoIndices.isEmpty()){ //não tenho mais pra onde ir
//            throw new Exception("n pertence");
//        }
//
//        EFechoIndex eFechoIndex = eFechoIndices.pop();
//
//        Set<String> eFecho = new HashSet<>(emptySet());
//        if (eFechoIndex.getPosition() == sequence.length()) { // terminei de ler a cadeia
//            if (automaton.getFinalStates().contains(eFechoIndex.getState())) { // estado atual é estado de aceitação
//                throw new Exception("pertence");
//            } else { //busco as próximas possibilidades da pilha de efecho
//                processSequence(sequence);
//            }
//        } else { // se não é o fim da cadeia
//            char symbol = sequence.charAt(eFechoIndex.getPosition()); // busco o símbolo na posição do efecho do topo da pilha
//            for (Rule rule: automaton.getRules()) {
//                if (rule.getSourceState().equals(eFechoIndex.getState()) && rule.getSymbol() == symbol) { // encontro regra aplicável
//                    for (String targetState : rule.getTargetStates()) { //calculo novo e fecho
//                        eFecho.addAll(calculateEfecho(targetState, emptySet()));
//                    }
//                    for (String eFechoState: eFecho) { // adiciono os novos estados lendo a próxima posição da cadeia
//                        eFechoIndices.add(new EFechoIndex(eFechoState, eFechoIndex.getPosition()+1));
//                    }
//                }
//            }
//            processSequence(sequence);
//        }
    }
}
