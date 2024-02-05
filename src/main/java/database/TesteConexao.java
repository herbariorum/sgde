package database;

import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        var sqlDb = "USE sgde;";
        var sqlTable = "CREATE TABLE employees (\n" +
                "id serial primary key not null,\n" +
                "nome varchar(20) not null,\n" +
                "cpf varchar(11) not null unique,\n" +
                "telefone varchar(11),\n" +
                "cargo varchar(80),\n" +
                "dta_nasc date,\n" +
                "logradouro varchar(100) not null,\n" +
                "numero varchar(10),\n" +
                "complemento varchar(100),\n" +
                "bairro varchar(80),\n" +
                "cidade varchar(100),\n" +
                "estado varchar(100),\n" +
                "cep varchar(8)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE banco(\n" +
                " id serial primary key not null,\n" +
                " nome varchar(80) not null,\n" +
                " agencia int,\n" +
                " conta int,\n" +
                " salario numeric(10, 2),\n" +
                " employee_id int unique not null references \"employees\" (id) on delete cascade\n" +
                ");";
        try(var conexao = DB.getConexao(); var stmt = conexao.createStatement()){
//            stmt.execute(sqlDb);
            stmt.execute(sqlTable);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
