package modulo.servidores.DAOImpl;

import Database.DB;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.Dao.IDAO;
import modulo.servidores.Entity.Employees;
import modulo.servidores.Entity.Municipios;
import util.ComboBoxList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO implements IDAO<Municipios> {
    private String sqlBuscaPorValor = "SELECT id, nome, id_estado FROM municipios WHERE id_estado = ? ORDER BY nome";


    @Override
    public Municipios getById(Long id) throws ExceptionDAO {
        return null;
    }

    @Override
    public int delete(Long id) throws ExceptionDAO {
        return 0;
    }

    @Override
    public int save(Municipios municipios) throws ExceptionDAO {
        return 0;
    }

    @Override
    public int update(Municipios municipios) throws ExceptionDAO {
        return 0;
    }

    @Override
    public List<Municipios> getAll() throws ExceptionDAO {
        return null;
    }

    @Override
    public List<Municipios> getByValue(String value) throws ExceptionDAO {
        return null;
    }

    public List<Municipios> getByCodigoUf(Long value) throws ExceptionDAO{
        List<Municipios> municipiosList = new ArrayList<>();
        try (var connection = DB.getConexao(); var stmt = connection.prepareStatement(sqlBuscaPorValor);) {
            stmt.setLong(1, value);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var municipio = new Municipios(rs.getLong("id"), rs.getString("nome"), rs.getInt("id_estado"));
                    municipiosList.add(municipio);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao buscar o município\n" + e.getMessage());
        }
        return municipiosList;
    }


}
