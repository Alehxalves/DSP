import java.util.Properties;
import java.util.Scanner;

public class Q4 {

    public static void readArchiveProp(String path) {
        Properties props = new Properties();
        try {
            props.load(Q4.class.getClassLoader().getResourceAsStream(path));
            String archive = props.toString();
            archive = archive.replaceAll("[{},]", "");
            archive = archive.replaceAll("[ ]", "\n");

            System.out.println(archive);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o path do arquivo de propriedades: ");
        String line = scanner.nextLine();

        readArchiveProp(line);
        scanner.close();
    }

}
