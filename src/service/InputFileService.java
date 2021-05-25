package service;

import data.Rule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import data.Automaton;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileService {

    JSONArray jsonArray;
    JSONObject jsonField;

    public Automaton parseAutomaton() throws Exception {
        File workingDirectory = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(workingDirectory);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                return parseFile(fileChooser.getSelectedFile().getAbsolutePath());
            } catch (Exception e) {
                throw new Exception("Arquivo inválido!");
            }
        }
        return null;
    }

    private Automaton parseFile(String absolutePath) throws IOException, ParseException {
        jsonField = (JSONObject) new JSONParser().parse(new FileReader(absolutePath));

        jsonArray = (JSONArray) jsonField.get("estados");

        List<String> states = parseArrayField(jsonArray);

        String alphabet = parseAlphabet();

        List<Rule> AFNDRules = parseRules();

        String stackAlphabet = parseStackAlphabet();

        String initialState = parseInitialState();

        String initialSymbol = parseInitialSymbol();

        jsonArray = (JSONArray) jsonField.get("estadosFinais");

        List<String> finalStates = parseArrayField(jsonArray);

        return new Automaton(states, alphabet, AFNDRules, initialState, finalStates,stackAlphabet,initialSymbol);
    }

    private List<String> parseArrayField(JSONArray jsonArray) {
        List<String> array = new ArrayList<>();
        for (Object o : jsonArray) {
            array.add((String) o);
        }
        return array;
    }

    private String parseAlphabet() {
        return (String) jsonField.get("alfabeto");
    }

    private List<Rule> parseRules() {
        //lista com as regras

        List<Rule> AFNDRules = new ArrayList<>();

        jsonArray = (JSONArray) jsonField.get("regras");

        for (Object rule : jsonArray) {

            JSONObject jsonRule = (JSONObject) rule;

            String sourceState = (String) jsonRule.get("estadoPartida");

            String symbol = (String) jsonRule.get("simbolo");

            JSONArray targets = (JSONArray) jsonRule.get("estadosDestino");

            char StackTop = (char) jsonRule.get("topo");

            String StackSymbol = (String) jsonRule.get("empilha");

            List<String> targetStates = parseArrayField(targets);

            AFNDRules.add(new Rule(sourceState, symbol.charAt(0), targetStates,StackSymbol,StackTop));
        }
        return AFNDRules;
    }

    private String parseInitialState() {
        return (String) jsonField.get("estadoInicial");
    }

    private String parseStackAlphabet(){
        return(String) jsonField.get("alfabetoPilha");
    }

    private String parseInitialSymbol(){
        return(String) jsonField.get("simboloInicial");
    }
}


