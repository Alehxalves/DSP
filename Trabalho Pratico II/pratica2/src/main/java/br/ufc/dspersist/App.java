package br.ufc.dspersist;

import java.net.MalformedURLException;

import javax.swing.JOptionPane;

import br.ufc.dspersist.ui.AtorMenu;
import br.ufc.dspersist.ui.FilmeMenu;

public class App {

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws MalformedURLException {
        String menu = "Escolha uma opção:\n1 - Menu Filme\n2 - Menu Ator\n3 - Sair";

        char opcao;
        do {
            opcao = JOptionPane.showInputDialog(null, menu,
                    "Prática 2", 3).charAt(0);
            try {
                switch (opcao) {
                    case '1':
                        FilmeMenu.menuFilme();
                        break;
                    case '2':
                        AtorMenu.menuAtor();
                        break;
                    case '3':
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } while (opcao != '3');
    }
}
