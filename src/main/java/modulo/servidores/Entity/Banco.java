package modulo.servidores.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Banco {
    private Long id;
    private String nome;
    private int agencia;
    private int conta;
    private double salario;
    private Employees employee;

    public Banco() {
    }

    public Banco(Long id, String nome, int agencia, int conta, double salario) {
        this.id = id;
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.salario = salario;
    }
}
