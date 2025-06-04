package principal.principal.visao;

import principal.principal.connection.SqlConnection;
import principal.principal.modelo.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class CadastroProduto extends javax.swing.JFrame {

    private Produto produto;
    private SqlConnection sqlConnection;

    public CadastroProduto() {
        initComponents();
        this.produto = new Produto();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.sqlConnection = new SqlConnection();
        Connection connection = sqlConnection.getConnection();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JTFNome = new javax.swing.JTextField();
        JTFPrecoUnitario = new javax.swing.JTextField();
        JTFUnidade = new javax.swing.JTextField();
        JTFQuantidadeEmEstoque = new javax.swing.JTextField();
        JTFQuantidadeMinimaEmEstoque = new javax.swing.JTextField();
        JTFQuantidadeMaximaEmEstoque = new javax.swing.JTextField();
        JBCadastrar = new javax.swing.JButton();
        JBCancelar = new javax.swing.JButton();

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
        JBCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBCadastrarActionPerformed(evt, finalSmt, connection);
            }
        });

        JBCancelar.setText("Cancelar");
        JBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBCancelarActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(JTFPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(JTFUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(JTFQuantidadeMinimaEmEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(JTFQuantidadeMaximaEmEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(JBCancelar)
                                                .addGap(52, 52, 52)
                                                .addComponent(JBCadastrar))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JTFNome))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(JTFPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(JTFUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(JTFQuantidadeMinimaEmEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(JTFQuantidadeMaximaEmEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(JBCadastrar)
                                        .addComponent(JBCancelar))
                                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JBCadastrarActionPerformed(ActionEvent evt, Statement smt, Connection connection) {//GEN-FIRST:event_JBCadastrarActionPerformed
        try {
            // recebendo e validando dados da interface gráfica.
            String nome = "";
            Double precoUnitario = 0.00;
            int unidade = 0;
            int quantidadeEmEstoque = 0;
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
                quantidadeEmEstoque = Integer.parseInt(this.JTFUnidade.getText());
                quantidadeMinimaEmEstoque = Integer.parseInt(this.JTFQuantidadeMinimaEmEstoque.getText());
                quantidadeMaximaEmEstoque = Integer.parseInt(this.JTFQuantidadeMaximaEmEstoque.getText());
            }

            ResultSet result = smt.executeQuery("select * from produto");
            Produto produto = null;
            while(result.next()) {
                produto = new Produto(nome, precoUnitario, unidade);
                String nomeDB = result.getString("nome");
                if (nomeDB.equals(nome)) {
                    Integer id = Integer.valueOf(result.getString("id"));
                    Integer qtdEstoque = Integer.valueOf(result.getString("quantidade_em_estoque"));
                    Integer qtdMaxima = Integer.valueOf(result.getString("quantidade_maxima_em_estoque"));
                    Integer qtdMinima = Integer.valueOf(result.getString("quantidade_minima_em_estoque"));
                    Integer qtdTotal = qtdEstoque + unidade;
                    produto.setId(id);
                    produto.setQuantidadeEmEstoque(qtdTotal);
                    produto.setQuantidadeMinimaEmEstoque(quantidadeMinimaEmEstoque);
                    produto.setQuantidadeMaximaEmEstoque(quantidadeMaximaEmEstoque);
                    if (qtdTotal >= qtdMaxima) {
                        JOptionPane.showMessageDialog(null, "A quantidade máxima foi atingida. Não compre mais o produto: " + nome);
                    }
                    if (qtdTotal <= qtdMinima) {
                        JOptionPane.showMessageDialog(null, "A quantidade minima foi atingida, realizar a compra de mais o produto: " + nome);
                    }
                    boolean update = updateProduto(produto, connection);
                    if (update) {
                        produto = null;
                        JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso!");
                    } else {
                        produto = null;
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar o produto");
                    }
                    break;
                }
                produto.setQuantidadeEmEstoque(unidade);
                produto.setQuantidadeMinimaEmEstoque(quantidadeMinimaEmEstoque);
                produto.setQuantidadeMaximaEmEstoque(quantidadeMaximaEmEstoque);
            }

            if (produto != null) {
                boolean cadastro = cadastrarProduto(produto, connection);
                if (cadastro) {
                    JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
                }
            }

//            // envia os dados para o Controlador cadastrar
//            if (this.produto(nome, precoUnitario, unidade)) {
//                JOptionPane.showMessageDialog(null, "Aluno Cadastrado com Sucesso!");
//                // limpa campos da interface
//                this.JTFNome.setText("");
//                this.JTFIdade.setText("");
//                this.JTFCurso.setText("");
//                this.JTFFase.setText("");
//            }
            //Exibie no console o aluno cadastrado
//            System.out.println(this.objetoaluno.getMinhaLista().toString());

        } catch (Mensagem erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (NumberFormatException erro2) {
            JOptionPane.showMessageDialog(null, "Informe um número válido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void JBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBCancelarActionPerformed
        //Libera todos os recurso da interface gráfica
        this.dispose();

    }//GEN-LAST:event_JBCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroProduto().setVisible(true);
            }
        });
    }

    public boolean updateProduto(Produto p, Connection connection) {
        String update = "update produto set unidade = ?, quantidade_em_estoque = ?, " +
                "quantidade_minima_em_estoque = ?, quantidade_maxima_em_estoque = ? where id = ?";

        System.out.println(update);

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

        System.out.println(insert);
        try {
            PreparedStatement pre = connection.prepareStatement(insert);
            pre.setString(1, p.getNome());
            pre.setString(2, p.getPrecoUnitario().toString());
            pre.setString(3, p.getUnidade().toString());
            pre.setString(4, p.getQuantidadeEmEstoque().toString());
            pre.setString(5, p.getQuantidadeMinimaEmEstoque().toString());
            pre.setString(6, p.getQuantidadeMaximaEmEstoque().toString());
            pre.setInt(7, 2);

            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private javax.swing.JButton JBCadastrar;
    private javax.swing.JButton JBCancelar;
    private javax.swing.JTextField JTFQuantidadeMaximaEmEstoque;
    private javax.swing.JTextField JTFQuantidadeMinimaEmEstoque;
    private javax.swing.JTextField JTFQuantidadeEmEstoque;
    private javax.swing.JTextField JTFUnidade;
    private javax.swing.JTextField JTFPrecoUnitario;
    private javax.swing.JTextField JTFNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
}
