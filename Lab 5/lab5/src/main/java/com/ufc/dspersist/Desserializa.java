package com.ufc.dspersist;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Desserializa {

    // Deserializar cada objeto serializa em json como pede a lista
    public static void readEachObject() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Pessoa pessoa = new Pessoa();
        List<Pessoa> pessoas = Serializa.getPessoas();

        for (Pessoa p : pessoas) {
            pessoa = objectMapper.readValue(new File(p.getName().concat(".json")), Pessoa.class);
            System.out.println(pessoa);
        }
    }

    // Desserializar a lista de objetos.
    public static void readAllList() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Pessoa[] pessoas = objectMapper.readValue(new File("pessoas.json"), Pessoa[].class);

        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {

        try {
            readEachObject();
            // readAllList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
