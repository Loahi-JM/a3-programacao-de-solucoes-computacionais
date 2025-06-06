package principal.principal.visao;

import principal.principal.connection.ProdutoDao;
import principal.principal.modelo.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class SaidaProduto extends JFrame {

    // DAO e dados
    private ProdutoDao produtoDao;
    private ArrayList<Produto> listaProdutos;

    // Componentes da interface
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JTextField tfId, tfNome, tfPreco, tfQtdEstoque, tfQtdMinima, tfQtdMaxima;
    private JButton btnAlterar, btnApagar, btnCancelar;

    public SaidaProduto() {
        produtoDao = new ProdutoDao();
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
        listaProdutos = new ArrayList<>();
        inicializarComponentes(smt, connection, listaProdutos);
        carregarTabela(smt, connection);
    }

    private void inicializarComponentes(Statement smt, Connection connection, ArrayList<Produto> listaProdutos) {
        setTitle("Gerenciamento de Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblPreco = new JLabel("Preço:");
        JLabel lblQtdEstoque = new JLabel("Qtd. Estoque:");
        JLabel lblQtdMinima = new JLabel("Qtd. Mínima:");
        JLabel lblQtdMaxima = new JLabel("Qtd. Máxima:");

        tfId = new JTextField(); tfId.setVisible(false);
        tfNome = new JTextField();
        tfPreco = new JTextField();
        tfQtdEstoque = new JTextField();
        tfQtdMinima = new JTextField();
        tfQtdMaxima = new JTextField();

        tabelaProdutos = new JTable();
        modeloTabela = new DefaultTableModel();
        tabelaProdutos.setModel(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        btnAlterar = new JButton("Alterar");
        btnApagar = new JButton("Apagar");
        btnCancelar = new JButton("Cancelar");

        // Posicionamento
        scrollPane.setBounds(20, 20, 640, 200);
        lblNome.setBounds(20, 230, 100, 20);
        tfNome.setBounds(120, 230, 200, 25);
        lblPreco.setBounds(20, 260, 100, 20);
        tfPreco.setBounds(120, 260, 100, 25);
        lblQtdEstoque.setBounds(20, 290, 100, 20);
        tfQtdEstoque.setBounds(120, 290, 100, 25);
        lblQtdMinima.setBounds(20, 320, 100, 20);
        tfQtdMinima.setBounds(120, 320, 100, 25);
        lblQtdMaxima.setBounds(20, 350, 100, 20);
        tfQtdMaxima.setBounds(120, 350, 100, 25);

        btnAlterar.setBounds(250, 390, 100, 30);
        btnApagar.setBounds(360, 390, 100, 30);
        btnCancelar.setBounds(470, 390, 100, 30);

        add(scrollPane);
        add(lblNome); add(tfNome);
        add(lblPreco); add(tfPreco);
        add(lblQtdEstoque); add(tfQtdEstoque);
        add(lblQtdMinima); add(tfQtdMinima);
        add(lblQtdMaxima); add(tfQtdMaxima);
        add(btnAlterar); add(btnApagar); add(btnCancelar);

        tabelaProdutos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                preencherCamposTabela();
            }
        });

        btnAlterar.addActionListener(e -> alterarProduto(smt, connection, listaProdutos));
        btnApagar.addActionListener(e -> apagarProduto(smt, connection));
        btnCancelar.addActionListener(e -> dispose());
    }

    private void carregarTabela(Statement smt, Connection connection) {
        listaProdutos = getMinhaLista(smt, connection);
        modeloTabela.setRowCount(0);
        modeloTabela.setColumnIdentifiers(new String[]{"ID", "Nome", "Preço", "Qtd Estoque", "Qtd Mínima", "Qtd Máxima"});

        for (Produto p : listaProdutos) {
            modeloTabela.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getPrecoUnitario(),
                    p.getQuantidadeEmEstoque(),
                    p.getQuantidadeMinimaEmEstoque(),
                    p.getQuantidadeMaximaEmEstoque()
            });
        }
    }

    private void preencherCamposTabela() {
        int row = tabelaProdutos.getSelectedRow();
        if (row != -1) {
            tfId.setText(tabelaProdutos.getValueAt(row, 0).toString());
            tfNome.setText(tabelaProdutos.getValueAt(row, 1).toString());
            tfPreco.setText(tabelaProdutos.getValueAt(row, 2).toString());
            tfQtdEstoque.setText(tabelaProdutos.getValueAt(row, 3).toString());
            tfQtdMinima.setText(tabelaProdutos.getValueAt(row, 4).toString());
            tfQtdMaxima.setText(tabelaProdutos.getValueAt(row, 5).toString());
        }
    }

    private void alterarProduto(Statement smt, Connection connection, ArrayList<Produto> listaProdutos) {
        try {
            if (tfId.getText().isEmpty()) throw new Exception("Selecione um produto para alterar.");

            Produto p = new Produto(
                    Integer.parseInt(tfId.getText()),
                    tfNome.getText(),
                    Double.parseDouble(tfPreco.getText()),
                    0, // unidade não usado no form
                    Integer.parseInt(tfQtdEstoque.getText()),
                    Integer.parseInt(tfQtdMinima.getText()),
                    Integer.parseInt(tfQtdMaxima.getText())
            );

            for (int i = 0; i < listaProdutos.size(); i++) {
                Produto pro = listaProdutos.get(i);
                if (pro.getId().equals(p.getId())) {
                    if (p.getQuantidadeEmEstoque() < pro.getQuantidadeMinimaEmEstoque()) {
                        JOptionPane.showMessageDialog(null, "A quantidade minima foi ultrapassada, realizar a compra de mais: " + p.getNome());
                    }
                }
            }

            if (updateProduto(p, connection)) {
                JOptionPane.showMessageDialog(this, "Produto alterado com sucesso.");
                limparCampos();
                carregarTabela(smt, connection);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
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

    private void apagarProduto(Statement smt, Connection connection) {
        try {
            if (tfId.getText().isEmpty()) throw new Exception("Selecione um produto para apagar.");

            int id = Integer.parseInt(tfId.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar este produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (deleteProduto(id, smt, connection)) {
                    JOptionPane.showMessageDialog(this, "Produto apagado com sucesso.");
                    limparCampos();
                    carregarTabela(smt, connection);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao apagar o produto.");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    public boolean deleteProduto(int id, Statement smt, Connection connection) {
        String delete = "delete from produto where id = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(delete);
            pre.setInt(1, id);

            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Produto> getMinhaLista(Statement smt, Connection connection) {
        listaProdutos.clear();

        try {
            ResultSet res = smt.executeQuery("SELECT * FROM produto");
            while(res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String precoUnitario = res.getString("preco_unitario");
                String unidade = res.getString("unidade");
                String qtdEstoque = res.getString("quantidade_em_estoque");
                String qtdMinima = res.getString("quantidade_minima_em_estoque");
                String qtdMaxima = res.getString("quantidade_maxima_em_estoque");

                Double preco = 0.00;
                Integer unidad = 0;
                Integer qtdEmEstoque = 0;
                Integer qtdEMinima = 0;
                Integer qtdEMaxima = 0;

                if (!precoUnitario.isEmpty()) {
                    preco = Double.parseDouble(precoUnitario.replace(",", "."));
                }
                if (!unidade.isEmpty()) {
                    unidad = Integer.parseInt(unidade);
                }
                if (!qtdEstoque.isEmpty()) {
                    qtdEmEstoque = Integer.parseInt(qtdEstoque);
                }
                if (!qtdMinima.isEmpty()) {
                    qtdEMinima = Integer.parseInt(qtdMinima);
                }
                if (!qtdMaxima.isEmpty()) {
                    qtdEMaxima = Integer.parseInt(qtdMaxima);
                }
                Produto p = new Produto(id, nome, preco, unidad, qtdEmEstoque, qtdEMinima, qtdEMaxima);
                listaProdutos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return listaProdutos;
    }

    private void limparCampos() {
        tfId.setText("");
        tfNome.setText("");
        tfPreco.setText("");
        tfQtdEstoque.setText("");
        tfQtdMinima.setText("");
        tfQtdMaxima.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SaidaProduto().setVisible(true));
    }
}
