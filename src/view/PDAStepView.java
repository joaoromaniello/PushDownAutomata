package view;

import data.Automaton;
import service.PDAService;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class PDAStepView extends JFrame {

    PDAService pdaService = new PDAService();
    Automaton emptyStackAutomata;
    Automaton finalStateAutomata;

    JTextField palavra = new JTextField();
    JButton validate = new JButton("Validar");
    JButton changeAutomaton = new JButton("<<<<");

    public PDAStepView(Automaton aut1, Automaton aut2) {

        setupFrame();
        setupTitle();
        setupButtons();

        JLabel originalLabel;
        JLabel convertedLabel;

        if (aut1.identifyType() == 0) {
            emptyStackAutomata = aut1;
            finalStateAutomata = aut2;
            originalLabel = new JLabel("PDA Original (Por pilha vazia)");
            convertedLabel = new JLabel("PDA Convertido (Por estado final)");
        } else {
            emptyStackAutomata = aut2;
            finalStateAutomata = aut1;
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

        //Adiciona os elementos da janela referente ao automato originalmente lido pelo json
        JScrollPane OriginalPane = new JScrollPane(buildTextArea(aut1.toString()));
        OriginalPane.setBounds(20, 75, 470, 480);
        add(OriginalPane);

        //Adiciona os elementos da janela referente ao automato convertido
        JScrollPane ConvertedPane = new JScrollPane(buildTextArea(aut2.toString()));
        ConvertedPane.setBounds(500, 75, 470, 480);
        add(ConvertedPane);
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
        changeAutomaton.setBounds(20, 15, 100, 30);
        add(changeAutomaton);
        add(validate);

        this.changeAutomatonButtonAction();
        this.validateButtonAction();
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

    private void changeAutomatonButtonAction() {
        changeAutomaton.addActionListener(e -> {
            dispose();
            new InitialView();
        });
    }

    private void validateButtonAction() {
        validate.addActionListener(e -> {
            try {
                pdaService.belongsToLanguage(palavra.getText(), finalStateAutomata);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
