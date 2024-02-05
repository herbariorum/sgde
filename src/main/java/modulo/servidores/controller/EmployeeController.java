package modulo.servidores.controller;

import modulo.servidores.DAOImpl.EmployeeDAO;
import modulo.servidores.dao.SQLExceptionDAO;
import modulo.servidores.entity.Employees;

import java.time.LocalDate;

public class EmployeeController {
    private Employees employees;
    private EmployeeDAO employeeDAO;
    public boolean adicionaEmploees(Integer id, String nome, String cpf, String telefone, String cargo, LocalDate dta_nasc,
                                 String logradouro, String numero, String complemento, String bairro, String cidade,
                                 String estado, String cep) throws SQLExceptionDAO{
        boolean sucesso = false;
        employeeDAO = new EmployeeDAO();
        try {
            if (id == null){
                employees = new Employees(nome, cpf, telefone, cargo, dta_nasc, logradouro, numero, complemento, bairro, cidade, estado, cep);
                employeeDAO.save(employees);

            }else{
                employees = new Employees(id, nome, cpf, telefone, cargo, dta_nasc, logradouro, numero, complemento, bairro, cidade, estado, cep);
                employeeDAO.update(employees);
            }

        }catch (SQLExceptionDAO e){
            throw new SQLExceptionDAO("Erro ao salvar o registro no banco de dados");
        }
        return sucesso;
    }
}
