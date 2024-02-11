package modulo.servidores.Entity;


public class Banco {
    private Long id;
    private String nome;
    private String agencia;
    private String conta;
    private double salario;
    private Long employee_id;

    public Banco() {
    }

    public Banco(Long id, String nome, String agencia, String conta, double salario, Long idEmployee) {
        this.id = id;
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.salario = salario;
        this.employee_id = idEmployee;
    }
    public Banco(Long id, String nome, String agencia, String conta, double salario) {
        this.id = id;
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.salario = salario;
    }

    public Banco(String nome, String agencia, String conta, double salario, Long idEmployee) {
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.salario = salario;
        this.employee_id = idEmployee;
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

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", agencia=" + agencia +
                ", conta=" + conta +
                ", salario=" + salario +
                ", employee_id=" + employee_id +
                '}';
    }
}
