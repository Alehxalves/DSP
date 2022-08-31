import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Q1 {

    public static void containSubstring(String pathArquivo, String subString) {
        try {
            InputStream is = new FileInputStream(pathArquivo);
            Scanner scanner = new Scanner(is, "UTF-8");
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (line.toLowerCase().contains(subString.toLowerCase()))
                    System.out.println(line);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado.");
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path, subString;
        System.out.print("Digite o path do arquivo: ");
        path = scanner.nextLine();
        System.out.print("Digite a substring: ");
        subString = scanner.nextLine();

        containSubstring(path, subString);
        scanner.close();
    }

}
