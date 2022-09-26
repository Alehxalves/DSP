package br.ufc.dspersist.pratica1;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class HashSHA1 {

    public static String getSha1Hash(String filePath) throws Exception {
        // GetInstance retorna um objeto MessageDigest que implementa o algoritmo
        // especificado
        MessageDigest mdigest = MessageDigest.getInstance("SHA-1");

        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int nread;

            while ((nread = fis.read(buffer)) != -1) {
                mdigest.update(buffer, 0, nread);
                // buffer array de bytes
                // offset o deslocamento inicial
                // nread o número de bytes a serem usados, começando no offset.
            }
        }

        StringBuilder result = new StringBuilder();

        for (byte b : mdigest.digest()) {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        if (!ValidateArgs.validate(args))
            return;

        String filePath = args[0];
        System.out.println(getSha1Hash(filePath));
    }
}
