package service;

import data.RuleDTO;
import repository.PDARuleRepository;

import java.util.ArrayList;

//TODO

public class PDARuleService {

    PDARuleRepository ruleRepository = new PDARuleRepository(); //cria um novo repositorio de regras

    public void addCoveredRule(RuleDTO ruleDTO) {
        ruleRepository.coveredRules.add(ruleDTO);
    }

    public void cleanCoveredRules() {
        ruleRepository.coveredRules = new ArrayList<>();
    }

}
