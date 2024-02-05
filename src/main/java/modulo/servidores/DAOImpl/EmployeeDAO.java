package modulo.servidores.DAOImpl;

import modulo.servidores.dao.IDAO;
import modulo.servidores.dao.SQLExceptionDAO;
import modulo.servidores.entity.Employees;

import java.util.List;

public class EmployeeDAO implements IDAO<Employees> {

    @Override
    public void getById() throws SQLExceptionDAO {

    }

    @Override
    public int delete(int id) throws SQLExceptionDAO {
        return 0;
    }

    @Override
    public int save(Employees employees) throws SQLExceptionDAO {

        return 0;
    }

    @Override
    public int update(Employees employees) throws SQLExceptionDAO {
        return 0;
    }

    @Override
    public List<Employees> getAll() throws SQLExceptionDAO {
        return null;
    }

    @Override
    public List<Employees> getByValue(String value) throws SQLExceptionDAO {
        return null;
    }
}
