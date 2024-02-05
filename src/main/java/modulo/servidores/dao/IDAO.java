package modulo.servidores.dao;

import modulo.servidores.entity.Employees;

import java.util.List;

public abstract interface IDAO<T> {
    Employees getById(int id) throws ExceptionDAO;
    List<T> getByCpf(String cpf) throws ExceptionDAO;
    void delete(int id) throws ExceptionDAO;

    void save(T t) throws ExceptionDAO;

    void update(T t) throws ExceptionDAO;

    List<T> getAll() throws ExceptionDAO;

    List<T> getByValue(String value) throws ExceptionDAO;
}
