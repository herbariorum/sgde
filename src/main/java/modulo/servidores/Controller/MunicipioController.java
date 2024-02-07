package modulo.servidores.Controller;

import modulo.servidores.DAOImpl.EstadoDAO;
import modulo.servidores.DAOImpl.MunicipioDAO;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.Entity.Estados;
import modulo.servidores.Entity.Municipios;

import java.util.List;

public class MunicipioController {

    public List<Municipios> getByCodigoUf(Long value) throws ExceptionDAO {
        var dao = new MunicipioDAO();
        List<Municipios> lista;
        try{
            lista = dao.getByCodigoUf(value);
        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return lista;
    }
}
