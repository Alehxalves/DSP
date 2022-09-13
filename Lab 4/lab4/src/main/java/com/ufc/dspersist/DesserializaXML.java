package com.ufc.dspersist;

import java.io.File;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class DesserializaXML {
    public static void main(String[] args) {
        try {
            File file = new File("livraria.xml");
            XmlMapper xm = new XmlMapper();

            Livros livros = xm.readValue(file, Livros.class);

            System.out.println(livros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
