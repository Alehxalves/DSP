package br.ufc.dspersist.pratica1;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressSigleFile {

    public static void compressSingleFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não encontrado.");
        } else if (file.isDirectory())
            return;

        String zipFileName = file.getName().concat(".zip");

        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);

        // Cria uma nova entrada zip com o nome especificado.
        zos.putNextEntry(new ZipEntry(file.getName()));

        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        zos.write(bytes, 0, bytes.length);

        zos.closeEntry();
        zos.close();
        fos.close();

        System.out.println("Sucess!");
    }

    public static void main(String[] args) throws Exception {
        if (!ValidateArgs.validate(args))
            return;
        String filePath = args[0];

        compressSingleFile(filePath);
    }
}
