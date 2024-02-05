package modulo.servidores.dao;

public class ExceptionDAO extends Exception {

    public ExceptionDAO(String msg, Throwable cause){
        super(msg, cause);
    }
    public ExceptionDAO(String msg){
        super(msg);
    }
}
