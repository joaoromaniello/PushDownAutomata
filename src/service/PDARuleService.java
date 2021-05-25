package service;

import data.Rule;
import data.RuleDTO;
import repository.PDARuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//TODO

public class PDARuleService {

    PDARuleRepository ruleRepository = new PDARuleRepository(); //cria um novo repositorio de regras

//    public List<Rule> getApplicableRule(List<Rule> rules, String CurrentState, char currentSymbol, Stack a){
//
//        //ler o topo da pilha, e achar uma regra que seja compativel com esse topo da pilha
//        //caso não existir, regra não é encontrada
//
//
//
//    }

    public void addCoveredRule(RuleDTO ruleDTO) {
        ruleRepository.coveredRules.add(ruleDTO);
    }


    public void cleanCoveredRules() {
        ruleRepository.coveredRules = new ArrayList<>();
    }

}
