package modulo.servidores.Controller;

import modulo.servidores.DAOImpl.EstadoDAO;
import Database.Dao.ExceptionDAO;
import modulo.servidores.Entity.Estados;

import java.util.List;

public class EstadoController {

    public List<Estados> getAll() throws ExceptionDAO {
        var dao = new  EstadoDAO();
        List<Estados> lista;
        try{
            lista = dao.getAll();
        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return lista;
    }

    public Estados findByName(String name) throws ExceptionDAO{
        var dao = new EstadoDAO();
        var estado = new Estados();
        try{
            estado = dao.getByName(name);
        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return estado;
    }
}
