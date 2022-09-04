import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializa {

    public static void serializar(List<CarroEntity> list) {
        try {
            for (CarroEntity carList : list) {
                FileOutputStream fileOut = new FileOutputStream("./" + carList.getModelo().concat(".ser"));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                out.writeObject(carList);
                out.close();
                fileOut.close();
            }
            System.out.println("Serializado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CarroEntity car = new CarroEntity();
        car.setModelo("LAMBORGHINI HURACAN");
        car.setCor("BLACK");
        car.setAno(2015);
        car.setFabricante("Lamborghini");

        CarroEntity car2 = new CarroEntity();
        car2.setModelo("FERRARI F40");
        car2.setCor("VERMELHO");
        car2.setAno(1990);
        car2.setFabricante("FERRARI");

        List<CarroEntity> carList = new ArrayList<>();
        carList.add(car);
        carList.add(car2);

        serializar(carList);
    }
}
