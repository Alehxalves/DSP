package com.ufc.dspersist;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "livraria")
public class Livros {

    @JacksonXmlElementWrapper(localName = "livros")
    @JacksonXmlProperty(localName = "livro")
    private List<Livro> livros;

    public Livros() {
    }

    public Livros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return this.livros.toString();
    }
}
