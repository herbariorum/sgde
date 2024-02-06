package modulo.servidores.View.config;


import FormModel.tables.ViewAbstractTableModel;
import modulo.servidores.Entity.Employees;
import util.Util;

import java.util.List;

public class EmployeeTableModel extends ViewAbstractTableModel<Employees> {

    public EmployeeTableModel(List<Employees> rows) {
        super(rows);
        columns = new String[]{
                "ID",
                "NOME",
                "CPF",
                "CARGO",
                "TELEFONE"
        };

    }

    @Override
    public Object getValueAt(int row, int col) {
        Employees std = this.rows.get(row);
        switch (col) {
            case 0:
                return std.getId();
            case 1:
                return std.getNome();
            case 2:
                return std.getCpf();
            case 3:
                return std.getCargo();
            case 4:
                return std.getTelefone();
            default:
                return null;
        }
    }
}
