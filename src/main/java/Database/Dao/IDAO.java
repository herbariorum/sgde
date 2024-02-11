package Database.Dao;

import java.sql.SQLException;
import java.util.List;

public abstract interface IDAO<T> {
    T getById(Long id) throws ExceptionDAO, SQLException;
    int delete(Long id) throws ExceptionDAO;

    int save(T t) throws ExceptionDAO;

    int update(T t) throws ExceptionDAO;

    List<T> getAll() throws ExceptionDAO;

    List<T> getByValue(String value) throws ExceptionDAO, SQLException;
}
