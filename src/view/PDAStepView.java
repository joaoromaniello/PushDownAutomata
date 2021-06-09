package view;

import data.Automaton;
import service.InputFileService;

import javax.lang.model.util.Elements;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

public class PDAStepView extends JFrame {

    JLabel titleLabel = new JLabel("Automato de Pilha(PDA)");
    JPanel OriginalPDA = new JPanel();
    JPanel ConvertedPDA = new JPanel();
    JLabel Original = new JLabel("PDA Original");
    JLabel Converted = new JLabel("PDA Convertido");

    JTextField palavra = new JTextField();
    JButton validate = new JButton("Validar");
    JButton changeAutomaton = new JButton("<<<<");

//    JScrollBar barra = new JScrollBar();
//    JScrollBar barra2 = new JScrollBar();


    int offset = 50;

    public PDAStepView(Automaton Aut1,Automaton Aut2){

            if(Aut1.identifyType() == 0){
                Original = new JLabel("PDA Original (Por pilha vazia)");
                Converted = new JLabel("PDA Convertido (Por estado final)");

            }

            if(Aut1.identifyType() == 1){
                 Converted = new JLabel("PDA Convertido (Por pilha vazia)");
                 Original = new JLabel("PDA Original (Por estado final)");

            }

        setupFrame();
        setupTitle();
        setupButtons();



        //Adiciona os elementos da janela referente ao automato originalmente lido pelo json
        OriginalPDA.setLayout(null);
        OriginalPDA.setVisible(true);
        OriginalPDA.setBounds(20, 100-offset, 470, 500);
        OriginalPDA.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        OriginalPDA.setBackground(new Color(255, 255, 255));
        add(OriginalPDA);
        Original.setBounds(140, 60-offset, 300, 30);
        Original.setFont(new Font(null, Font.BOLD, 15));
        OriginalPDA.add(Original);
//        barra.setBounds(450,0,20,500);
//        OriginalPDA.add(barra);

        printAutomaton(Aut1,OriginalPDA,60,-140);


        //Adiciona os elementos da janela referente ao automato convertido
        ConvertedPDA.setLayout(null);
        ConvertedPDA.setVisible(true);
        ConvertedPDA.setBounds(500, 100-offset, 470, 500);
        ConvertedPDA.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        ConvertedPDA.setBackground(new Color(255, 255, 255));
        add(ConvertedPDA);
        Converted.setBounds(140, 60-offset, 300, 30);
        Converted.setFont(new Font(null, Font.BOLD, 15));
        ConvertedPDA.add(Converted);

//        barra2.setBounds(450,0,20,500);
//        ConvertedPDA.add(barra2);
//        ConvertedPDA.setPreferredSize(new Dimension(470,500));

        printAutomaton(Aut2,ConvertedPDA,60,-140);




    }

    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setTitle("Automatos Convertidos");
        palavra.setBounds(150,615-(offset/2),400,30);
        palavra.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        JLabel wordLabel = new JLabel("PALAVRA:");
        wordLabel.setBounds(80,615-(offset/2),100,30);
        add(wordLabel);
        add(palavra);
    }

    private void setupTitle() {
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setBounds(300, 20, 400, 20);
        add(titleLabel);
    }

    private void setupButtons(){
        validate.setBounds(580,600-(offset/2),390,60);
        validate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        changeAutomaton.setBounds(20,15,100,30);
        changeAutomaton.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        add(changeAutomaton);
        add(validate);

        this.changeAutomatonButtonAction();


    }

    private void changeAutomatonButtonAction() {
        changeAutomaton.addActionListener(e -> {
            dispose();
            new InitialView();
        });
    }

    private void printAutomaton(Automaton a,JPanel panel, int xAxis , int yAxis){
        int counter = 0;


        //Constroi a String para os estados percorridos
        JLabel CStates = new JLabel();
        CStates.setText("Q:"+a.getStates()+",\n");
        CStates.setBounds(xAxis,yAxis,250,400);
        panel.add(CStates);
        counter += 20;
        /////////////////////////////////////////////////////////////////////////

        //Constroi a String para o alfabeto de entrada
        StringBuilder outputAlphabet = new StringBuilder();
        outputAlphabet.append("[");
        for(int i = 0; i < a.getAlphabet().length(); i++) {
            outputAlphabet.append(a.getAlphabet().charAt(i));
            if (i != a.getAlphabet().length()-1) {
                outputAlphabet.append(", ");
            }
        }
        JLabel CAlphabet = new JLabel();
        CAlphabet.setText("\u03A3: " +outputAlphabet + "], \n");
        CAlphabet.setBounds(xAxis,yAxis + counter,250,400);
        panel.add(CAlphabet);
        counter += 20;
        /////////////////////////////////////////////////////////////////////////

        //Constroi a String para o alfabeto da pilha
        StringBuilder outputStackAlphabet = new StringBuilder();
        for(int i = 0; i < a.getStackAlphabet().length(); i++) {
            outputStackAlphabet.append(a.getStackAlphabet().charAt(i));
            if (i != a.getStackAlphabet().length()-1) {
                outputStackAlphabet.append(", ");
            }
        }

        JLabel CStackAlphabet = new JLabel();
        CStackAlphabet.setText( "\u0393: ["  + outputStackAlphabet + "],\n");
        CStackAlphabet.setBounds(xAxis,yAxis + counter,250,400);
        panel.add(CStackAlphabet);
        counter += 20;
        /////////////////////////////////////////////////////////////////////////

        //Constroi a String para as regras do automato
        JLabel regras = new JLabel("\u03B4:[");
        regras.setBounds(xAxis, yAxis + counter, 250, 400);
        panel.add(regras);
        counter += 20;

        JLabel[] CRules =new JLabel[a.getRules().size()];

        for(int j = 0; j < a.getRules().size();j++){

            if(j == a.getRules().size()-1){
                CRules[j] = new JLabel(a.getRules().get(j).toString() +"]");
            }
            else
            CRules[j] = new JLabel(a.getRules().get(j).toString());

        }

          for(int i =0 ; i < a.getRules().size();i++) {
              CRules[i].setBounds(xAxis, yAxis + counter, 250, 400);
              panel.add(CRules[i]);
              counter += 20;

          }


        /////////////////////////////////////////////////////////////////////////


        //Constroi a String para os estados iniciais
        JLabel CInitialState = new JLabel();

        CInitialState.setText("q \u2080  : " + a.getInitialState() +",");
        CInitialState.setBounds(xAxis,yAxis+counter,250,400);
        panel.add(CInitialState);
        counter += 20;

        /////////////////////////////////////////////////////////////////////////

        JLabel CFinalState = new JLabel();

        if(a.getFinalStates().size() != 0){
            CFinalState.setText("F: " + a.getFinalStates() + "\n");
            CFinalState.setBounds(xAxis,yAxis+counter,250,400);
            panel.add(CFinalState);
            counter += 20;

        }

            /////////////////////////////////////////////////////////////////////////
        JLabel CInitialSymbol = new JLabel();

        StringBuilder outputStackInitialSymbol = new StringBuilder();
        for(int i = 0; i < a.getInitialSymbols().length(); i++) {
            outputStackInitialSymbol.append(a.getInitialSymbols().charAt(i));
            if (i != a.getInitialSymbols().length()-1) {
                outputStackInitialSymbol.append(", ");
            }
        }

        CInitialSymbol.setText("Z0: [" + outputStackInitialSymbol + "],\n");
        CInitialSymbol.setBounds(xAxis,yAxis+counter,250,400);
        panel.add(CInitialSymbol);
        counter += 20;





    }

}
