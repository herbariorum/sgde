package Database;

import modulo.servidores.Controller.EmployeeController;
import Database.Dao.ExceptionDAO;

import javax.swing.*;

public class TesteConexao {

    public void inserir() throws ExceptionDAO {
        boolean sucesso;
        var controller = new EmployeeController();
//        try {
//            sucesso = controller.adicionaEmploees(null,
//                    "Wanderson", "77920207037", "63991111196", "Professor",
//                    LocalDate.now(), "Rua Presidente", "21", "", "Centro",
//                    "Areia", "Paraíba", "77960000"
//            );
//            if (sucesso) {
//                JOptionPane.showMessageDialog(null, "O Registro foi cadastrado/atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (ExceptionDAO e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }


    public void buscaPorId() throws ExceptionDAO {
        var controller = new EmployeeController();
        try {
            System.out.println(controller.buscaPorId(1L));
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscaPorValor() throws ExceptionDAO {
        var controller = new EmployeeController();
        try {
            System.out.println(controller.buscaPorValor(""));
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() throws ExceptionDAO {
        var controller = new EmployeeController();
        try {
            System.out.println(controller.deleteEmployees(11L));
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws ExceptionDAO {
        TesteConexao t = new TesteConexao();
        t.inserir();
//        var sqlDb = "USE sgde;";
//        var sqlTable = "CREATE TABLE employees (\n" +
//                "id serial primary key not null,\n" +
//                "nome varchar(20) not null,\n" +
//                "cpf varchar(11) not null unique,\n" +
//                "telefone varchar(11),\n" +
//                "cargo varchar(80),\n" +
//                "dta_nasc date,\n" +
//                "logradouro varchar(100) not null,\n" +
//                "numero varchar(10),\n" +
//                "complemento varchar(100),\n" +
//                "bairro varchar(80),\n" +
//                "cidade varchar(100),\n" +
//                "estado varchar(100),\n" +
//                "cep varchar(8)\n" +
//                ");\n" +
//                "\n" +
//                "CREATE TABLE banco(\n" +
//                " id serial primary key not null,\n" +
//                " nome varchar(80) not null,\n" +
//                " agencia int,\n" +
//                " conta int,\n" +
//                " salario numeric(10, 2),\n" +
//                " employee_id int unique not null references \"employees\" (id) on delete cascade\n" +
//                ");";
//        try(var conexao = DB.getConexao(); var stmt = conexao.createStatement()){
////            stmt.execute(sqlDb);
//            stmt.execute(sqlTable);
//        }catch (SQLException e){
//            System.err.println(e.getMessage());
//        }
    }
}
