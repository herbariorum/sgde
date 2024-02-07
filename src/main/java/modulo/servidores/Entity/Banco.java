package modulo.servidores.Entity;


public class Banco {
    private Long id;
    private String nome;
    private int agencia;
    private int conta;
    private double salario;
    private Employees employee;

    public Banco() {
    }

    public Banco(String nome, int agencia, int conta, double salario, Long codEmployee) {
        this.nome = nome;
        this.agencia = agencia;
        this.conta = conta;
        this.salario = salario;
        var employee = new Employees();
        employee.setId(codEmployee);
        this.employee = employee;
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

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
}
