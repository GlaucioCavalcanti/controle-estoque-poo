public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    // Construtor 1: Completo com todos os atributos
    public Produto(int codigo, String nome, double preco, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        setPreco(preco);
        setQuantidadeEstoque(quantidadeEstoque);
    }

    // Construtor 2: Sobrecarga com quantidadeEstoque padrão zerada
    public Produto(int codigo, String nome, double preco) {
        this(codigo, nome, preco, 0);
    }

    // Métodos de negócio do estoque
    public boolean adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Erro: A quantidade para adicionar deve ser maior que zero.");
            return false;
        }
        this.quantidadeEstoque += quantidade;
        return true;
    }

    public boolean retirarEstoque(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Erro: A quantidade para retirar deve ser maior que zero.");
            return false;
        }
        if (quantidade > this.quantidadeEstoque) {
            System.out.println("Erro: Saldo insuficiente em estoque. Disponível: " + this.quantidadeEstoque);
            return false;
        }
        this.quantidadeEstoque -= quantidade;
        return true;
    }

    public double calcularValorEstoque() {
        return this.preco * this.quantidadeEstoque;
    }

    // Getters e Setters com validações
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Erro: O preço deve ser maior que zero.");
        }
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Erro: A quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return String.format("Código: %d | Nome: %s | Preço: R$ %.2f | Quantidade em Estoque: %d",
                codigo, nome, preco, quantidadeEstoque);
    }
}
