import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ApresentaDados extends JFrame {
    private List<Aluno> alunos;
    private JPanel painelDados;
    private JScrollPane painelScroll;

    String sql = "SELECT * FROM alunos";

    public ApresentaDados() {
        setTitle("Alunos Matriculados");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criação do painel que vai conter os dados dos alunos
        painelDados = new JPanel();
        painelDados.setLayout(new BoxLayout(painelDados, BoxLayout.Y_AXIS));  // Aplicando BoxLayout no painel de dados

        // Criando o JScrollPane com o painelDados
        painelScroll = new JScrollPane(painelDados);
        painelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        painelScroll.setPreferredSize(new Dimension(850, 700));

        // Adicionando o painel com rolagem ao JFrame
        add(painelScroll, BorderLayout.CENTER);

        // Carregar e mostrar os dados dos alunos
        try {
            carregarMatriculas();
            mostrarMatriculas();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados dos alunos.");
        }
    }

    private void carregarMatriculas() throws SQLException {
        alunos = new ArrayList<>();
        try (Connection conn = Conexao.getConnection()) {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    int matricula = resultSet.getInt("id_matricula");
                    String nome = resultSet.getString("nome");
                    int idade = resultSet.getInt("idade");
                    float peso = resultSet.getFloat("peso");
                    float altura = resultSet.getFloat("altura");
                    String objetivo = resultSet.getString("objetivo");
                    alunos.add(new Aluno(matricula, nome, idade, peso, altura, objetivo));
                }

                resultSet.close();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao exibir matriculas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarMatriculas() {
        painelDados.removeAll();

        for (Aluno aluno : alunos) {
            JPanel painelAluno = new JPanel();
            painelAluno.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 10, 5, 10);

            // Adicionando as informações do aluno, um por um
            gbc.gridx = 0;
            gbc.gridy = 0;
            painelAluno.add(new JLabel("Matrícula: " + aluno.getMatricula()), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            painelAluno.add(new JLabel("Nome: "), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            painelAluno.add(new JTextField(20) {{ setText(aluno.getNome()); setEditable(false); }}, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            painelAluno.add(new JLabel("Idade: "), gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            painelAluno.add(new JTextField(5) {{ setText(String.valueOf(aluno.getIdade())); setEditable(false); }}, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            painelAluno.add(new JLabel("Peso: "), gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            painelAluno.add(new JTextField(5) {{ setText(String.valueOf(aluno.getPeso())); setEditable(false); }}, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            painelAluno.add(new JLabel("Altura: "), gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            painelAluno.add(new JTextField(5) {{ setText(String.valueOf(aluno.getAltura())); setEditable(false); }}, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            painelAluno.add(new JLabel("Objetivo: "), gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            painelAluno.add(new JTextField(20) {{ setText(aluno.getObjetivo()); setEditable(false); }}, gbc);

            painelDados.add(painelAluno);
        }

        painelDados.revalidate();
        painelDados.repaint();
    }
}
