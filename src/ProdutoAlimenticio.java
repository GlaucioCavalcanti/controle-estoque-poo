public class ProdutoAlimenticio extends Produto {
    private String dataValidade;
    private String fabricante;

    // Construtor completo
    public ProdutoAlimenticio(int codigo, String nome, double preco, int quantidadeEstoque, String dataValidade, String fabricante) {
        super(codigo, nome, preco, quantidadeEstoque);
        this.dataValidade = dataValidade;
        this.fabricante = fabricante;
    }

    // Construtor sobrecarregado (sem quantidade inicial informada)
    public ProdutoAlimenticio(int codigo, String nome, double preco, String dataValidade, String fabricante) {
        super(codigo, nome, preco);
        this.dataValidade = dataValidade;
        this.fabricante = fabricante;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Alimentício | Validade: %s | Fabricante: %s",
                dataValidade, fabricante);
    }
}
