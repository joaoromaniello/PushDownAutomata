package view;

import data.Automaton;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class PDAStepView extends JFrame {

    JTextField palavra = new JTextField();
    JButton validate = new JButton("Validar");
    JButton changeAutomaton = new JButton("<<<<");
    Automaton Automato1;
    Automaton Automato2;

    public PDAStepView(Automaton aut1, Automaton aut2) {

        this.Automato1 = aut1;
        this.Automato2 = aut2;

        JLabel originalLabel;
        JLabel convertedLabel;

        if (aut1.identifyType() == 0) {
            originalLabel = new JLabel("PDA Original (Por pilha vazia)");
            convertedLabel = new JLabel("PDA Convertido (Por estado final)");
        } else {
            convertedLabel = new JLabel("PDA Convertido (Por pilha vazia)");
            originalLabel = new JLabel("PDA Original (Por estado final)");
        }

        originalLabel.setBounds(20, 50, 480, 20);
        originalLabel.setFont(new Font(null, Font.BOLD, 15));
        originalLabel.setHorizontalAlignment(CENTER);
        originalLabel.setForeground(Color.blue);
        add(originalLabel);

        convertedLabel.setBounds(520, 50, 480, 20);
        convertedLabel.setFont(new Font(null, Font.BOLD, 15));
        convertedLabel.setHorizontalAlignment(CENTER);
        convertedLabel.setForeground(Color.red);
        add(convertedLabel);

        setupFrame();
        setupTitle();
        setupButtons();

        //Adiciona os elementos da janela referente ao automato originalmente lido pelo json
        JScrollPane OriginalPane = new JScrollPane(buildTextArea(aut1.toString()));
        OriginalPane.setBounds(20, 75, 470, 480);
        add(OriginalPane);

        //Adiciona os elementos da janela referente ao automato convertido
        JScrollPane ConvertedPane = new JScrollPane(buildTextArea(aut2.toString()));
        ConvertedPane.setBounds(500, 75, 470, 480);
        add(ConvertedPane);
        setValidateButton();
    }

    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        palavra.setBounds(150, 590, 400, 30);
        palavra.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        JLabel wordLabel = new JLabel("PALAVRA:");
        wordLabel.setBounds(80, 590, 100, 30);
        add(wordLabel);
        add(palavra);
        setLocation(460, 140);
    }

    private void setupTitle() {
        JLabel titleLabel = new JLabel("Automato de Pilha(PDA)");
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setBounds(300, 20, 400, 20);
        add(titleLabel);
    }

    private void setupButtons() {
        validate.setBounds(580, 575, 390, 60);
        validate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        changeAutomaton.setBounds(20, 15, 100, 30);
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

    public JTextArea buildTextArea(String text) {
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setFont(new Font("", Font.PLAIN, 15));
        textArea.setCaretPosition(0);

        return textArea;
    }

    public void setValidateButton() {
        validate.addActionListener(event -> {
            try {


                System.out.print("APN: ");
                new ResultView(Automato2, palavra.getText());
                System.out.print("APV: ");
                new ResultView(Automato1, palavra.getText());
                System.out.println();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
