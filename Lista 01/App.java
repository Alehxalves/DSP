import java.util.Scanner;
import java.lang.ProcessBuilder;

public class App {
    private static final ManipularArquivos arq = new ManipularArquivos();

    public void clearConsole() throws Exception {
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            new ProcessBuilder("clear").inheritIO().start().waitFor();
    }

    public void run() throws Exception {
        Scanner userInputInt = new Scanner(System.in);
        Scanner userInputLine = new Scanner(System.in);

        while (true) {
            try {
                int option = 0;
                System.out.println(
                        "[1] Ler arquivo de texto.\n" +
                                "[2] Copiar conteudo de arquivo x para arquivo y.\n" +
                                "[3] Sair.");

                option = userInputInt.nextInt();
                clearConsole();

                if (option == 1) {
                    System.out.println("Digite o caminho do arquivo.");
                    String path = userInputLine.nextLine();
                    int begin = 0, end = 0;
                    try {
                        System.out.println("Digite o inteiro referente a linha de inicio: ");
                        begin = userInputInt.nextInt();
                        System.out.println("Digite o inteiro referente a linha final: ");
                        end = userInputInt.nextInt();
                    } catch (Exception e) {
                        throw new Exception("Apenas numeros nas linhas");
                    }

                    if (begin > end) {
                        throw new Exception("Intervalo invalido, linha inicio > linha final");
                    } else {
                        clearConsole();
                        arq.readRange(path, begin, end);
                    }

                } else if (option == 2) {
                    System.out.println("Digite o caminho do arquivo remetente.");
                    String rementente = userInputLine.nextLine();
                    System.out.println("Digite o caminho do arquivo destinatario.");
                    String destinatario = userInputLine.nextLine();

                    clearConsole();
                    arq.sobreescreverArquivo(rementente, destinatario);
                    arq.read(rementente);
                } else if (option == 3) {
                    break;
                } else {
                    throw new Exception("Opcao invalida.");
                }
            } catch (Exception e) {
                clearConsole();
                System.out.println("ERROR! ".concat(e.getMessage() + "\n"));
                run();
            }

        }
        userInputInt.close();
        userInputLine.close();
    }

    public static void main(String[] args) throws Exception {
        App app = new App();

        try {
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}