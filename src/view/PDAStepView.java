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
    JLabel OriginalString =  new JLabel();
    JLabel ConvertedString =  new  JLabel();
    JTextField palavra = new JTextField();
    JButton validate = new JButton("Validar");
    JButton changeAutomaton = new JButton("<<<<");
    int offset = 50;

    public PDAStepView(Automaton Aut1,Automaton Aut2){
        setupFrame();
        setupTitle();
        setupButtons();

        JLabel OriginalString =  new JLabel();
        JLabel ConvertedString =  new  JLabel();

        OriginalString.setText("<html><p style=\"width:300px\">"+Aut1.toString()+"</p></html>");
        //Adiciona os elementos da janela referente ao automato originalmente lido pel json
        OriginalPDA.setVisible(true);
        OriginalPDA.setBounds(20, 100-offset, 470, 500);
        OriginalPDA.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        OriginalPDA.setBackground(new Color(255, 255, 255));
        add(OriginalPDA);
        Original.setBounds(245, 140-offset, 55, 30);
        Original.setFont(new Font(null, Font.BOLD, 15));
        OriginalPDA.add(Original);
        OriginalString.setBounds(60,170-offset,250,400);
        OriginalPDA.add(OriginalString);

        ConvertedString.setText("<html><p style=\"width:300px\">"+Aut2.toString()+"</p></html>");
        //Adiciona os elementos da janela referente ao automato convertido
        ConvertedPDA.setVisible(true);
        ConvertedPDA.setBounds(500, 100-offset, 470, 500);
        ConvertedPDA.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        ConvertedPDA.setBackground(new Color(255, 255, 255));
        add(ConvertedPDA);
        Converted.setBounds(715, 140-offset, 55, 30);
        Converted.setFont(new Font(null, Font.BOLD, 15));
        ConvertedPDA.add(Converted);
        ConvertedString.setBounds(540,170-offset,250,400);
        ConvertedPDA.add(ConvertedString);




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

}
