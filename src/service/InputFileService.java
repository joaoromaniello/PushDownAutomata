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


        String stackAlphabet = parseStackAlphabet();

        String initialState = parseInitialState();

        String initialSymbol = parseInitialSymbol();

        List<Rule> AFNDRules = parseRules();


        jsonArray = (JSONArray) jsonField.get("estadosFinais");

        List<String> finalStates = parseArrayField(jsonArray);

        Automaton Automato = new Automaton(states,alphabet, AFNDRules, initialState, finalStates,stackAlphabet,initialSymbol);

        pdaTransformation(Automato);

        return Automato;
    }

    //TODO
    private Automaton pdaTransformation(Automaton a){


        int aux = -1;  //Variavel para saber qual o tipo do automato o qual estamos fazendo o processamento

        if(a.getFinalStates().size() == 0)
             aux = 0; //Automato por pilha vazia

        else
            aux = 1;  //Automato por estado final


        //caso o automato seja um automato por estado final
        if(aux == 1){

         ConvertionService b = new ConvertionService(a);

         Automaton autB = b.finalToEmpty();

            return autB;

        }

        //caso o automato seja um automato por pilha vazia
        else if(aux == 0){

            ConvertionService b = new ConvertionService(a);

            Automaton autB = b.emptyToFinal();

            return autB;

        }


      else
          return a;


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

        List<Rule> PDARules = new ArrayList<>();

        jsonArray = (JSONArray) jsonField.get("regras");

        for (Object rule : jsonArray) {

            JSONObject jsonRule = (JSONObject) rule;

            String sourceState = (String) jsonRule.get("estadoPartida");

            String symbol = (String) jsonRule.get("simbolo");

            String targets = (String) jsonRule.get("estadosDestino");

            String StackSymbol = (String) jsonRule.get("empilha");

            String StackTop = (String) jsonRule.get("topo");

            String targetState = (String) jsonRule.get("estadosDestino");

            PDARules.add(new Rule(sourceState, symbol.charAt(0), targetState,StackSymbol,StackTop));
        }
        return PDARules;
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


