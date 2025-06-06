package principal.principal;

import principal.principal.visao.Menu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.setVisible(true);
//        SqlConnection sql = new SqlConnection();
//        Connection connection = sql.getConnection();
//        if (connection == null) {
//            System.exit(0);
//        }
//        Statement smt = connection.createStatement();
//
//        try {
//            ResultSet a = smt.executeQuery("select * from produto;");
//
//            while(a.next()) {
//                System.out.println(a.getInt("id"));
//                System.out.println(a.getString("nome"));
//                System.out.println(a.getString("preco_unitario"));
//                System.out.println(a.getString("unidade"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }
}