package com.ufc.dspersist;

/*
Enunciado:
1. Crie uma classe Java de entidade. Exemplo: Filme (id, titulo, sinopse, diretor).
- OBS: A classe não precisa implementar a interface java.io.Serializable.

2. Crie uma classe Java de nome Serializa para instanciar objetos da classe definida na Questão 1 e adicionar esses objetos em uma Lista. Depois, percorrer a lista e Serializar os objetos em disco/ssd. 
Serialize usando JSON através da biblioteca Jackson.

3. Crie uma classe java de nome Desserializa para ler / desserializar os objetos Serializados na Questão 2 e exibi-los também através do uso da bilbioteca
 */

public class Pessoa {

    private int id;
    private String name;
    private String cpf;
    private String endereco;

    public Pessoa() {

    }

    public Pessoa(int id, String name, String cpf, String endereco) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String toString() {
        return "{ id: " + getId() + ", Nome: " + getName() + ", CPF: "
                + getCpf() + ", Endereco: " + getEndereco() + " }";
    }

}