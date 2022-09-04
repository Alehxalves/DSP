import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializa {

    public static void main(String[] args) {
        CarroEntity car = new CarroEntity();
        car.setModelo("LAMBORGHINI HURACAN");
        car.setCor("BLACK");
        car.setAno(2015);
        car.setFabricante("Lamborghini");

        try {
            FileOutputStream fileOut = new FileOutputStream("./carro.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(car);
            out.close();
            fileOut.close();

            System.out.println("Serializado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
