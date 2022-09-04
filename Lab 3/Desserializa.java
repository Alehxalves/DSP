import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Desserializa {

    public static void main(String[] args) {
        CarroEntity car = null;

        try {
            FileInputStream fileIn = new FileInputStream("./carro.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            car = (CarroEntity) in.readObject();

            fileIn.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(car);
    }
}