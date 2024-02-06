package modulo.servidores.Controller;

import modulo.servidores.DAOImpl.EmployeeDAO;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.Entity.Employees;
import util.CPF;
import util.DateValidatorUsingIDateFormat;
import util.IDateValidator;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private Employees employees;
    private EmployeeDAO employeeDAO;
    private CPF cpfVal;
    private IDateValidator validator;
    public EmployeeController(){
        this.validator = new DateValidatorUsingIDateFormat("yyyy-MM-dd");
    }
    public boolean adicionaEmploees(Long id, String nome, String cpf, String telefone, String cargo, LocalDate dta_nasc,
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
                    int idReturn = employeeDAO.save(employees);
                    if (idReturn > 0) {
                        return true;
                    }
                } else {
                    employees = new Employees(id, nome, cpf, telefone, cargo, dta_nasc, logradouro, numero, complemento, bairro, cidade, estado, cep);
                    int idReturn = employeeDAO.update(employees);
                    if (idReturn > 0) {
                        return true;
                    }
                }
            } catch (ExceptionDAO e) {
                throw new ExceptionDAO(e.getMessage());
            }
        }
        return false;
    }

    public Employees buscaPorId(Long id) throws ExceptionDAO {
        employeeDAO = new EmployeeDAO();
        employees = new Employees();
        try{
            employees = employeeDAO.getById(id);
        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return employees;
    }

    public List<Employees> buscaPorValor(String valor) throws ExceptionDAO{
        employeeDAO = new EmployeeDAO();
        List<Employees> lista;
        try{
            lista = employeeDAO.getByValue(valor);

        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return lista;
    }

    public boolean deleteEmployees(Long id) throws ExceptionDAO{
        var dao = new EmployeeDAO();
        try{
            int idReturn = dao.delete(id);
            if (idReturn > 0) {
                return true;
            }
        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return false;
    }
}
