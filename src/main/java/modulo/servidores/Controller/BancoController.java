package modulo.servidores.Controller;

import modulo.servidores.DAOImpl.BancoDAO;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.Entity.Banco;

import java.util.List;

public class BancoController {

    public List<Banco> getAll() throws ExceptionDAO {
        var bancoDao = new BancoDAO();
        List<Banco> lista;
        try {
            lista = bancoDao.getAll();
        } catch (ExceptionDAO e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return lista;
    }
}
