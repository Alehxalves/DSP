package com.ufc.dspersist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class SerializaXML {

    public static List<Livro> getLivros() {
        Livro l1 = new Livro(1, "Moby Dick", "Herman Melville", "Createspace ");
        Livro l2 = new Livro(2, "Hamlet", "William Shakespeare", "Penguin-Companhia");
        Livro l3 = new Livro(3, "The Odyssey", "Homer", "W. W. Norton & Company");
        Livro l4 = new Livro(4, "The Divine Comedy", "Dante Alighieri", "Bantam Classics");
        Livro l5 = new Livro(5, "The Return of the King", "J. R. R. Tolkien", "Recorded Books");

        List<Livro> livros = new ArrayList<Livro>();
        livros.add(l1);
        livros.add(l2);
        livros.add(l3);
        livros.add(l4);
        livros.add(l5);

        return livros;
    }

    public static void main(String[] args) {
        List<Livro> bookList = getLivros();

        Livros livros = new Livros(bookList);
        File file = new File("livraria.xml");

        try {
            XmlMapper xm = new XmlMapper();
            xm.enable(SerializationFeature.INDENT_OUTPUT);
            xm.writeValue(file, livros);
            System.out.println("Serialização concluída!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
