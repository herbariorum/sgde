package modulo.servidores.View.config;

import FormModel.tables.ViewAbstractTableModel;
import modulo.servidores.Controller.EmployeeController;
import Database.Dao.ExceptionDAO;
import modulo.servidores.Entity.Banco;
import modulo.servidores.Entity.Employees;

import java.util.List;

public class BancoTableModel extends ViewAbstractTableModel<Banco> {

    public BancoTableModel(List<Banco> rows){
        super(rows);
        columns = new String[]{
                "ID",
                "SERVIDOR",
                "BANCO",
                "AGÊNCIA",
                "CONTA",
                "SALÁRIO"
        };

    }
    @Override
    public Object getValueAt(int row, int col) {
        Banco banco = this.rows.get(row);
        switch (col) {
            case 0:
                return banco.getId();
            case 1:
                // Busca todos os servidores que tenham uma conta cadastrada
            var controller = new EmployeeController();
            var employee = new Employees();
                try {
                   employee = controller.buscaPorId(banco.getEmployee_id());
                } catch (ExceptionDAO e) {
                    throw new RuntimeException(e);
                }
                return employee.getNome();
            case 2:
                return banco.getNome();
            case 3:
                return banco.getAgencia();
            case 4:
                return banco.getConta();
            case 5:
                return banco.getSalario();
            default:
                return null;
        }
    }
}
