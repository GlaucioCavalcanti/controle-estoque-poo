# Grupo 3 - Sistema de Controle de Estoque de Loja (POO - Java)

Trabalho de Programação Orientada a Objetos em Java (Console), desenvolvido estritamente de acordo com os requisitos definidos no documento de especificações.

---

## 📁 Estrutura de Arquivos

```text
SETA_13-POO/
├── assets/
│   └── requisições/
│       └── Grupo_3_Controle_Estoque.docx.PDF
├── bin/                       # Bytecodes compilados (.class)
└── src/
    ├── Produto.java            # Superclasse base com atributos e métodos comuns
    ├── ProdutoEletronico.java  # Subclasse de eletrônicos (marca, garantiaMeses)
    ├── ProdutoAlimenticio.java # Subclasse de alimentícios (dataValidade, fabricante)
    └── Principal.java          # Classe executável com menu interativo e Scanner
```

---

## 🎯 Conceitos de POO Aplicados

1. **Encapsulamento**:
   - Todos os atributos foram definidos como `private`.
   - Acesso controlado via `getters` e `setters`, contendo validações contra valores negativos para estoque e garantia, e validação de preço estritamente positivo (> 0).

2. **Construtores e Sobrecarga**:
   - A superclasse [`Produto`](file:///c:/Users/glauc/Downloads/SETA_13-POO/src/Produto.java) implementa construtores sobrecarregados com diferentes assinaturas:
     - `Produto(int codigo, String nome, double preco, int quantidadeEstoque)`
     - `Produto(int codigo, String nome, double preco)`
   - As subclasses invocam os construtores da superclasse via `super(...)`.

3. **Herança**:
   - [`ProdutoEletronico`](file:///c:/Users/glauc/Downloads/SETA_13-POO/src/ProdutoEletronico.java) e [`ProdutoAlimenticio`](file:///c:/Users/glauc/Downloads/SETA_13-POO/src/ProdutoAlimenticio.java) estendem (`extends`) a superclasse `Produto`.

4. **Sobrescrita (`@Override`) e `toString()`**:
   - Cada subclasse sobrescreve o método `toString()`, aproveitando `super.toString()` e adicionando seus atributos particulares.
   - Na listagem e consulta, os produtos são impressos diretamente via `System.out.println(objeto)`, garantindo polimorfismo dinâmico.

5. **Sobrecarga de Métodos**:
   - Na classe [`Principal`](file:///c:/Users/glauc/Downloads/SETA_13-POO/src/Principal.java), foram implementados os métodos sobrecarregados:
     - `consultarProduto(int codigo)`
     - `consultarProduto(String nome)`

---

## 🚀 Como Compilar e Executar

### 1. Compilação
No terminal do projeto:
```bash
javac -d bin src/*.java
```

### 2. Execução
```bash
java -cp bin Principal
```
