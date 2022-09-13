package com.ufc.dspersist;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Livro {

    @JacksonXmlProperty(isAttribute = true)
    private int id;
    private String nome;
    private String autor;
    private String editora;

    public Livro() {

    }

    public Livro(int id, String nome, String autor, String editora) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "{ Id: " + getId() + ", Nome: " + getNome() +
                ", Autor(a): " + getAutor() + ", Editora: " + getEditora() + " }";
    }
}
