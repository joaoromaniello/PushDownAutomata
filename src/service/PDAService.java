package service;

import data.Automaton;
import data.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PDAService {

    Automaton automaton;
    String sequence;

    public PDAService(Automaton automaton){
        this.automaton = automaton;
    }

    public Boolean belongsToLanguage(String sequence){
        this.sequence = sequence;
        Stack<String> pilha = new Stack<>();
        pilha.push(automaton.getInitialSymbols()); //Adiciona o simbolo inicial à pilha
        return processSequence(0,pilha,automaton.getInitialState());
    }

    public Boolean processSequence(int position, Stack <String> stack, String currentState){
        if(position == sequence.length() && (stack.isEmpty() || automaton.getFinalStates().contains(currentState)))
            return true;

        if(stack.isEmpty())
            return false;

        String stackTop = stack.pop();
        List <Rule> rules = getRules(currentState, '_', stackTop);
        for(Rule rule: rules){
            String targetState = rule.getTargetState();
            String newStackSymbols = rule.getStackSymbols();
            for (int i = newStackSymbols.length()-1; i >= 0; i--) {
                if (newStackSymbols.charAt(i) != '_') {
                    stack.push(String.valueOf(newStackSymbols.charAt(i)));
                }
            }
            if (processSequence(position, stack, targetState)) {
                return true;
            }
        }

        if (position == sequence.length()) {
            return false;
        }

        rules = getRules(currentState, sequence.charAt(position), stackTop);
        for(Rule rule: rules){
            String targetState = rule.getTargetState();
            String getStackSymbols = rule.getStackSymbols();
            for (int i = getStackSymbols.length()-1; i >= 0; i--) {
                if (getStackSymbols.charAt(i) != '_') {
                    stack.push(String.valueOf(getStackSymbols.charAt(i)));
                }
            }
            if (processSequence(position+1, stack, targetState)) {
                return true;
            }
        }

        return false;
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
}
