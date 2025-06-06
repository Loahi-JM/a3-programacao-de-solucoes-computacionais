package principal.principal.visao;

import principal.principal.connection.GerenciadorCategoriaDao;
import principal.principal.connection.ProdutoDao;
import principal.principal.modelo.Categoria;
import principal.principal.modelo.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class GerenciadorCategoria extends JFrame {

    private GerenciadorCategoriaDao categoriaDao;
    private ArrayList<Categoria> listaCategorias;

    private JTable tabelaCategorias;
    private DefaultTableModel modeloTabela;
    private JTextField tfId, tfTamanho, tfEmbalagem;
    private JButton btnAlterar, btnApagar, btnCancelar;

    public GerenciadorCategoria() {
        categoriaDao = new GerenciadorCategoriaDao();
        Connection connection = categoriaDao.getConnection();
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
        listaCategorias = new ArrayList<>();
        inicializarComponentes(smt, connection, listaCategorias);
        carregarTabela(smt, connection);
    }

    private void inicializarComponentes(Statement smt, Connection connection, ArrayList<Categoria> listaCategorias) {
        setTitle("Gerenciamento de Categorias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JLabel lblTamanho = new JLabel("Tamanho:");
        JLabel lblEmbalagem = new JLabel("Embalagem:");

        tfId = new JTextField(); tfId.setVisible(false);
        tfTamanho = new JTextField();
        tfEmbalagem = new JTextField();

        tabelaCategorias = new JTable();
        modeloTabela = new DefaultTableModel();
        tabelaCategorias.setModel(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaCategorias);

        btnAlterar = new JButton("Alterar");
        btnApagar = new JButton("Apagar");
        btnCancelar = new JButton("Cancelar");

        // Posicionamento
        scrollPane.setBounds(20, 20, 640, 200);
        lblTamanho.setBounds(20, 230, 100, 20);
        tfTamanho.setBounds(120, 230, 200, 25);
        lblEmbalagem.setBounds(20, 260, 100, 20);
        tfEmbalagem.setBounds(120, 260, 100, 25);

        btnAlterar.setBounds(250, 390, 100, 30);
        btnApagar.setBounds(360, 390, 100, 30);
        btnCancelar.setBounds(470, 390, 100, 30);

        add(scrollPane);
        add(lblTamanho);
        add(tfTamanho);
        add(lblEmbalagem);
        add(tfEmbalagem);
        add(btnAlterar);
        add(btnApagar);
        add(btnCancelar);

        tabelaCategorias.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                preencherCamposTabela();
            }
        });

        btnAlterar.addActionListener(e -> alterarCategoria(smt, connection));
        btnApagar.addActionListener(e -> apagarCategoria(smt, connection));
        btnCancelar.addActionListener(e -> dispose());
    }

    private void carregarTabela(Statement smt, Connection connection) {
        listaCategorias = getMinhaLista(smt, connection);
        modeloTabela.setRowCount(0);
        modeloTabela.setColumnIdentifiers(new String[]{"ID", "Tamanho", "Embalagem"});

        for (Categoria c : listaCategorias) {
            modeloTabela.addRow(new Object[]{
                    c.getId(),
                    c.getTamanho(),
                    c.getEmbalagem()
            });
        }
    }

    private void preencherCamposTabela() {
        int row = tabelaCategorias.getSelectedRow();
        if (row != -1) {
            tfId.setText(tabelaCategorias.getValueAt(row, 0).toString());
            tfTamanho.setText(tabelaCategorias.getValueAt(row, 1).toString());
            tfEmbalagem.setText(tabelaCategorias.getValueAt(row, 2).toString());
        }
    }

    private void alterarCategoria(Statement smt, Connection connection) {
        try {
            if (tfId.getText().isEmpty()) throw new Exception("Selecione uma categoria para alterar.");

            String tamanho = "";
            String embalagem = "";

            if (this.tfTamanho.getText().equals("Pequeno") || this.tfTamanho.getText().equals("Médio") || this.tfTamanho.getText().equals("Grande")) {
                tamanho = this.tfTamanho.getText();
            } else {
                throw new Mensagem("Tamanhos aceitos no sistema (Pequeno, Médio e Grande)");
            }

            if (this.tfEmbalagem.getText().equals("Lata") || this.tfEmbalagem.getText().equals("Vidro") || this.tfEmbalagem.getText().equals("Plástico")) {
                embalagem = this.tfEmbalagem.getText();
            } else {
                throw new Mensagem("Embalagens aceitas no sistema (Lata, Vidro e Plástico)");
            }

            Categoria c = new Categoria(Integer.parseInt(tfId.getText()), tamanho, embalagem);

            if (updateProduto(c, connection)) {
                JOptionPane.showMessageDialog(this, "Categoria alterada com sucesso.");
                limparCampos();
                carregarTabela(smt, connection);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    public boolean updateProduto(Categoria c, Connection connection) {
        String update = "update categoria set tamanho = ?, embalagem = ? where id = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(update);
            pre.setString(1, c.getTamanho());
            pre.setString(2, c.getEmbalagem());
            pre.setInt(3, c.getId());

            pre.execute();
            pre.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void apagarCategoria(Statement smt, Connection connection) {
        try {
            if (tfId.getText().isEmpty()) throw new Exception("Selecione uma categoria para apagar.");

            int id = Integer.parseInt(tfId.getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente apagar esta categoria?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (deleteProduto(id, smt, connection)) {
                    JOptionPane.showMessageDialog(this, "Categoria apagado com sucesso.");
                    limparCampos();
                    carregarTabela(smt, connection);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao apagar categoria.");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    public boolean deleteProduto(int id, Statement smt, Connection connection) {
        String delete = "delete from categoria where id = ?";

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

    public ArrayList<Categoria> getMinhaLista(Statement smt, Connection connection) {
        listaCategorias.clear();

        try {
            ResultSet res = smt.executeQuery("SELECT * FROM categoria");
            while(res.next()) {
                int id = res.getInt("id");
                String tamanho = res.getString("tamanho");
                String embalagem = res.getString("embalagem");

                String tm = "";
                String eb = "";

                if (!tamanho.isEmpty()) {
                    tm = tamanho;
                }
                if (!embalagem.isEmpty()) {
                    eb = embalagem;
                }
                Categoria c = new Categoria(id, tm, eb);
                listaCategorias.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return listaCategorias;
    }

    private void limparCampos() {
        tfId.setText("");
        tfTamanho.setText("");
        tfEmbalagem.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GerenciadorCategoria().setVisible(true));
    }
}
