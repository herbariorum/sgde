package modulo.servidores.view;

import modulo.servidores.controller.EmployeeController;
import modulo.servidores.dao.ExceptionDAO;

import javax.swing.*;
import java.time.LocalDate;

public class EmployeeView extends javax.swing.JDialog{
    private EmployeeController controller;

    public EmployeeView(){

    }

    public void inserir() throws ExceptionDAO {
        boolean sucesso;
        var controller = new EmployeeController();
        try{
            sucesso = controller.adicionaEmploees(null,
                    "José Elias Gomes de Lima", "79798365453", "63991111196", "Professor",
                    LocalDate.now(), "Rua Presidente", "21", "", "Centro",
                    "Areia", "Paraíba", "77960000"
            );
            if (sucesso){
                JOptionPane.showMessageDialog(null, "O Registro foi cadastrado/atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (ExceptionDAO e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void buscaPorId() throws ExceptionDAO{
        var controller= new EmployeeController();
        try{
            System.out.println(controller.buscaPorId(1));
        }catch (ExceptionDAO e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) throws ExceptionDAO {
        EmployeeView e = new EmployeeView();
//        e.inserir();
        e.buscaPorId();
    }
}
