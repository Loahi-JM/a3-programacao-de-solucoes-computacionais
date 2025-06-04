package principal.principal.modelo;

public class Produto extends Categoria {

    private Integer id;
    private String nome;
    private Double precoUnitario;
    private Integer unidade;
    private Integer quantidadeEmEstoque;
    private Integer quantidadeMinimaEmEstoque;
    private Integer quantidadeMaximaEmEstoque;

    public Produto() {}

    public Produto(String nome, Double precoUnitario, Integer unidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
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
}
