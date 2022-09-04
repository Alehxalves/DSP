import java.util.Scanner;
import java.lang.ProcessBuilder;

public class App {
    private final ManipularArquivos arq = new ManipularArquivos();
    private Scanner userInputInt = new Scanner(System.in);
    private Scanner userInputLine = new Scanner(System.in);

    public void clearConsole() throws Exception {
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            new ProcessBuilder("clear").inheritIO().start().waitFor();
    }

    public void run(int option) throws Exception {
        clearConsole();
        if (option == 1) {
            System.out.println("Digite o caminho do arquivo.");

            String path = userInputLine.nextLine();
            int begin, end;
            try {
                System.out.println("Digite o inteiro referente a linha de inicio: ");
                begin = userInputInt.nextInt();
                System.out.println("Digite o inteiro referente a linha final: ");
                end = userInputInt.nextInt();

            } catch (Exception e) {
                throw new Exception("Apenas numeros nas linhas");
            }
            if (begin > end)
                throw new Exception("Intervalo invalido, linha inicio > linha final");
            arq.readRange(path, begin, end);
        } else if (option == 2) {
            System.out.println("Digite o caminho do arquivo.");
            String arquivo = userInputLine.nextLine();
            arq.read(arquivo);
        } else if (option == 3) {
            System.out.println("Digite o caminho do arquivo de origem.");
            String rementente = userInputLine.nextLine();
            System.out.println("Digite o caminho do arquivo de destino.");
            String destinatario = userInputLine.nextLine();

            arq.sobreescreverArquivo(rementente, destinatario);
            arq.read(rementente);
        }
    }

    public void menu() throws Exception {
        while (true) {
            try {

                System.out.println(
                        "[1] Ler arquivo de texto com intervalo.\n" +
                                "[2] Ler arquivo completo\n" +
                                "[3] Copiar conteudo de arquivo x para arquivo y.\n" +
                                "[4] Sair.");

                String opcao = userInputInt.next();

                if (Integer.parseInt(opcao) > 4 || Integer.parseInt(opcao) < 1)
                    throw new Exception("Opcao invalida.");
                else if (Integer.parseInt(opcao) == 4) {
                    break;
                } else {
                    clearConsole();
                    run(Integer.parseInt(opcao));
                }
            } catch (Exception e) {
                clearConsole();
                System.out.println("ERROR ".concat(e.getMessage() + "\n"));
            }
        }
        clearConsole();
        userInputLine.close();
        userInputInt.close();
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.menu();
    }
}