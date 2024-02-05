package modulo.servidores.view;

import modulo.servidores.controller.EmployeeController;
import modulo.servidores.dao.SQLExceptionDAO;

import java.time.LocalDate;

public class EmployeeView {

    public static void main(String[] args) throws SQLExceptionDAO {

        EmployeeController controller = new EmployeeController();
        controller.adicionaEmploees( null,
                "Elias", "79798365453", "63991111196", "Professor",
                LocalDate.now(), "Rua Presidente", "21", "", "Centro",
                "Augustinópolis", "Tocantins", "77960000"
                );

    }
}
