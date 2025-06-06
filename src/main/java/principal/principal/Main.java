package principal.principal;

import principal.principal.visao.Menu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}