package principal.principal.modelo;

public class Categoria {

    private Integer id;
    private String tamanho;
    private String embalagem;

    public Categoria() {}

    public Categoria(String tamanho, String embalagem) {
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    public Categoria(Integer id, String tamanho, String embalagem) {
        this.id = id;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }
}
