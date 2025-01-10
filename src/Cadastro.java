import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Cadastro extends JFrame{
    private JTextField tfNome;
    private JSpinner spnIdade, spnPeso, spnAltura;
    private JTextArea taObjetivo;
    private JButton btnIncluir, btnLimpar, btnExibir, btnSair, btnVoltar;

    public Cadastro(){
        setTitle("Academia la Conga");
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
        spnIdade = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
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
            JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spnPeso, "0.00");
            spnPeso.setEditor(editor);
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
            editor = new JSpinner.NumberEditor(spnAltura, "0.00");
            spnAltura.setEditor(editor);
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

        // incluir
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        btnIncluir = new JButton("Incluir");
        painelForm.add(btnIncluir, gbc);

            btnIncluir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cadastrarAluno();
                }
            });


        // buttons
        JPanel painelButtons = new JPanel(new GridLayout(1,4,10,10));
        painelButtons.setPreferredSize(new Dimension(400,100));

        btnVoltar = new JButton("Voltar");
        painelButtons.add(btnVoltar);
        fonteButtons(btnVoltar);

        btnLimpar = new JButton("Limpar");
        painelButtons.add(btnLimpar);
        fonteButtons(btnLimpar);

            btnLimpar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    limparCampos();
                }
            });

        btnExibir = new JButton("Exibir Dados");
        painelButtons.add(btnExibir);
        fonteButtons(btnExibir);
            btnExibir.addActionListener(e -> {
                new ApresentaDados().setVisible(true);
            });

        btnSair = new JButton("Sair");
        painelButtons.add(btnSair);
        fonteButtons(btnSair);

        add(lbCadastrar, BorderLayout.NORTH);
        add(painelForm, BorderLayout.CENTER);
        add(painelButtons, BorderLayout.SOUTH);
    }

    public void centralizarTextoSpinner(JSpinner spinner) {
        JComponent editor = (JComponent) spinner.getEditor();
        JTextField textField = (JTextField) editor.getComponent(0);
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    public void fonteButtons(JButton button) {
        button.setFont(new Font("Verdana", Font.BOLD, 15));
    }

    public void cadastrarAluno(){
        String nome = tfNome.getText().trim();
        int idade = Integer.parseInt(spnIdade.getValue().toString());
        float peso = Float.parseFloat(spnPeso.getValue().toString());
        float altura = Float.parseFloat(spnAltura.getValue().toString());
        String objetivo = taObjetivo.getText();

        if(nome.isEmpty() || idade <= 0 || altura <= 0 || objetivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!");
        }

        else{
        try(Connection conn = Conexao.getConnection()){
            if(conn != null){
                String sql = "INSERT INTO alunos(nome, idade, peso, altura, objetivo) VALUES(?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,nome);
                stmt.setInt(2,idade);
                stmt.setFloat(3,peso);
                stmt.setFloat(4,altura);
                stmt.setString(5,objetivo);

                int linhasInseridas = stmt.executeUpdate();
                if(linhasInseridas > 0){
                    JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } else{
                    JOptionPane.showMessageDialog(this, "Falha na conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao inserir os dados" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        }}
    }

    public void limparCampos(){
        tfNome.setText("");
        spnIdade.setValue(0);
        spnPeso.setValue(0);
        spnAltura.setValue(0);
        taObjetivo.setText("");
    }
}
