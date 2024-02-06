package modulo.servidores.DAOImpl;

import Database.DB;
import modulo.servidores.Dao.IDAO;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.Entity.Employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IDAO<Employees> {

    private String sqlBuscaPorCpf = "SELECT * FROM employees WHERE cpf = ?";
    private String sqlBuscaPorID = "SELECT * FROM employees WHERE id = ?";
    private String sqlDelete = "DELETE FROM employees WHERE id = ?";
    private String sqlBuscaPorValor = "SELECT * FROM employees WHERE LOWER(nome) Like ? ORDER BY nome";
    private String sqlInsert = "INSERT INTO employees(nome, cpf, telefone, cargo, dta_nasc, logradouro, numero," +
            " complemento, bairro, cidade, estado, cep) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private String sqlUpdate = "UPDATE employees SET nome = ?, cpf = ?, telefone = ?, cargo = ?, dta_nasc = ?, logradouro = ?, numero = ?," +
            " complemento = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?";

    @Override
    public Employees getById(Long id) throws ExceptionDAO {
        Employees employee = null;
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlBuscaPorID);) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employee = new Employees();
                    employee.setId(rs.getLong("id"));
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
                    employee.setId(rs.getLong("id"));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar o cpf\n" + e.getMessage());
        }
        return employees;
    }

    @Override
    public int delete(Long id) throws ExceptionDAO {
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlDelete);) {
            stmt.setLong(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao deletar o registro\n" + e.getMessage());
        }
    }

    @Override
    public int save(Employees employees) throws ExceptionDAO {
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);) {
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
                // Executa o INSERT e retorna o id do item adicionado
                int insertedRow = stmt.executeUpdate();
                if (insertedRow > 0) {
                    var rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } else {
                throw new ExceptionDAO("Este cpf já está cadastrado na base de dados.");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao inserir o registro.\n" + e.getMessage());
        }
        return -1;
    }

    @Override
    public int update(Employees employees) throws ExceptionDAO {
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);) {
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
            stmt.setLong(13, employees.getId());
            int insertedRow = stmt.executeUpdate();
            if (insertedRow > 0) {
                var rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao atualizar o registro:\n" + e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Employees> getAll() throws ExceptionDAO {
        return null;
    }

    @Override
    public List<Employees> getByValue(String value) throws ExceptionDAO {
        var employees = new ArrayList<Employees>();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlBuscaPorValor);) {
            stmt.setString(1, "%" + value.toLowerCase() + "%");
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var employee = new Employees();
                    employee.setId(rs.getLong("id"));
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
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar o cpf\n" + e.getMessage());
        }
        return employees;
    }
}
