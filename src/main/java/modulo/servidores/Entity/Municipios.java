package modulo.servidores.Entity;

public class Municipios {
    private Long id;
    private String nome;
    private int id_estado;

    public Municipios(Long id, String nome, int id_estado) {
        this.id = id;
        this.nome = nome;
        this.id_estado = id_estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }
}
