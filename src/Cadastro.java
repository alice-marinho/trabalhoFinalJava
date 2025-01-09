import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Cadastro extends JFrame{
    private JTextField tfNome;
    private JSpinner spnIdade, spnPeso, spnAltura;
    private JTextArea taObjetivo;
    private JButton btnIncluir, btnLimpar, btnExibir, btnSair, btnVoltar;
    //private List<>

    public Cadastro(){
        setTitle("Academia da Gretchen");
        setSize(900,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // label cadastrar topo
        JLabel lbCadastrar = new JLabel("CADASTRO");
        lbCadastrar.setBorder(new EmptyBorder(20,10,10,10));
        lbCadastrar.setFont(new Font("Verdana", Font.BOLD, 40));
        lbCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbCadastrar);

        // formulario
        JPanel painelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        painelForm.setBorder(new EmptyBorder(10,10,10,10));

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelForm.add(new JLabel("Nome: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        tfNome = new JTextField(60);
        tfNome.setPreferredSize(new Dimension(500,30));
        painelForm.add((tfNome), gbc);

        // Idade
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelForm.add(new JLabel("Idade: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        spnIdade = new JSpinner(new SpinnerNumberModel(18, 1, 100, 1));
        spnIdade.setPreferredSize(new Dimension(200, 30));
        centralizarTextoSpinner(spnIdade);
        painelForm.add((spnIdade), gbc);

        // Peso
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelForm.add(new JLabel("Peso: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        spnPeso = new JSpinner(new SpinnerNumberModel(0.00, 0.00, 200.00, 0.50));
        spnPeso.setPreferredSize(new Dimension(200, 30));
        centralizarTextoSpinner(spnPeso);
        painelForm.add((spnPeso), gbc);

        // Altura
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelForm.add(new JLabel("Altura: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        spnAltura = new JSpinner(new SpinnerNumberModel(0, 0, 3.0, 0.1));
        spnAltura.setPreferredSize(new Dimension(200, 30));
        centralizarTextoSpinner(spnAltura);
        painelForm.add((spnAltura), gbc);

        //Objetivo
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelForm.add(new JLabel("Objetivo: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        taObjetivo = new JTextArea(5,60);
        taObjetivo.setBorder(new LineBorder(Color.gray));
        painelForm.add(taObjetivo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        btnIncluir = new JButton("Incluir");
        painelForm.add(btnIncluir, gbc);




        // buttons
        JPanel painelButtons = new JPanel(new GridLayout(1,4,10,10));
        painelButtons.setPreferredSize(new Dimension(400,100));

        btnVoltar = new JButton("Voltar");
        painelButtons.add(btnVoltar);
        fonteButtons(btnVoltar);

        btnLimpar = new JButton("Limpar");
        painelButtons.add(btnLimpar);
        fonteButtons(btnLimpar);

        btnExibir = new JButton("Exibir Dados");
        painelButtons.add(btnExibir);
        fonteButtons(btnExibir);

        btnSair = new JButton("Sair");
        painelButtons.add(btnSair);
        fonteButtons(btnSair);

        add(lbCadastrar, BorderLayout.NORTH);
        add(painelForm, BorderLayout.CENTER);
        add(painelButtons, BorderLayout.SOUTH);
    }


    public static void centralizarTextoSpinner(JSpinner spinner) {
        JComponent editor = (JComponent) spinner.getEditor();
        JTextField textField = (JTextField) editor.getComponent(0);
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    public static void fonteButtons(JButton button) {
        button.setFont(new Font("Verdana", Font.BOLD, 15));
    }

}
