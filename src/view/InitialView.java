package view;

import data.Automaton;
import service.InputFileService;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

public class InitialView extends JFrame implements ActionListener {

    InputFileService inputFileService = new InputFileService();
    JLabel titleLabel = new JLabel("Automato de Pilha(PDA)");
    JButton openFileButton = new JButton("Arquivo...");
    JButton aboutButton = new JButton("Sobre...");


    //AFNDService automatonService;

    public InitialView(){
        //this.automatonService = new AFNDService();
        setupFrame();
        setupTitle();
        setupFileButton();
        setupAboutButton();
    }

    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setTitle("Introdução");
    }

    private void setupTitle() {
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setBounds(0, 20, 400, 20);
        add(titleLabel);
    }

    private void setupFileButton() {
        openFileButton.setBounds(220,70 ,120,50);
        openFileButton.addActionListener(this);
        add(openFileButton);
    }

    private void setupAboutButton() {
        aboutButton.setBounds(60,70 ,120,50);
        aboutButton.addActionListener(this);
        add(aboutButton);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == openFileButton) {
            try {
                List<Automaton> automaton = inputFileService.parseAndConvertJSON();
                Automaton automaton1 = automaton.get(0);
                Automaton automaton2 = automaton.get(1);


                new PDAStepView(automaton1, automaton2);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (event.getSource() == aboutButton) {
            JOptionPane.showMessageDialog(this, "\n\nO programa tem a finalidade de simular o " +
                            "funcionamento de um Automato de pilha e suas formas de validação.\nPara tal, deve-se " +
                            "inserir a 6-tupla que o define em um arquivo json que contenha os campos como no exemplo:\n\n" +
                            "{\n" +
                            "   \"estados\": [\"q0\", ..., \"qn\"],\n" +
                            "   \"alfabeto\": \"01...\",\n" +
                            "   \"alfabetopilha\": \"Z0...\",\n" +
                            "   \"simboloinicial\": \"Z...\",\n" +
                            "   \"estadoInicial\": \"qx\",\n" +
                            "   \"estadosFinais\": [\"qx\", \"qy\"],<=== SÓ EXISTIRA CASO A VALIDAÇÃO FOR FEITA POR ESTADO FINAL\n" +
                            "   \"regras\": [\n" +
                            "       {\"estadoPartida\":\"qx\", \"simbolo\":\"1\", \"estadoDestino\":\"q1\"},\n" +
                            "       ...,\n" +
                            "       {\"estadoPartida\":\"qy\", \"simbolo\":\"0\", \"estadoDestino\":\"q0\"}\n" +
                            "   ]\n" +
                            "}\n\n" +
                            "Posteriormente, basta adicionar as cadeias que deseja testar, tendo a possibilidade de" +
                            " checar os passos\nque o automato seguiu junto à pilha. " +
                            "\n\n",
                    "Sobre o programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}