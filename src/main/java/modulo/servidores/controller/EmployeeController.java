package modulo.servidores.controller;

import modulo.servidores.DAOImpl.EmployeeDAO;
import modulo.servidores.dao.ExceptionDAO;
import modulo.servidores.entity.Employees;
import util.CPF;
import util.DateValidatorUsingIDateFormat;
import util.IDateValidator;

import java.time.LocalDate;

public class EmployeeController {
    private Employees employees;
    private EmployeeDAO employeeDAO;
    private CPF cpfVal;
    private IDateValidator validator;
    public EmployeeController(){
        this.validator = new DateValidatorUsingIDateFormat("yyyy-MM-dd");
    }
    public boolean adicionaEmploees(Integer id, String nome, String cpf, String telefone, String cargo, LocalDate dta_nasc,
                                 String logradouro, String numero, String complemento, String bairro, String cidade,
                                 String estado, String cep) throws ExceptionDAO {

        employeeDAO = new EmployeeDAO();
        cpfVal = new CPF(cpf);
        if (nome != null && nome.length() > 5 &&
                cpfVal.isCPF() && telefone != null &&
                cargo != null &&
                validator.isValid(dta_nasc.toString()) && logradouro != null) {
            try {
                if (id == null) {
                    employees = new Employees(nome, cpf, telefone, cargo, dta_nasc, logradouro, numero, complemento, bairro, cidade, estado, cep);
                    employeeDAO.save(employees);
                    return true;
                } else {
                    employees = new Employees(id, nome, cpf, telefone, cargo, dta_nasc, logradouro, numero, complemento, bairro, cidade, estado, cep);
                    employeeDAO.update(employees);
                    return true;
                }
            } catch (ExceptionDAO e) {

                throw new ExceptionDAO(e.getMessage());
            }
        }
        return false;
    }

    public Employees buscaPorId(int id) throws ExceptionDAO {
        employeeDAO = new EmployeeDAO();
        employees = new Employees();
        try{
            employees = employeeDAO.getById(id);
        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return employees;
    }
}
