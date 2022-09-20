package com.ufc.dspersist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Serializa {

    public static List<Pessoa> getPessoas() {
        Pessoa p1 = new Pessoa(1, "Alex Alves", "001.005.006-99", "Rua A");
        Pessoa p2 = new Pessoa(2, "João Silva", "652.526.999-66", "Rua J");
        Pessoa p3 = new Pessoa(3, "Maria Pereira", "556.666.555.63", "Rua M");
        Pessoa p4 = new Pessoa(4, "Dante Alighieri", "666.666.666-66", "Rua D");
        Pessoa p5 = new Pessoa(5, "J. R. R. Tolkien", "777.777.777.77", "Rua J");

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.add(p1);
        pessoas.add(p2);
        pessoas.add(p3);
        pessoas.add(p4);
        pessoas.add(p5);

        return pessoas;
    }

    // Salvar em disco cada o objeto da lista como pede na questão
    public static void saveEachObjectList(List<Pessoa> pessoas) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        for (Pessoa p : pessoas) {
            objectMapper.writeValue(new File(p.getName().concat(".json")), p);
        }
    }

    // Salve a lista inteira em disco.
    public static void saveAllList(List<Pessoa> pessoas) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("pessoas.json"), pessoas);
    }

    public static void main(String[] args) {
        List<Pessoa> pessoas = getPessoas();

        try {
            saveEachObjectList(pessoas);
            // saveAllList(pessoas);
            System.out.println("Serialização concluída.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
