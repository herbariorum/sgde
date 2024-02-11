package modulo.servidores.DAOImpl;

import Database.DB;
import Database.Dao.ExceptionDAO;
import Database.Dao.IDAO;
import modulo.servidores.Entity.Banco;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO implements IDAO<Banco> {
    private String sqlInsert = "INSERT INTO banco(nome, agencia, conta, salario, employee_id) VALUES(?,?,?,?,?)";
    private String sqlUpdate = "UPDATE banco SET nome = ?, agencia = ?, conta = ? , salario = ? , employee_id =? WHERE id = ?";
    private String sqlDelete = "DELETE FROM banco WHERE id = ?";

    public Banco getByEmployeeId(Long id) throws ExceptionDAO {
        var banco = new Banco();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT * FROM banco WHERE employee_id = ? ORDER BY nome");) {
            stmt.setLong(1, id);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    banco.setId(rs.getLong("id"));
                    banco.setNome(rs.getString("nome"));
                    banco.setAgencia(rs.getString("agencia"));
                    banco.setConta(rs.getString("conta"));
                    banco.setSalario(rs.getDouble("salario"));
                    banco.setEmployee_id(rs.getLong("employee_id"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return banco;
    }

    @Override
    public Banco getById(Long id) throws ExceptionDAO, SQLException {
        var banco = new Banco();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT * FROM banco WHERE id = ? ORDER BY nome");) {
            stmt.setLong(1, id);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    banco.setId(rs.getLong("id"));
                    banco.setNome(rs.getString("nome"));
                    banco.setAgencia(rs.getString("agencia"));
                    banco.setConta(rs.getString("conta"));
                    banco.setSalario(rs.getDouble("salario"));
                    banco.setEmployee_id(rs.getLong("employee_id"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return banco;
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
    public int save(Banco banco) throws ExceptionDAO {
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, banco.getNome());
            stmt.setString(2, banco.getAgencia());
            stmt.setString(3, banco.getConta());
            stmt.setDouble(4, banco.getSalario());
            stmt.setLong(5, banco.getEmployee_id());
            int insertedRow = stmt.executeUpdate();
            if (insertedRow > 0) {
                var rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }catch (SQLException e ){
            throw new ExceptionDAO("Ocorreu o seguinte erro ao inserir o registro.\n" + e.getMessage());
        }
        return -1;
    }

    @Override
    public int update(Banco banco) throws ExceptionDAO {
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, banco.getNome());
            stmt.setString(2, banco.getAgencia());
            stmt.setString(3, banco.getConta());
            stmt.setDouble(4, banco.getSalario());
            stmt.setLong(5, banco.getEmployee_id());
            stmt.setLong(6, banco.getId());
            int insertedRow = stmt.executeUpdate();
            if (insertedRow > 0) {
                var rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }catch (SQLException e ){
            throw new ExceptionDAO("Ocorreu o seguinte erro ao inserir o registro.\n" + e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Banco> getAll() throws ExceptionDAO {
        var bancos = new ArrayList<Banco>();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT id, nome, agencia, conta, salario FROM banco ORDER BY nome ASC;");) {
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var banco = new Banco();
                    banco.setId(rs.getLong("id"));
                    banco.setNome(rs.getString("nome"));
                    banco.setAgencia(rs.getString("agencia"));
                    banco.setConta(rs.getString("conta"));
                    banco.setSalario(rs.getDouble("salario"));
                    banco.setEmployee_id(rs.getLong("employee_id"));
                    bancos.add(banco);
                }
            }
        }catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao listar os bancos\n" + e.getMessage());
        }
        return bancos;
    }

    @Override
    public List<Banco> getByValue(String value) throws ExceptionDAO {
        var bancoList = new ArrayList<Banco>();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT * FROM banco WHERE LOWER(nome) Like ? ORDER BY nome");) {
            stmt.setString(1, "%"+value.toLowerCase()+"%");
            try(var rs = stmt.executeQuery()){
                while (rs.next()){
                    var banco = new Banco();
                    banco.setId(rs.getLong("id"));
                    banco.setNome(rs.getString("nome"));
                    banco.setAgencia(rs.getString("agencia"));
                    banco.setConta(rs.getString("conta"));
                    banco.setSalario(rs.getDouble("salario"));
                    banco.setEmployee_id(rs.getLong("employee_id"));
                    bancoList.add(banco);
                }
            }
        } catch (SQLException e){
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar o banco\n" + e.getMessage());
        }
        return bancoList;
    }
}
