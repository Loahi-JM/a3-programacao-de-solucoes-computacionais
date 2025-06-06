package principal.principal.visao;

import principal.principal.connection.ProdutoDao;
import principal.principal.modelo.Categoria;
import principal.principal.modelo.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastrarCategoria extends JFrame {

    private ProdutoDao produtoDao;

    public CadastrarCategoria() {
        initComponents();
    }

    private void initComponents() {
        this.produtoDao = new ProdutoDao();
        Connection connection = produtoDao.getConnection();
        Statement smt = null;
        try {
            smt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        if (smt == null) {
            System.out.println("Erro ao conectar ao banco de dados");
            System.exit(0);
        }

        setTitle("Cadastro de Categoria");
        setResizable(false);

        jLabelTamanho = new JLabel();
        jLabelEmbalagem = new JLabel();
        JTFTamanho = new JTextField();
        JTFEmbalagem = new JTextField();
        JBCadastrar = new JButton();
        JBCancelar = new JButton();

        jLabelTamanho.setText("Tamanho:");
        jLabelEmbalagem.setText("Embalagem:");
        JBCadastrar.setText("Cadastrar");
        Statement finalSmt = smt;
        JBCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JBCadastrarActionPerformed(e, finalSmt, connection);
            }
        });

        JBCancelar.setText("Cancelar");
        JBCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JBCancelarActionPerformed(e);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabelTamanho, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabelEmbalagem, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(JTFTamanho, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(JTFEmbalagem, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(JBCancelar)
                                                .addGap(34, 34, 34)
                                                .addComponent(JBCadastrar))
                                        .addComponent(jLabelTamanho, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JTFTamanho))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTamanho)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTFTamanho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEmbalagem)
                                .addComponent(JTFEmbalagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelEmbalagem)
                                        .addComponent(JTFEmbalagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(JBCadastrar)
                                        .addComponent(JBCancelar))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroProduto().setVisible(true);
            }
        });
    }

    private void JBCancelarActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void JBCadastrarActionPerformed(ActionEvent evt, Statement smt, Connection connection) {
        try {
            String tamanho = "";
            String embalagem = "";

            if (this.JTFTamanho.getText().equals("Pequeno") || this.JTFTamanho.getText().equals("Médio") || this.JTFTamanho.getText().equals("Grande")) {
                tamanho = this.JTFTamanho.getText();
            } else {
                throw new Mensagem("Tamanhos aceitos no sistema (Pequeno, Médio e Grande)");
            }

            if (this.JTFEmbalagem.getText().equals("Lata") || this.JTFEmbalagem.getText().equals("Vidro") || this.JTFEmbalagem.getText().equals("Plástico")) {
                embalagem = this.JTFEmbalagem.getText();
            } else {
                throw new Mensagem("Embalagens aceitas no sistema (Lata, Vidro e Plástico)");
            }

            Categoria categoria = new Categoria(tamanho, embalagem);
            boolean cadastro = cadastrarCategoria(categoria, connection);
            if (cadastro) {
                JOptionPane.showMessageDialog(null, "Categoria Cadastrado com Sucesso!");
                this.JTFTamanho.setText("");
                this.JTFEmbalagem.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar categoria!");
            }
        } catch (Mensagem e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean cadastrarCategoria(Categoria c, Connection connection) {
        String insert = "insert into categoria (tamanho, embalagem) values (?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(insert);
            pre.setString(1, c.getTamanho());
            pre.setString(2, c.getEmbalagem());

            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private JLabel jLabelTamanho;
    private JLabel jLabelEmbalagem;
    private JTextField JTFTamanho;
    private JTextField JTFEmbalagem;
    private JButton JBCadastrar;
    private JButton JBCancelar;
}
