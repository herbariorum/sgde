package modulo.servidores.DAOImpl;

import database.DB;
import modulo.servidores.dao.IDAO;
import modulo.servidores.dao.ExceptionDAO;
import modulo.servidores.entity.Employees;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IDAO<Employees> {

    private String sqlBuscaPorCpf = "SELECT * FROM employees WHERE cpf = ?";
    private String sqlBuscaPorID = "SELECT * FROM employees WHERE id = ?";
    private String sqlInsert = "INSERT INTO employees(nome, cpf, telefone, cargo, dta_nasc, logradouro, numero," +
            " complemento, bairro, cidade, estado, cep) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private String sqlUpdate = "UPDATE employees SET nome = ?, cpf = ?, telefone = ?, cargo = ?, dta_nasc = ?, logradouro = ?, numero = ?," +
            " complemento = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?";

    @Override
    public Employees getById(int id) throws ExceptionDAO {
        Employees employee = null;
        try (Connection connection = DB.getConexao(); PreparedStatement stmt = connection.prepareStatement(sqlBuscaPorID);) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employee = new Employees();
                    employee.setId(rs.getInt("id"));
                    employee.setNome(rs.getString("nome"));
                    employee.setCpf(rs.getString("cpf"));
                    employee.setTelefone(rs.getString("telefone"));
                    employee.setCargo(rs.getString("cargo"));
                    employee.setDta_nasc(rs.getDate("dta_nasc").toLocalDate());
                    employee.setLogradouro(rs.getString("logradouro"));
                    employee.setNumero(rs.getString("numero"));
                    employee.setComplemento(rs.getString("complemento"));
                    employee.setBairro(rs.getString("bairro"));
                    employee.setCidade(rs.getString("cidade"));
                    employee.setEstado(rs.getString("estado"));
                    employee.setCep(rs.getString("cep"));
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar o cpf\n" + e.getMessage());
        }
        return employee;
    }

    @Override
    public List<Employees> getByCpf(String cpf) throws ExceptionDAO {
        List<Employees> employees = new ArrayList<>();
        try (Connection connection = DB.getConexao(); PreparedStatement stmt = connection.prepareStatement(sqlBuscaPorCpf);) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employees employee = new Employees();
                    employee.setId(rs.getInt("id"));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar o cpf\n" + e.getMessage());
        }
        return employees;
    }

    @Override
    public void delete(int id) throws ExceptionDAO {

    }

    @Override
    public void save(Employees employees) throws ExceptionDAO {
        try (Connection connection = DB.getConexao(); PreparedStatement stmt = connection.prepareStatement(sqlInsert);) {
            List<Employees> lista = getByCpf(employees.getCpf());
            if (lista.isEmpty()) {
                stmt.setString(1, employees.getNome());
                stmt.setString(2, employees.getCpf());
                stmt.setString(3, employees.getTelefone());
                stmt.setString(4, employees.getCargo());
                stmt.setDate(5, Date.valueOf(employees.getDta_nasc()));
                stmt.setString(6, employees.getLogradouro());
                stmt.setString(7, employees.getNumero());
                stmt.setString(8, employees.getComplemento());
                stmt.setString(9, employees.getBairro());
                stmt.setString(10, employees.getCidade());
                stmt.setString(11, employees.getEstado());
                stmt.setString(12, employees.getCep());
                stmt.executeUpdate();
            } else {
                throw new ExceptionDAO("Este cpf já está cadastrado na base de dados.");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao inserir o registro.\n" + e.getMessage());
        }
    }

    @Override
    public void update(Employees employees) throws ExceptionDAO {
        try (Connection connection = DB.getConexao(); PreparedStatement stmt = connection.prepareStatement(sqlUpdate);) {
            stmt.setString(1, employees.getNome());
            stmt.setString(2, employees.getCpf());
            stmt.setString(3, employees.getTelefone());
            stmt.setString(4, employees.getCargo());
            stmt.setDate(5, Date.valueOf(employees.getDta_nasc()));
            stmt.setString(6, employees.getLogradouro());
            stmt.setString(7, employees.getNumero());
            stmt.setString(8, employees.getComplemento());
            stmt.setString(9, employees.getBairro());
            stmt.setString(10, employees.getCidade());
            stmt.setString(11, employees.getEstado());
            stmt.setString(12, employees.getCep());
            stmt.setInt(13, employees.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao atualizar o registro:\n" + e.getMessage());
        }
    }

    @Override
    public List<Employees> getAll() throws ExceptionDAO {
        return null;
    }

    @Override
    public List<Employees> getByValue(String value) throws ExceptionDAO {
        return null;
    }
}
