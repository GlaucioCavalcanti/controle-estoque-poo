public class ProdutoEletronico extends Produto {
    private String marca;
    private int garantiaMeses;

    // Construtor completo
    public ProdutoEletronico(int codigo, String nome, double preco, int quantidadeEstoque, String marca, int garantiaMeses) {
        super(codigo, nome, preco, quantidadeEstoque);
        this.marca = marca;
        setGarantiaMeses(garantiaMeses);
    }

    // Construtor sobrecarregado (sem quantidade inicial informada)
    public ProdutoEletronico(int codigo, String nome, double preco, String marca, int garantiaMeses) {
        super(codigo, nome, preco);
        this.marca = marca;
        setGarantiaMeses(garantiaMeses);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        if (garantiaMeses < 0) {
            throw new IllegalArgumentException("Erro: O período de garantia não pode ser negativo.");
        }
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Eletrônico | Marca: %s | Garantia: %d meses",
                marca, garantiaMeses);
    }
}
