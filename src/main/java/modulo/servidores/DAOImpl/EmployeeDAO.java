package modulo.servidores.DAOImpl;

import Database.DB;
import Database.Dao.IDAO;
import Database.Dao.ExceptionDAO;
import modulo.servidores.Entity.Banco;
import modulo.servidores.Entity.Employees;
import util.ComboBoxList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IDAO<Employees> {

    private String sqlBuscaTodos = "SELECT * FROM employees";
    private String sqlBuscaPorCpf = "SELECT * FROM employees WHERE cpf = ?";
    //    private String sqlBuscaPorID = "SELECT * FROM employees WHERE id = ?";
    private String sqlBuscaPorID = "SELECT e.*, b.id as bancoId, b.nome as bancoNome, b.agencia, b.conta, b.salario FROM employees e INNER JOIN banco b ON e.id = b.employee_id WHERE e.id = ?";
    private String sqlDelete = "DELETE FROM employees WHERE id = ?";
    private String sqlBuscaPorValor = "SELECT * FROM employees WHERE LOWER(nome) Like ? ORDER BY nome";
    private String sqlInsertEmployee = "INSERT INTO employees(nome, cpf, telefone, cargo, dta_nasc, logradouro, numero," +
            " complemento, bairro, cidade, estado, cep) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private String sqlInsertBanco = "INSERT INTO banco(nome, agencia, conta, salario, employee_id) VALUES(?,?,?,?,?)";
    private String sqlUpdate = "UPDATE employees SET nome = ?, cpf = ?, telefone = ?, cargo = ?, dta_nasc = ?, logradouro = ?, numero = ?," +
            " complemento = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?";
    private String sqlUpdateBanco = "UPDATE banco SET nome = ?, agencia = ?, conta = ? , salario = ? , employee_id =? WHERE id = ?";
    private ArrayList<ComboBoxList> list;

    public ArrayList<ComboBoxList> getList() {
        return list;
    }

    public void setList(ArrayList<ComboBoxList> list) {
        this.list = list;
    }

    @Override
    public Employees getById(Long id) throws ExceptionDAO {
        Employees employee = null;
        ArrayList<Banco> listaBancos = new ArrayList<>();
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
                    // set as informações bancárias
                    Long idBanco = rs.getLong("bancoId");
                    String nomeBanco = rs.getString("bancoNome");
                    String agencia = rs.getString("agencia");
                    String conta = rs.getString("conta");
                    double salario = rs.getDouble("salario");
                    Banco banco = new Banco(idBanco, nomeBanco, agencia, conta, salario);
                    listaBancos.add(banco);
                    employee.setConta_bancaria(listaBancos);
                    // -----
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
        int insertedRow = 0;
        int insertedBanco = 0;
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlInsertEmployee, Statement.RETURN_GENERATED_KEYS);) {
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
                // Executa o INSERT e retorna o 1 de inserido corretamente
                insertedRow = stmt.executeUpdate();
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        //devo pegar o ultimo insedio
                        try (var stmtb = connection.prepareStatement(sqlInsertBanco, Statement.RETURN_GENERATED_KEYS);) {
                            for (Banco banco : employees.getConta_bancaria()) {
                                stmtb.setString(1, banco.getNome());
                                stmtb.setString(2, banco.getAgencia());
                                stmtb.setString(3, banco.getConta());
                                stmtb.setDouble(4, banco.getSalario());
                                stmtb.setLong(5, generatedKeys.getLong(1));
                                insertedBanco = stmtb.executeUpdate();
                            }
                        }

                        if ((insertedRow > 0) && (insertedBanco > 0)) {
                            return 1;
                        }
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
        int updatedRow = 0;
        int updatedBanco = 0;
        Long idEmployee = employees.getId();
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
            stmt.setLong(13, idEmployee);
            updatedRow = stmt.executeUpdate();
            try (var stmtb = connection.prepareStatement(sqlUpdateBanco, Statement.RETURN_GENERATED_KEYS);) {
                for (Banco banco : employees.getConta_bancaria()) {
                    stmtb.setString(1, banco.getNome());
                    stmtb.setString(2, banco.getAgencia());
                    stmtb.setString(3, banco.getConta());
                    stmtb.setDouble(4, banco.getSalario());
                    stmtb.setLong(5, idEmployee);
                    stmtb.setLong(6, banco.getId());
                    updatedBanco = stmtb.executeUpdate();
                }
            }
            if ((updatedRow > 0) && (updatedBanco > 0)) {
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
        var employees = new ArrayList<Employees>();
        try (var connection = DB.getConexao(); var stmt = connection.createStatement();) {
            try (var rs = stmt.executeQuery(sqlBuscaTodos)) {
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
            throw new ExceptionDAO("Ocorreu o seguinte erro ao listar servidores\n" + e.getMessage());
        }
        return employees;
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

    public void comboBoxEmployee() throws ExceptionDAO {
        this.setList(new ArrayList<>());
        List<Employees> employeesList = getAll();
        for (Employees e : employeesList) {
            this.getList().add(new ComboBoxList(e.getId(), e.getNome()));
        }
    }
}
