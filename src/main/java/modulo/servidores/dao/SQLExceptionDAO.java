package modulo.servidores.dao;

import java.sql.SQLException;

public class SQLExceptionDAO extends SQLException {

    public SQLExceptionDAO(String msg){
        super(msg);
    }
}
