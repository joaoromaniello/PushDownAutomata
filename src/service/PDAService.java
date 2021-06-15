package service;

import data.Automaton;
import data.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PDAService {

    Automaton automaton;
    String sequence;

    public PDAService(Automaton automaton) {
        this.automaton = automaton;
    }

    public Boolean belongsToLanguage(String sequence) {
        this.sequence = sequence;
        Stack<String> stack = new Stack<>(); //Escopo local
        stack.push(automaton.getInitialStackSymbol()); //Adiciona o símbolo inicial à pilha
        return processSequence(0, stack, automaton.getInitialState());
    }

    public Boolean processSequence(int position, Stack<String> currentStack, String currentState) {
        //Casos bases pra ambos tipos de autômato
        if (position == sequence.length() && (currentStack.isEmpty() || automaton.getFinalStates().contains(currentState)))
            return true;
        if (currentStack.isEmpty())
            return false;

        String stackTop = currentStack.pop();
        if (searchAndApplyRuleBySymbol(position, currentStack, currentState, stackTop,'_'))
            return true;

        if (position == sequence.length()) {
            return false;
        }

        if (searchAndApplyRuleBySymbol(position + 1, currentStack, currentState, stackTop, sequence.charAt(position)))
            return true;

        return false;
    }

    private boolean searchAndApplyRuleBySymbol(int position, Stack<String> stack, String currentState, String stackTop, char symbol) {
        List<Rule> applicableRules = getApplicableRules(currentState, symbol, stackTop);
        for (Rule rule : applicableRules) {
            applyRule(stack, rule);
            if (processSequence(position, stack, rule.getTargetState())) {
                return true;
            }
        }
        return false;
    }

    private void applyRule(Stack<String> stack, Rule rule) {
        String newStackSymbols = rule.getTargetStack();
        for (int i = newStackSymbols.length() - 1; i >= 0; i--) {
            if (newStackSymbols.charAt(i) != '_') {
                stack.push(String.valueOf(newStackSymbols.charAt(i)));
            }
        }
    }

    public List<Rule> getApplicableRules(String state, char symbol, String top) {

        List<Rule> regras = new ArrayList<>();

        for (Rule rule: automaton.getRules()) {
            if (rule.getSourceState().equalsIgnoreCase(state) &&
                rule.getSymbol() == symbol &&
                rule.getStackTop().equalsIgnoreCase(top)
            ) {
                regras.add(rule);
            }
        }
        return regras;
    }
}
