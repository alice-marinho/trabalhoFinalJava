import javax.swing.*;
import java.awt.*;

public class ExemploAbrirPagina extends JFrame {
    public ExemploAbrirPagina() {
        setTitle("Página Principal");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel1 = new JPanel();
        painel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Academia la Conga", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        painel1.add(titulo, gbc);


        JLabel mensagem = new JLabel("O que gostaria de fazer hoje?");
        mensagem.setFont(new Font("Arial", Font.ITALIC, 18));


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        painel1.add(mensagem, gbc);

            JButton botaoCadastro = new JButton("Abrir Cadastro");
            botaoCadastro.setPreferredSize(new Dimension(200, 60));
            botaoCadastro.addActionListener(e -> {
                Cadastro cadastro = new Cadastro();
                cadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // fecha somente a janela de cima
                cadastro.setVisible(true);
            });


        JButton botaoApresentaDados = new JButton("Abrir ApresentaDados");
        botaoApresentaDados.setPreferredSize(new Dimension(200, 60));
        botaoApresentaDados.addActionListener(e -> {
            ApresentaDados apresentaDados = new ApresentaDados();
            apresentaDados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            apresentaDados.setVisible(true);
        });


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        painel1.add(botaoCadastro, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        painel1.add(botaoApresentaDados, gbc);

        add(painel1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExemploAbrirPagina().setVisible(true));
    }
}
