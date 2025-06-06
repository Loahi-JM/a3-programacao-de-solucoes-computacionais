package principal.principal.connection;

import principal.principal.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDao {

    public ArrayList<Produto> minhaLista = new ArrayList<>();

    public Connection getConnection() {
        Connection connection = null;  //instância da conexão
        try {
            // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configurar a conexão
            String server = "localhost"; //caminho do MySQL
            String database = "estoque";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "12345678";

            connection = DriverManager.getConnection(url, user, password);
            // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    public ArrayList<Produto> getMinhaLista() {
        minhaLista.clear();

        try {
            Statement stmt = this.getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produto");
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
                    preco = Double.parseDouble(precoUnitario);
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
                minhaLista.add(p);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return minhaLista;
    }
}
