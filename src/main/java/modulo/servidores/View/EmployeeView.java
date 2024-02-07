package modulo.servidores.View;

import FormModel.FormPadrao;
import FormModel.Fragmentos.PanelFormEmployee;
import modulo.servidores.Controller.EmployeeController;
import modulo.servidores.Controller.EstadoController;
import modulo.servidores.Controller.MunicipioController;
import modulo.servidores.DAOImpl.EstadoDAO;
import modulo.servidores.DAOImpl.MunicipioDAO;
import modulo.servidores.Dao.ExceptionDAO;
import modulo.servidores.Entity.Employees;
import modulo.servidores.Entity.Estados;
import modulo.servidores.Entity.Municipios;
import modulo.servidores.View.config.EmployeeCellRenderer;
import modulo.servidores.View.config.EmployeeTableModel;
import util.ComboBoxList;
import util.Util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class EmployeeView extends FormPadrao {
    private EmployeeController controller;
    private EstadoController estadoController;
    private MunicipioController municipioController;
    private PanelFormEmployee panelFormEmployee;
    private final EstadoDAO estadoDAO = new EstadoDAO();
    private final MunicipioDAO municipioDAO = new MunicipioDAO();
    private Long idRow;

    public EmployeeView(JFrame parent, boolean modal, String titulo) throws ExceptionDAO {
        super(parent, modal, titulo);
        setUndecorated(true);

        this.municipioController = new MunicipioController();

        this.panelFormEmployee = new PanelFormEmployee();
        this.formulario.add(this.panelFormEmployee);

        habilitarCampos(false);
        habilitarBotoes(true);
        preencherTabela("");
        preencherComboEstado();
        preencherComboCidade();

        try {
            var formatCpf = new MaskFormatter("###.###.###-##");
            formatCpf.install(panelFormEmployee.txtCpf);
            var formatData = new MaskFormatter("##/##/####");
            formatData.install(panelFormEmployee.txtDtaNasc);
            var formatTelefone = new MaskFormatter("(##)#####-####");
            formatTelefone.install(panelFormEmployee.txtTelefone);
            var formatMoney = new MaskFormatter("##.###,##");
            formatMoney.install(panelFormEmployee.txtSalario);
            var formatCep = new MaskFormatter("##.###-###");
            formatCep.install(panelFormEmployee.txtCep);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        panelTable.txtLocalizar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    preencherTabela(panelTable.txtLocalizar.getText());
                } catch (ExceptionDAO ex) {
                    ex.printStackTrace();
                }
            }
        });
        panelTable.tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    preencherFormulario();
                    habilitarCampos(true);
                    habilitarBotoes(false);
                    abas.setSelectedIndex(0);
                } catch (ExceptionDAO ex) {
                    ex.printStackTrace();
                }
            }
        });
        panelButton.btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    adicionarAction();
                } catch (ExceptionDAO e) {
                    throw new RuntimeException(e);
                }
            }
        });
        panelButton.btnCancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    cancelAction();
                } catch (ExceptionDAO e) {
                    throw new RuntimeException(e);
                }
            }
        });
        panelButton.btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                salvarNoBD();
            }
        });
        panelButton.btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteDoBD();
            }
        });


        panelFormEmployee.cbxEstado.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    preencherComboCidade();
                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void adicionarAction() throws ExceptionDAO {
        limparCampos();
        habilitarBotoes(false);
        habilitarCampos(true);
        preencherComboEstado();
        preencherComboCidade();
        if (this.idRow != null) idRow = null;
        if (abas.getSelectedIndex() != 0) abas.setSelectedIndex(0);
    }

    private void cancelAction() throws ExceptionDAO {
        limparCampos();
        habilitarBotoes(true);
        habilitarCampos(false);
        preencherComboEstado();
        preencherComboCidade();
        if (this.idRow != null) idRow = null;
        if (abas.getSelectedIndex() != 1) abas.setSelectedIndex(1);
    }


    @Override
    public void limparCampos() {
        panelFormEmployee.txtNome.setText(null);
        panelFormEmployee.txtCpf.setText(null);
        panelFormEmployee.txtDtaNasc.setText(null);
        panelFormEmployee.txtCargo.setText(null);
        panelFormEmployee.txtBanco.setText(null);
        panelFormEmployee.txtAgencia.setText(null);
        panelFormEmployee.txtConta.setText(null);
        panelFormEmployee.txtSalario.setText(null);
        panelFormEmployee.txtLogradouro.setText(null);
        panelFormEmployee.txtNumero.setText(null);
        panelFormEmployee.txtTelefone.setText(null);
        panelFormEmployee.txtComplemento.setText(null);
        panelFormEmployee.txtBairro.setText(null);
        panelFormEmployee.txtCep.setText(null);
        panelFormEmployee.cbxCidade.setSelectedItem("TO");
    }

    @Override
    public void habilitarCampos(boolean valor) {
        panelFormEmployee.txtNome.setEnabled(valor);
        panelFormEmployee.txtCpf.setEnabled(valor);
        panelFormEmployee.txtDtaNasc.setEnabled(valor);
        panelFormEmployee.txtCargo.setEnabled(valor);
        panelFormEmployee.txtBanco.setEnabled(valor);
        panelFormEmployee.txtAgencia.setEnabled(valor);
        panelFormEmployee.txtConta.setEnabled(valor);
        panelFormEmployee.txtSalario.setEnabled(valor);
        panelFormEmployee.txtLogradouro.setEnabled(valor);
        panelFormEmployee.txtNumero.setEnabled(valor);
        panelFormEmployee.txtTelefone.setEnabled(valor);
        panelFormEmployee.txtComplemento.setEnabled(valor);
        panelFormEmployee.txtBairro.setEnabled(valor);
        panelFormEmployee.txtCep.setEnabled(valor);
        panelFormEmployee.cbxCidade.setEnabled(valor);
        panelFormEmployee.cbxEstado.setEnabled(valor);
    }

    @Override
    public void habilitarBotoes(boolean valor) {
        panelButton.btnNovo.setEnabled(valor);
        panelButton.btnSalva.setEnabled(!valor);
        panelButton.btnCancela.setEnabled(!valor);
    }

    @Override
    public void preencherFormulario() throws ExceptionDAO {
        int row = panelTable.tabela.getSelectedRow();
        if (row != -1) {
            Long idEmployee = Long.valueOf(panelTable.tabela.getValueAt(row, 0).toString());
            var employee = new Employees();
            employee = controller.buscaPorId(idEmployee);
            this.idRow = employee.getId();
            panelFormEmployee.txtNome.setText(employee.getNome());
            panelFormEmployee.txtCpf.setText(employee.getCpf());
            panelFormEmployee.txtDtaNasc.setText(new Util().formatDate(employee.getDta_nasc()));
            panelFormEmployee.txtCargo.setText(employee.getCargo());
            panelFormEmployee.txtBanco.setText(null);
            panelFormEmployee.txtAgencia.setText(null);
            panelFormEmployee.txtConta.setText(null);
            panelFormEmployee.txtSalario.setText(null);
            panelFormEmployee.txtLogradouro.setText(employee.getLogradouro());
            panelFormEmployee.txtNumero.setText(employee.getNumero());
            panelFormEmployee.txtTelefone.setText(employee.getTelefone());
            panelFormEmployee.txtComplemento.setText(employee.getComplemento());
            panelFormEmployee.txtBairro.setText(employee.getBairro());
            panelFormEmployee.txtCep.setText(employee.getCep());
            panelFormEmployee.cbxEstado.getModel().setSelectedItem(employee.getEstado());
            panelFormEmployee.cbxCidade.getModel().setSelectedItem(employee.getCidade());

        }
    }

    private void preencherComboEstado() throws ExceptionDAO {
        estadoDAO.comboBoxEstado();
        panelFormEmployee.cbxEstado.removeAllItems();
        for (ComboBoxList c : estadoDAO.getList()) {
            panelFormEmployee.cbxEstado.addItem(c);
        }
    }

    private void preencherComboCidade() throws ExceptionDAO {
        String uf = String.valueOf(panelFormEmployee.cbxEstado.getModel().getSelectedItem());
        estadoController = new EstadoController();
        Estados estado = estadoController.findByName(uf);
        if (estado.getId() != null){
         List<Municipios> municipiosList = this.municipioController.getByCodigoUf(estado.getId());
            panelFormEmployee.cbxCidade.removeAllItems();
            for (Municipios m : municipiosList){
                panelFormEmployee.cbxCidade.addItem(m.getNome());
            }
        }
    }

    @Override
    public void preencherTabela(String valor) throws ExceptionDAO {
        List<Employees> employeesList;
        controller = new EmployeeController();
        employeesList = controller.buscaPorValor(valor);
        EmployeeTableModel modelo = new EmployeeTableModel(employeesList);
        panelTable.tabela.setModel(modelo);
        panelTable.tabela.setDefaultRenderer(Object.class, new EmployeeCellRenderer());
        panelTable.tabela.setRowSorter(new TableRowSorter<>(panelTable.tabela.getModel()));
    }

    @Override
    public void salvarNoBD() {

    }

    @Override
    public void deleteDoBD() {

    }


    //    public void inserir() throws ExceptionDAO {
//        boolean sucesso;
//        var controller = new EmployeeController();
//        try{
//            sucesso = controller.adicionaEmploees(null,
//                    "José Elias Gomes de Lima", "79798365453", "63991111196", "Professor",
//                    LocalDate.now(), "Rua Presidente", "21", "", "Centro",
//                    "Areia", "Paraíba", "77960000"
//            );
//            if (sucesso){
//                JOptionPane.showMessageDialog(null, "O Registro foi cadastrado/atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//            }else {
//                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }catch (ExceptionDAO e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//    public void buscaPorId() throws ExceptionDAO{
//        var controller= new EmployeeController();
//        try{
//            System.out.println(controller.buscaPorId(1));
//        }catch (ExceptionDAO e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public javax.swing.JDialog getInstance() {
        return this;
    }

    public void start() {
        setVisible(true);
    }
}
