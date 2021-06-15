package view;

import data.Automaton;
import service.PDAService;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class ResultView extends JFrame {

    Automaton automato;
    String word;
    JPanel resultPane = new JPanel();
    JLabel wordLabel = new JLabel();
    PDAService pdaService;

    public ResultView(Automaton aut, String wordLabel) {
        this.automato = aut;
        this.word = wordLabel;
        pdaService = new PDAService(aut);

        setupFrame();
        System.out.println(pdaService.belongsToLanguage(word));
    }

    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocation(460, 140);
        resultPane.setBounds(50, 100, 500, 350);
        resultPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        wordLabel.setText(word);
        wordLabel.setFont(new Font(null, Font.BOLD, 30));
        wordLabel.setBounds(250, 10, 300, 80);
        JLabel teste = new JLabel(automato.toString());
        add(teste);
        add(wordLabel);
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
