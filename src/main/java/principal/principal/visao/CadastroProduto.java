package principal.principal.visao;

import principal.principal.connection.ProdutoDao;
import principal.principal.modelo.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroProduto extends JFrame {

    private Produto produto;
    private ProdutoDao produtoDao;

    public CadastroProduto() {
        initComponents();
        this.produto = new Produto();
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

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        JTFNome = new JTextField();
        JTFPrecoUnitario = new JTextField();
        JTFUnidade = new JTextField();
        JTFQuantidadeMinimaEmEstoque = new JTextField();
        JTFQuantidadeMaximaEmEstoque = new JTextField();
        JBCadastrar = new JButton();
        JBCancelar = new JButton();

        setTitle("Cadastro de Produto");
        setResizable(false);

        jLabel1.setText("Nome:");

        jLabel2.setText("Preço unitário: ");

        jLabel3.setText("Unidade: ");

        jLabel4.setText("Quantidade em estoque: ");

        jLabel5.setText("Quantidade minima em estoque: ");

        jLabel6.setText("Quantidade máxima em estoque: ");

        JBCadastrar.setText("Cadastrar");
        Statement finalSmt = smt;
        JBCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JBCadastrarActionPerformed(evt, finalSmt, connection);
            }
        });

        JBCancelar.setText("Cancelar");
        JBCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JBCancelarActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel6, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(JTFPrecoUnitario, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(JTFUnidade, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(JTFQuantidadeMinimaEmEstoque, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(JTFQuantidadeMaximaEmEstoque, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(JBCancelar)
                                                .addGap(52, 52, 52)
                                                .addComponent(JBCadastrar))
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JTFNome))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTFNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(JTFPrecoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(JTFUnidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(JTFQuantidadeMinimaEmEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(JTFQuantidadeMaximaEmEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(JBCadastrar)
                                        .addComponent(JBCancelar))
                                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void JBCadastrarActionPerformed(ActionEvent evt, Statement smt, Connection connection) {
        try {
            String nome = "";
            Double precoUnitario = 0.00;
            int unidade = 0;
            int quantidadeMinimaEmEstoque = 0;
            int quantidadeMaximaEmEstoque = 0;

            if (this.JTFNome.getText().length() < 2) {
                throw new Mensagem("Nome deve conter ao menos 2 caracteres.");
            } else {
                nome = this.JTFNome.getText();
            }

            if (this.JTFPrecoUnitario.getText().length() <= 0) {
                throw new Mensagem("Infome o preço do produto.");
            } else {
                precoUnitario = Double.parseDouble(this.JTFPrecoUnitario.getText());
            }

            if (this.JTFUnidade.getText().length() <= 0) {
                throw new Mensagem("Informe a quantidade que irá entrar.");
            } else {
                unidade = Integer.parseInt(this.JTFUnidade.getText());
                if (this.JTFQuantidadeMinimaEmEstoque.getText() != null && !this.JTFQuantidadeMinimaEmEstoque.getText().isEmpty()) {
                    quantidadeMinimaEmEstoque = Integer.parseInt(this.JTFQuantidadeMinimaEmEstoque.getText());
                }
                if (this.JTFQuantidadeMaximaEmEstoque.getText() != null && !this.JTFQuantidadeMaximaEmEstoque.getText().isEmpty()) {
                    quantidadeMaximaEmEstoque = Integer.parseInt(this.JTFQuantidadeMaximaEmEstoque.getText());
                }
            }

            ResultSet result = smt.executeQuery("select * from produto");
            Produto produto = null;
            while(result.next()) {
                produto = new Produto(nome, precoUnitario, unidade);
                String nomeDB = result.getString("nome");
                if (nomeDB.equals(nome)) {
                    int id = Integer.parseInt(result.getString("id"));
                    int qtdEstoque = Integer.parseInt(result.getString("quantidade_em_estoque"));
                    int qtdMaxima = Integer.parseInt(result.getString("quantidade_maxima_em_estoque"));
                    int qtdMinima = Integer.parseInt(result.getString("quantidade_minima_em_estoque"));
                    int qtdTotal = qtdEstoque + unidade;
                    produto.setId(id);
                    produto.setQuantidadeEmEstoque(qtdTotal);
                    if (quantidadeMinimaEmEstoque != 0) {
                        produto.setQuantidadeMinimaEmEstoque(quantidadeMinimaEmEstoque);
                    } else {
                        produto.setQuantidadeMinimaEmEstoque(qtdMinima);
                    }
                    if (quantidadeMaximaEmEstoque != 0) {
                        produto.setQuantidadeMaximaEmEstoque(quantidadeMaximaEmEstoque);
                    } else {
                        produto.setQuantidadeMaximaEmEstoque(qtdMaxima);
                    }
                    if (qtdTotal >= qtdMaxima) {
                        JOptionPane.showMessageDialog(null, "A quantidade máxima foi ultrapassada. Não compre mais: " + nome);
                    }
                    if (qtdTotal <= qtdMinima) {
                        JOptionPane.showMessageDialog(null, "A quantidade minima foi ultrapassada, realizar a compra de mais: " + nome);
                    }
                    boolean update = updateProduto(produto, connection);
                    if (update) {
                        produto = null;
                        JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso!");
                        this.JTFNome.setText("");
                        this.JTFPrecoUnitario.setText("");
                        this.JTFUnidade.setText("");
                        this.JTFQuantidadeMinimaEmEstoque.setText("");
                        this.JTFQuantidadeMaximaEmEstoque.setText("");
                        return;
                    } else {
                        produto = null;
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar o produto");
                        return;
                    }
                }
                produto.setQuantidadeEmEstoque(unidade);
                produto.setQuantidadeMinimaEmEstoque(quantidadeMinimaEmEstoque);
                produto.setQuantidadeMaximaEmEstoque(quantidadeMaximaEmEstoque);
            }

            if (produto == null) {
                produto = new Produto(nome, precoUnitario, unidade, unidade, quantidadeMinimaEmEstoque, quantidadeMaximaEmEstoque);
            }

            boolean cadastro = cadastrarProduto(produto, connection);
            if (cadastro) {
                JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
                this.JTFNome.setText("");
                this.JTFPrecoUnitario.setText("");
                this.JTFUnidade.setText("");
                this.JTFQuantidadeMinimaEmEstoque.setText("");
                this.JTFQuantidadeMaximaEmEstoque.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
            }
        } catch (Mensagem | SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (NumberFormatException erro2) {
            JOptionPane.showMessageDialog(null, "Informe um número válido.");
        }
    }

    private void JBCancelarActionPerformed(ActionEvent evt) {
        this.dispose();
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

    public boolean updateProduto(Produto p, Connection connection) {
        String update = "update produto set unidade = ?, quantidade_em_estoque = ?, " +
                "quantidade_minima_em_estoque = ?, quantidade_maxima_em_estoque = ? where id = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(update);
            pre.setString(1, p.getUnidade().toString());
            pre.setString(2, p.getQuantidadeEmEstoque().toString());
            pre.setString(3, p.getQuantidadeMinimaEmEstoque().toString());
            pre.setString(4, p.getQuantidadeMaximaEmEstoque().toString());
            pre.setInt(5, p.getId());

            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean cadastrarProduto(Produto p, Connection connection) {
        String insert = "insert into produto (nome, preco_unitario, unidade, quantidade_em_estoque, quantidade_minima_em_estoque, " +
                "quantidade_maxima_em_estoque, id_categoria) values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(insert);
            pre.setString(1, p.getNome());
            pre.setString(2, p.getPrecoUnitario().toString());
            pre.setString(3, p.getUnidade().toString());
            pre.setString(4, p.getQuantidadeEmEstoque().toString());
            pre.setString(5, p.getQuantidadeMinimaEmEstoque().toString());
            pre.setString(6, p.getQuantidadeMaximaEmEstoque().toString());
            pre.setInt(7, 1);

            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private JButton JBCadastrar;
    private JButton JBCancelar;
    private JTextField JTFQuantidadeMaximaEmEstoque;
    private JTextField JTFQuantidadeMinimaEmEstoque;
    private JTextField JTFUnidade;
    private JTextField JTFPrecoUnitario;
    private JTextField JTFNome;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
}
