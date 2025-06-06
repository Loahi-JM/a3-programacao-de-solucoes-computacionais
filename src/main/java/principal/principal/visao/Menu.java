package principal.principal.visao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends JFrame {

    public Menu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jMenuBar1 = new JMenuBar();
        jMenuProduto = new JMenu();
        jMenuCategoria = new JMenu();
        jMenuConfig = new JMenu();
        jMenuItemNovoProduto = new JMenuItem();
        jMenuItemSaidaProduto = new JMenuItem();
        jMenuItemCategoriaNovo = new JMenuItem();
        jMenuItemCategoriaEdicao = new JMenuItem();
        jMenuItemCategoriaExclusao = new JMenuItem();
        jMenuItemSair = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu principal");
        jMenuProduto.setText("Produto");
        jMenuItemNovoProduto.setText("Novo");
        jMenuItemNovoProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuItemNovoProdutoActionPerformed(evt);
            }
        });
        jMenuProduto.add(jMenuItemNovoProduto);

        jMenuItemSaidaProduto.setText("Retirada");
        jMenuItemSaidaProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItemSaidaProdutoActionPerformed(e);
            }
        });
        jMenuProduto.add(jMenuItemSaidaProduto);

        jMenuCategoria.setText("Categoria");
        jMenuItemCategoriaNovo.setText("Novo");
        jMenuItemCategoriaNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuItemAdicionarCategoriaActionPerformed(evt);
            }
        });
        jMenuCategoria.add(jMenuItemCategoriaNovo);

        jMenuItemCategoriaEdicao.setText("Editar");
        jMenuItemCategoriaEdicao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        jMenuCategoria.add(jMenuItemCategoriaEdicao);

        jMenuItemCategoriaExclusao.setText("Excluir");
        jMenuItemCategoriaExclusao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        jMenuCategoria.add(jMenuItemCategoriaExclusao);

        jMenuConfig.setText("Configurações");
        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        jMenuConfig.add(jMenuItemSair);

        jMenuBar1.add(jMenuProduto);
        jMenuBar1.add(jMenuCategoria);
        jMenuBar1.add(jMenuConfig);
        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 710, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 496, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jMenuItemNovoProdutoActionPerformed(ActionEvent evt) {
        CadastroProduto objeto = new CadastroProduto();
        objeto.setVisible(true);
    }

    public void jMenuItemSaidaProdutoActionPerformed(ActionEvent e) {
        SaidaProduto objeto = new SaidaProduto();
        objeto.setVisible(true);
    }

    private void jMenuItemAdicionarCategoriaActionPerformed(ActionEvent evt) {
        CadastrarCategoria objeto = new CadastrarCategoria();
        objeto.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
           Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    private JMenu jMenuProduto;
    private JMenu jMenuCategoria;
    private JMenu jMenuConfig;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItemCategoriaNovo;
    private JMenuItem jMenuItemCategoriaEdicao;
    private JMenuItem jMenuItemCategoriaExclusao;
    private JMenuItem jMenuItemNovoProduto;
    private JMenuItem jMenuItemSaidaProduto;
    private JMenuItem jMenuItemSair;
}
