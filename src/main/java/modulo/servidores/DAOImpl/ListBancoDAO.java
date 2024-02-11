package modulo.servidores.DAOImpl;

import Database.DB;
import Database.Dao.ExceptionDAO;
import modulo.servidores.Entity.ListBancos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListBancoDAO {

    public List<ListBancos> getAll() throws ExceptionDAO {
        var listaBancos = new ArrayList<ListBancos>();
        try (var connection = DB.getConexao();
             var stmt = connection.prepareStatement("SELECT cod, banco FROM lista_bancos ORDER BY banco"); ) {
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    var bancos = new ListBancos();
                    bancos.setCod(rs.getLong("cod"));
                    bancos.setBanco(rs.getString("banco"));
                    listaBancos.add(bancos);
                }
            }
        }catch (SQLException e) {
            throw new ExceptionDAO("Ocorreu o seguinte erro ao listar os bancos\n" + e.getMessage());
        }
        return listaBancos;
    }
}
