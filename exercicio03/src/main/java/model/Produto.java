package model;

// classe que representa o produto
public class Produto {

    private int id;
    private String nome;
    private float preco;
    private String descricao;

    public Produto() {
        id = -1;
        nome = "";
        preco = 0;
        descricao = "";
    }

    public Produto(int id, String nome, float preco, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
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


    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}