package br.ufc.dspersist.pratica1;

import java.util.Scanner;

public class App {
    private Scanner scannerInt;
    private Scanner scannerString;

    public App() {
        scannerInt = new Scanner(System.in);
        scannerString = new Scanner(System.in);
    }

    public void menu() {
        Controller controller = new Controller();

        int option = 0;
        String line = "";

        while (true) {
            try {
                System.out.println("==========================================");
                System.out.println("(1) Criar personagem\n"
                        + "(2) Converter JSON para XML e CSV\n"
                        + "(3) Comprimir arquivo para Zip\n"
                        + "(4) Exibir Hash de arquivo\n"
                        + "(5) Sair");
                System.out.println("==========================================");

                System.out.print("Digite a opcao: ");
                option = scannerInt.nextInt();

                CLS.clearConsole();
                if (option == 1) {
                    System.out.print("Digite o nome do personagem: ");
                    line = scannerString.nextLine();
                    System.out.println("CLASSE: (1) Knight | (2) Mage | (3) Paladin | (4) Druid");
                    System.out.print("Escolha sua classe: ");
                    option = scannerInt.nextInt();

                    CLS.clearConsole();
                    controller.createCharacter(line, option);
                } else if (option == 2) {
                    System.out.print("Digite o caminho do arquivo JSON: ");
                    line = scannerString.nextLine();
                    controller.convertJsonToXml(line);
                } else if (option == 3) {
                    System.out.print("Digite o caminho do arquivo: ");
                    line = scannerString.nextLine();
                    controller.compresFile(line);
                } else if (option == 4) {
                    System.out.print("Digite o caminho do arquivo: ");
                    line = scannerString.nextLine();
                    controller.showSHA1Hash(line);
                } else if (option == 5) {
                    break;
                } else {
                    System.out.println("Opção inválida");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.menu();

        app.scannerInt.close();
        app.scannerString.close();
    }
}
