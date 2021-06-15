package view;

import data.Automaton;
import service.PDAService;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class ResultView extends JFrame {

        Automaton automato;
        String palavra;
        JPanel resultPane = new JPanel();
        JLabel word = new JLabel();
        PDAService automatonProcc;
    
    
            public  ResultView(Automaton aut, String Word){
                this.automato = aut;
                this.palavra = Word;
                automatonProcc = new PDAService(aut);

                setupFrame();
                automatonProcc.proccesSequence(palavra,automato.getInitialState(),automatonProcc.getPdaStack(),automato.getInitialState(),automatonProcc.getPdaStack(),palavra);
                boolean result = automatonProcc.getAcceptance();
                System.out.println(result);


            }


        void setupFrame() {
            setLayout(null);
            setVisible(true);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 500);
            setLocation(460, 140);
            resultPane.setBounds(50,100,500,350);
            resultPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
            word.setText(palavra);
            word.setFont(new Font(null, Font.BOLD, 30));
            word.setBounds(250,10,300,80);
            JLabel teste = new JLabel(automato.toString());
            add(teste);
            add(word);
            add(resultPane);
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        }

        private void setupTitle() {
            JLabel titleLabel = new JLabel("Resultado Final");
            titleLabel.setHorizontalAlignment(CENTER);
            titleLabel.setFont(new Font(null, Font.BOLD, 20));
            titleLabel.setBounds(300, 20, 400, 20);
            add(titleLabel);
        }

    }
