package modulo.servidores.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Getter
@Setter
public class Employees {
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;
    private LocalDate dta_nasc;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;
    private ArrayList<Banco> conta_bancaria = new ArrayList<>();

    public Employees() {
    }
    public Employees(String nome, String cpf, String telefone, String cargo, LocalDate dta_nasc, String logradouro, String numero, String complemento, String bairro, String estado, String cidade, String cep) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
        this.dta_nasc = dta_nasc;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
    }
    public Employees(int id, String nome, String cpf, String telefone, String cargo, LocalDate dta_nasc, String logradouro, String numero, String complemento, String bairro, String estado, String cidade, String cep) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
        this.dta_nasc = dta_nasc;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
    }
}
