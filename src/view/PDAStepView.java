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


    public PDAStepView(Automaton Aut1,Automaton Aut2){
        setupFrame();
        setupTitle();

        JLabel OriginalString =  new JLabel();
        JLabel ConvertedString =  new  JLabel();

        OriginalString.setText("<html><p style=\"width:300px\">"+Aut1.toString()+"</p></html>");
        //Adiciona os elementos da janela referente ao automato originalmente lido pel json
        OriginalPDA.setVisible(true);
        OriginalPDA.setBounds(20, 100, 470, 500);
        OriginalPDA.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        OriginalPDA.setBackground(new Color(255, 255, 255));
        add(OriginalPDA);
        Original.setBounds(245, 140, 55, 30);
        Original.setFont(new Font(null, Font.BOLD, 15));
        OriginalPDA.add(Original);
        OriginalString.setBounds(60,170,250,400);
        OriginalPDA.add(OriginalString);

        ConvertedString.setText("<html><p style=\"width:300px\">"+Aut2.toString()+"</p></html>");
        //Adiciona os elementos da janela referente ao automato convertido
        ConvertedPDA.setVisible(true);
        ConvertedPDA.setBounds(500, 100, 470, 500);
        ConvertedPDA.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        ConvertedPDA.setBackground(new Color(255, 255, 255));
        add(ConvertedPDA);
        Converted.setBounds(715, 140, 55, 30);
        Converted.setFont(new Font(null, Font.BOLD, 15));
        ConvertedPDA.add(Converted);
        ConvertedString.setBounds(540,170,250,400);
        ConvertedPDA.add(ConvertedString);



    }


    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);
        setTitle("Automatos Convertidos");
    }

    private void setupTitle() {
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setBounds(300, 20, 400, 20);
        add(titleLabel);
    }

}
