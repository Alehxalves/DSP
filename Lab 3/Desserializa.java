import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Desserializa {

    public static void desserializarByPath(String path) {
        CarroEntity car = null;
        try {
            FileInputStream fileIn = new FileInputStream("./" + path);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            car = (CarroEntity) in.readObject();

            fileIn.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(car);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            System.out.println("<MENU>\n[1] Desserializar Arquivo. \n[2] Exit.");
            line = scanner.nextLine();

            if ("1".equals(line)) {
                System.out.print("Digite o path do arquivo a ser Desserializado: ");
                line = scanner.nextLine();
                desserializarByPath(line);
            } else if ("2".equals(line)) {
                break;
            } else
                System.out.println("Opção inválida.");
        }
        scanner.close();
    }
}