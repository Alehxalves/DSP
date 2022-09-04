import java.io.Serializable;

class CarroEntity implements Serializable {

    private String modelo;
    private String cor;
    private int ano;
    private String fabricante;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "{ Modelo: " + getModelo() + ", Cor: " + getCor() + ", Ano: "
                + getAno() + ", Fabricante: " + getFabricante() + " }";
    }
}