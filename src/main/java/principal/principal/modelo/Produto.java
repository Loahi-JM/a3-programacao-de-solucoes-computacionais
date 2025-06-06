package principal.principal.modelo;

import principal.principal.connection.ProdutoDao;

import java.util.ArrayList;

public class Produto extends Categoria {

    private Integer id;
    private String nome;
    private Double precoUnitario;
    private Integer unidade;
    private Integer quantidadeEmEstoque;
    private Integer quantidadeMinimaEmEstoque;
    private Integer quantidadeMaximaEmEstoque;
    private ProdutoDao dao;

    public Produto() {}

    public Produto(String nome, Double precoUnitario, Integer unidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        this.dao = new ProdutoDao();
    }

    public Produto(Integer id, String nome, Double precoUnitario, Integer unidade, Integer qtdEstoque, Integer qtdMinima, Integer qtdMaxima) {
        this.id = id;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        this.quantidadeEmEstoque = qtdEstoque;
        this.quantidadeMinimaEmEstoque = qtdMinima;
        this.quantidadeMaximaEmEstoque = qtdMaxima;
        this.dao = new ProdutoDao();
    }

    public Produto(String nome, Double precoUnitario, Integer unidade, Integer qtdEstoque, Integer qtdMinima, Integer qtdMaxima) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        this.quantidadeEmEstoque = qtdEstoque;
        this.quantidadeMinimaEmEstoque = qtdMinima;
        this.quantidadeMaximaEmEstoque = qtdMaxima;
        this.dao = new ProdutoDao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getUnidade() {
        return unidade;
    }

    public void setUnidade(Integer unidade) {
        this.unidade = unidade;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Integer getQuantidadeMinimaEmEstoque() {
        return quantidadeMinimaEmEstoque;
    }

    public void setQuantidadeMinimaEmEstoque(Integer quantidadeMinimaEmEstoque) {
        this.quantidadeMinimaEmEstoque = quantidadeMinimaEmEstoque;
    }

    public Integer getQuantidadeMaximaEmEstoque() {
        return quantidadeMaximaEmEstoque;
    }

    public void setQuantidadeMaximaEmEstoque(Integer quantidadeMaximaEmEstoque) {
        this.quantidadeMaximaEmEstoque = quantidadeMaximaEmEstoque;
    }

//    public ArrayList<Produto> getMinhaLista() {
//        return dao.getMinhaLista();
//    }
}
