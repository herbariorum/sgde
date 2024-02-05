package modulo.servidores.dao;

import java.util.List;

public abstract interface IDAO<T> {
    void getById() throws SQLExceptionDAO;

    int delete(int id) throws SQLExceptionDAO;

    int save(T t) throws SQLExceptionDAO;

    int update(T t) throws SQLExceptionDAO;

    List<T> getAll() throws SQLExceptionDAO;

    List<T> getByValue(String value) throws SQLExceptionDAO;
}
