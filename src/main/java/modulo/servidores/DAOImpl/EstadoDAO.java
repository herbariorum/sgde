package modulo.servidores.DAOImpl;

import Database.DB;

import Database.Dao.ExceptionDAO;
import Database.Dao.IDAO;
import modulo.servidores.Entity.Estados;
import util.ComboBoxList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements IDAO<Estados> {
    private ArrayList<ComboBoxList> list;

    public ArrayList<ComboBoxList> getList() {
        return list;
    }

    public void setList(ArrayList<ComboBoxList> list) {
        this.list = list;
    }

    @Override
    public Estados getById(Long id) throws ExceptionDAO {
        return null;
    }

    @Override
    public int delete(Long id) throws ExceptionDAO {
        return 0;
    }

    @Override
    public int save(Estados estados) throws ExceptionDAO {
        return 0;
    }

    @Override
    public int update(Estados estados) throws ExceptionDAO {
        return 0;
    }

    @Override
    public List<Estados> getAll() throws ExceptionDAO {
        var estados = new ArrayList<Estados>();
        try (var connection = DB.getConexao();
             var stmt = connection.prepareStatement("SELECT id, nome FROM estados ORDER BY nome;"); ) {
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var estado = new Estados();
                    estado.setId(rs.getLong("id"));
                    estado.setNome(rs.getString("nome"));
                    estados.add(estado);
                }
            }
        }catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao listar os estados\n" + e.getMessage());
        }
        return estados;
    }

    @Override
    public List<Estados> getByValue(String value) throws ExceptionDAO {
        return null;
    }

    public Estados getByName(String value) throws ExceptionDAO {
        var estado = new Estados();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement("SELECT * FROM estados WHERE nome = ?");) {
            stmt.setString(1, value);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    estado = new Estados(rs.getLong("id"), rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar o estado\n" + e.getMessage());
        }
        return estado;
    }

    public void comboBoxEstado() throws ExceptionDAO {
        this.setList(new ArrayList<>());
        List<Estados> estadosList = getAll();
        for (Estados e : estadosList){
            this.getList().add(new ComboBoxList(e.getId(), e.getNome()));
        }
    }
}
