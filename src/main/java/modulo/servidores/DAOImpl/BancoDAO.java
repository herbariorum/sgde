package modulo.servidores.DAOImpl;

import Database.DB;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.Dao.IDAO;
import modulo.servidores.Entity.Banco;
import modulo.servidores.Entity.Employees;
import modulo.servidores.Entity.Estados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO implements IDAO<Banco> {
    @Override
    public Banco getById(Long id) throws ExceptionDAO {
        return null;
    }

    @Override
    public int delete(Long id) throws ExceptionDAO {
        return 0;
    }

    @Override
    public int save(Banco banco) throws ExceptionDAO {
        return 0;
    }

    @Override
    public int update(Banco banco) throws ExceptionDAO {
        return 0;
    }

    @Override
    public List<Banco> getAll() throws ExceptionDAO {
        var bancos = new ArrayList<Banco>();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT id, nome, agencia, conta, salario FROM banco ORDER BY nome;");) {
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var banco = new Banco();
                    banco.setId(rs.getLong("id"));
                    banco.setNome(rs.getString("nome"));
                    banco.setAgencia(rs.getInt("agencia"));
                    banco.setConta(rs.getInt("conta"));
                    banco.setSalario(rs.getDouble("salario"));
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
        return null;
    }
}
