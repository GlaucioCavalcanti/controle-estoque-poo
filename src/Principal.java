import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final List<Produto> listaProdutos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;

        do {
            exibirMenu();
            try {
                System.out.print("Escolha uma opção: ");
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    continue;
                }
                opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        cadastrarProdutoEletronico();
                        break;
                    case 2:
                        cadastrarProdutoAlimenticio();
                        break;
                    case 3:
                        listarProdutos();
                        break;
                    case 4:
                        menuConsultarProduto();
                        break;
                    case 5:
                        adicionarUnidades();
                        break;
                    case 6:
                        retirarUnidades();
                        break;
                    case 7:
                        consultarValorEmEstoque();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema. Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida! Escolha um número entre 0 e 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido para a opção.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("========== MENU ==========");
        System.out.println("1 - Cadastrar produto eletrônico");
        System.out.println("2 - Cadastrar produto alimentício");
        System.out.println("3 - Listar produtos");
        System.out.println("4 - Consultar produto");
        System.out.println("5 - Adicionar unidades");
        System.out.println("6 - Retirar unidades");
        System.out.println("7 - Consultar valor em estoque");
        System.out.println("0 - Sair");
    }

    // Sobrecarga de método obrigatória: por código (int)
    public static Produto consultarProduto(int codigo) {
        for (Produto p : listaProdutos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // Sobrecarga de método obrigatória: por nome (String)
    public static Produto consultarProduto(String nome) {
        for (Produto p : listaProdutos) {
            if (p.getNome().equalsIgnoreCase(nome.trim())) {
                return p;
            }
        }
        return null;
    }

    private static void cadastrarProdutoEletronico() {
        System.out.println("\n--- Cadastro de Produto Eletrônico ---");
        int codigo = lerCodigoDisponivel();
        if (codigo == -1) return;

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        double preco = lerDoublePositivo("Preço unitário: ");
        int quantidade = lerIntNaoNegativo("Quantidade em estoque: ");

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        int garantiaMeses = lerIntNaoNegativo("Garantia (em meses): ");

        ProdutoEletronico eletronico = new ProdutoEletronico(codigo, nome, preco, quantidade, marca, garantiaMeses);
        listaProdutos.add(eletronico);
        System.out.println("Produto eletrônico cadastrado com sucesso!");
    }

    private static void cadastrarProdutoAlimenticio() {
        System.out.println("\n--- Cadastro de Produto Alimentício ---");
        int codigo = lerCodigoDisponivel();
        if (codigo == -1) return;

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        double preco = lerDoublePositivo("Preço unitário: ");
        int quantidade = lerIntNaoNegativo("Quantidade em estoque: ");

        System.out.print("Data de validade (ex: DD/MM/AAAA): ");
        String dataValidade = scanner.nextLine().trim();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine().trim();

        ProdutoAlimenticio alimenticio = new ProdutoAlimenticio(codigo, nome, preco, quantidade, dataValidade, fabricante);
        listaProdutos.add(alimenticio);
        System.out.println("Produto alimentício cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n--- Lista de Produtos Cadastrados ---");
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado no momento.");
            return;
        }
        for (Produto p : listaProdutos) {
            // Requisito obrigatório: utilizar System.out.println(objeto) para invocar toString()
            System.out.println(p);
        }
    }

    private static void menuConsultarProduto() {
        System.out.println("\n--- Consultar Produto ---");
        System.out.println("1 - Por código");
        System.out.println("2 - Por nome");
        System.out.print("Escolha o método de consulta: ");
        String tipo = scanner.nextLine().trim();

        Produto encontrado = null;
        if (tipo.equals("1")) {
            System.out.print("Digite o código do produto: ");
            try {
                int codigo = Integer.parseInt(scanner.nextLine().trim());
                encontrado = consultarProduto(codigo);
            } catch (NumberFormatException e) {
                System.out.println("Erro: O código deve ser um número inteiro.");
                return;
            }
        } else if (tipo.equals("2")) {
            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine().trim();
            encontrado = consultarProduto(nome);
        } else {
            System.out.println("Opção inválida para consulta.");
            return;
        }

        if (encontrado != null) {
            System.out.println("Produto encontrado:");
            // Requisito obrigatório: System.out.println(objeto)
            System.out.println(encontrado);
        } else {
            System.out.println("Erro: Produto não encontrado.");
        }
    }

    private static void adicionarUnidades() {
        System.out.println("\n--- Adicionar Unidades no Estoque ---");
        System.out.print("Digite o código do produto: ");
        try {
            int codigo = Integer.parseInt(scanner.nextLine().trim());
            Produto p = consultarProduto(codigo);
            if (p == null) {
                System.out.println("Erro: Produto não encontrado.");
                return;
            }

            int quantidade = lerIntPositivo("Quantidade a adicionar: ");
            if (p.adicionarEstoque(quantidade)) {
                System.out.println("Estoque atualizado com sucesso! Nova quantidade: " + p.getQuantidadeEstoque());
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Código inválido.");
        }
    }

    private static void retirarUnidades() {
        System.out.println("\n--- Retirar Unidades do Estoque ---");
        System.out.print("Digite o código do produto: ");
        try {
            int codigo = Integer.parseInt(scanner.nextLine().trim());
            Produto p = consultarProduto(codigo);
            if (p == null) {
                System.out.println("Erro: Produto não encontrado.");
                return;
            }

            int quantidade = lerIntPositivo("Quantidade a retirar: ");
            if (p.retirarEstoque(quantidade)) {
                System.out.println("Estoque atualizado com sucesso! Nova quantidade: " + p.getQuantidadeEstoque());
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Código inválido.");
        }
    }

    private static void consultarValorEmEstoque() {
        System.out.println("\n--- Consultar Valor Total em Estoque ---");
        System.out.print("Digite o código do produto: ");
        try {
            int codigo = Integer.parseInt(scanner.nextLine().trim());
            Produto p = consultarProduto(codigo);
            if (p == null) {
                System.out.println("Erro: Produto não encontrado.");
                return;
            }

            double valorTotal = p.calcularValorEstoque();
            System.out.println("Produto: " + p.getNome());
            System.out.printf("Preço unitário: R$ %.2f | Quantidade: %d\n", p.getPreco(), p.getQuantidadeEstoque());
            System.out.printf("Valor total em estoque: R$ %.2f\n", valorTotal);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Código inválido.");
        }
    }

    // Métodos auxiliares de entrada e validação segura
    private static int lerCodigoDisponivel() {
        while (true) {
            System.out.print("Código: ");
            try {
                int codigo = Integer.parseInt(scanner.nextLine().trim());
                if (consultarProduto(codigo) != null) {
                    System.out.println("Erro: Já existe um produto cadastrado com o código " + codigo + ". Tente outro.");
                    continue;
                }
                return codigo;
            } catch (NumberFormatException e) {
                System.out.println("Erro: O código deve ser um número inteiro.");
            }
        }
    }

    private static double lerDoublePositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                if (valor <= 0) {
                    System.out.println("Erro: O valor deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número decimal válido (ex: 19.99).");
            }
        }
    }

    private static int lerIntNaoNegativo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor < 0) {
                    System.out.println("Erro: O número não pode ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido.");
            }
        }
    }

    private static int lerIntPositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor <= 0) {
                    System.out.println("Erro: A quantidade deve ser maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido.");
            }
        }
    }
}
