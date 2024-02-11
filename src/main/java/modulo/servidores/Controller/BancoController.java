package modulo.servidores.Controller;

import modulo.servidores.DAOImpl.BancoDAO;
import Database.Dao.ExceptionDAO;
import modulo.servidores.Entity.Banco;

import java.sql.SQLException;
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

    public Banco buscarPorId(Long id) throws ExceptionDAO {
        var bancoDao = new BancoDAO();
        Banco banco;
        try{
            banco = bancoDao.getById(id);
        } catch (ExceptionDAO | SQLException e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return banco;
    }

    public Banco buscarPorEmployeeID(Long id) throws ExceptionDAO{
        var bancoDao = new BancoDAO();
        Banco banco;
        try{
            banco = bancoDao.getByEmployeeId(id);
        } catch (ExceptionDAO e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return banco;
    }
    public List<Banco> getByValue(String valor) throws ExceptionDAO{
        var bancoDao = new BancoDAO();
        List<Banco> lista;
        try{
            lista = bancoDao.getByValue(valor);
        }catch (ExceptionDAO e) {
            throw new ExceptionDAO(e.getMessage());
        }
        return lista;
    }

    public boolean save(Long id, String nomeDoBanco, String agenciaNumber, String contaNumber, Double salarioValor, Long employeeId) throws ExceptionDAO{
        var bancoDao = new BancoDAO();
        if (nomeDoBanco != null && agenciaNumber != null && contaNumber != null && salarioValor != null && employeeId != null){
            try{
                if (id == null){
                    this.banco = new Banco(nomeDoBanco, agenciaNumber, contaNumber, salarioValor, employeeId);
                    int idReturn = bancoDao.save(this.banco);
                    if (idReturn > 0) {
                        return true;
                    }
                }else{
                    this.banco = new Banco(id, nomeDoBanco, agenciaNumber, contaNumber, salarioValor, employeeId);
                    int idReturn = bancoDao.update(this.banco);
                    if (idReturn > 0) {
                        return true;
                    }
                }
            }catch (ExceptionDAO e){
                throw new ExceptionDAO(e.getMessage());
            }
        }
        return false;
    }

    public boolean deleteConta(Long id) throws ExceptionDAO{
        var dao = new BancoDAO();
        try{
            int idReturn = dao.delete(id);
            if (idReturn > 0) {
                return true;
            }
        }catch (ExceptionDAO e){
            throw new ExceptionDAO(e.getMessage());
        }
        return false;
    }

    private Banco banco;
}
