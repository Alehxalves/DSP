import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Q2 {

    public static void readArquivo(String path) throws FileNotFoundException {
        InputStream is = new FileInputStream(path);
        Scanner scanner = new Scanner(is, "UTF-8");

        while (scanner.hasNext())
            System.out.println(scanner.nextLine());
        scanner.close();
    }

    public static void createNewArquivo(String pathArq1, String pathArq2, String newArq) {
        try {
            InputStream is1 = new FileInputStream(pathArq1);
            InputStream is2 = new FileInputStream(pathArq2);
            Scanner scannIs1 = new Scanner(is1, "UTF-8");
            Scanner scannIs2 = new Scanner(is2, "UTF-8");

            PrintStream ps = new PrintStream(newArq, "UTF-8");

            do {
                if (scannIs1.hasNext()) {
                    ps.println(scannIs1.nextLine());
                } else {
                    ps.println(scannIs2.nextLine());
                }

            } while (scannIs2.hasNext());

            scannIs1.close();
            scannIs2.close();
            ps.close();
            readArquivo(newArq);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algum dos arquivos não foi encontrado.");
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arq1, arq2, newArq;

        System.out.print("Digite o path do primeiro arquivo: ");
        arq1 = scanner.nextLine();
        System.out.print("Digite o path do segundo arquivo: ");
        arq2 = scanner.nextLine();
        System.out.print("Digite o nome do novo arquivo: ");
        newArq = scanner.nextLine();

        createNewArquivo(arq1, arq2, newArq);
        scanner.close();
    }
}
