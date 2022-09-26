package br.ufc.dspersist.pratica1;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class SHA1Hash {

    public static String getSha1Hash(String filePath) throws Exception {
        // GetInstance retorna um objeto MessageDigest que implementa o algoritmo
        // especificado
        MessageDigest mdigest = MessageDigest.getInstance("SHA-1");

        // FIS obtém bytes de entrada de um arquivo em um sistema de arquivos
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int nread;

            // read -> return o número total de bytes lidos no buffer, ou -1 se não houver
            // mais dados, pois já chegou ao final do arquivo
            while ((nread = fis.read(buffer)) != -1) {
                // buffer array de bytes, offset o deslocamento inicial
                // nread o número de bytes a serem usados, começando no offset.
                mdigest.update(buffer, 0, nread);
            }
        }

        StringBuilder result = new StringBuilder();

        // .digest retornará um array de bytes.
        for (byte b : mdigest.digest()) {
            // Format cada byte b para hex
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }
}
