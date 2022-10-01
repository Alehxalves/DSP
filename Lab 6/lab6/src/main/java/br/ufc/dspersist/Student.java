package br.ufc.dspersist;

public class Student {
    private int id;
    private String nome;
    private String cpf;
    private int matricula;
    private String email;
    private String telefone;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        return "{ id: " + getId()
                + ", Nome: " + getNome()
                + ", CPF: " + getCpf()
                + ", Matricula: " + getMatricula()
                + ", Email: " + getEmail()
                + ", Telefone: " + getTelefone() + " }";
    }

}
