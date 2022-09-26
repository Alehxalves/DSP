package br.ufc.dspersist.pratica1;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFile {

    public static void compress(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não encontrado.");
        } else if (file.isDirectory())
            return; // Zip apenas de arquivos.

        // Nome do arquivo que será salvo no hd/ssd
        String zipFileName = file.getName().concat(".zip");

        // fluxo de saída para gravar dados em um arquivo caso o arquivo não exista ele
        // é criado
        FileOutputStream fos = new FileOutputStream(zipFileName);

        // Fluxo de saída para gravar arquivos no formato de arquivo ZIP
        ZipOutputStream zos = new ZipOutputStream(fos);

        // Cria uma nova entrada zip com o nome especificado.
        zos.putNextEntry(new ZipEntry(file.getName()));

        // Cria um array de bytes que vai armazenar todos os bytes do arquivo a ser
        // zipado
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        // Grava uma matriz de bytes no zipOut, len numero de bytes a serem escritos
        // e off o deslocamento inicial
        zos.write(bytes, 0, bytes.length);

        zos.closeEntry();
        zos.close();
        fos.close();

        System.out.println("Sucess!");
    }
}
