import java.io.FileInputStream;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class ManipularArquivos {
    InputStream arquivo;

    public ManipularArquivos() {
    }

    public void readRange(String path, int begin, int end) throws Exception {
        Scanner entrada;
        begin = begin < 0 ? 1 : begin;
        try {
            arquivo = new FileInputStream(path);
            entrada = new Scanner(arquivo, "UTF-8");

            if (!Objects.isNull(begin)) {
                if (begin > 1)
                    for (int i = 1; i < begin; i++)
                        entrada.nextLine();

                System.out.println("--------------------------------------------------------");
                while (entrada.hasNext() && begin <= end) {
                    System.out.println(entrada.nextLine());
                    begin++;
                }
                System.out.println("--------------------------------------------------------");
            }
            entrada.close();
        } catch (Exception e) {
            throw new Exception("Arquivo nao encontrado.");
        }

    }

    public void read(String path) throws Exception {
        try {
            arquivo = new FileInputStream(path);
            Scanner entrada = new Scanner(arquivo, "UTF-8");

            System.out.println("--------------------------------------------------------");
            while (entrada.hasNext()) {
                System.out.println(entrada.nextLine());
            }
            System.out.println("--------------------------------------------------------");
            entrada.close();
        } catch (Exception e) {
            throw new Exception("Arquivo nao encontrado.");
        }

    }

    public void sobreescreverArquivo(String pathRementente, String pathDestinatario) throws Exception {
        try {
            arquivo = new FileInputStream(pathRementente);
            Scanner entrada = new Scanner(arquivo, "UTF-8");
            InputStream destinatario = new FileInputStream(pathDestinatario);

            PrintStream ps = new PrintStream(pathDestinatario, "UTF-8");
            while (entrada.hasNext()) {
                ps.println(entrada.nextLine());
            }
            entrada.close();
            destinatario.close();
            ps.close();
        } catch (Exception e) {
            throw new Exception("Arquivo(s) nao encontrado. ");
        }
    }
}
