package modulo.servidores.View;

import FormModel.FormPadrao;
import FormModel.Fragmentos.PanelFormEmployee;
import com.formdev.flatlaf.FlatClientProperties;
import modulo.servidores.Controller.EmployeeController;
import modulo.servidores.Controller.EstadoController;
import modulo.servidores.Controller.MunicipioController;
import modulo.servidores.DAOImpl.EstadoDAO;
import modulo.servidores.DAOImpl.ListBancoDAO;
import modulo.servidores.DAOImpl.MunicipioDAO;
import Database.Dao.ExceptionDAO;
import modulo.servidores.Entity.*;
import modulo.servidores.View.config.EmployeeCellRenderer;
import modulo.servidores.View.config.EmployeeTableModel;
import util.CPF;
import util.ComboBoxList;
import util.DateValidatorUsingIDateFormat;
import util.Util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView extends FormPadrao {

    public EmployeeView(JFrame parent, boolean modal, String titulo) throws ExceptionDAO {
        super(parent, modal, titulo);
        setUndecorated(true);

        this.municipioController = new MunicipioController();
        this.estadoController = new EstadoController();
        this.controller = new EmployeeController();
        this.panelFormEmployee = new PanelFormEmployee();

        this.formulario.add(this.panelFormEmployee);

        habilitarCampos(false);
        habilitarBotoes(true);
        preencherTabela("");
        preencherComboEstado();
        preencherComboCidade();
        preencherComboListBanco();
//        try {
//            var formatCpf = new MaskFormatter("###.###.###-##");
//            formatCpf.install(panelFormEmployee.txtCpf);
////            var formatData = new MaskFormatter("##/##/####");
////            formatData.install(panelFormEmployee.txtDtaNasc);
//            var formatTelefone = new MaskFormatter("(##)#####-####");
//            formatTelefone.install(panelFormEmployee.txtTelefone);
//            var formatCep = new MaskFormatter("##.###-###");
//            formatCep.install(panelFormEmployee.txtCep);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


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
                try {
                    deleteDoBD();
                } catch (ExceptionDAO e) {
                    throw new RuntimeException(e);
                }
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
        preencherComboListBanco();
        if (this.idRow != null) idRow = null;
        if (this.idBanco != null) idBanco = null;
        if (abas.getSelectedIndex() != 0) abas.setSelectedIndex(0);
    }

    private void cancelAction() throws ExceptionDAO {
        limparCampos();
        habilitarBotoes(true);
        habilitarCampos(false);
        preencherComboEstado();
        preencherComboCidade();
        preencherComboListBanco();
        if (this.idRow != null) idRow = null;
        if (this.idBanco != null) idBanco = null;
        if (abas.getSelectedIndex() != 1) abas.setSelectedIndex(1);
    }

    @Override
    public void limparCampos() {
        panelFormEmployee.txtNome.setText(null);
        panelFormEmployee.txtCpf.setText(null);
        panelFormEmployee.txtDtaNasc.setText(null);
        panelFormEmployee.txtCargo.setSelectedIndex(0);
        panelFormEmployee.txtBanco.setSelectedIndex(1);
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
            try {
                employee = controller.buscaPorId(idEmployee);
                this.idRow = employee.getId();
                panelFormEmployee.txtNome.setText(employee.getNome());
                panelFormEmployee.txtCpf.setText(employee.getCpf());
                panelFormEmployee.txtDtaNasc.setText(new Util().formatDate(employee.getDta_nasc()));
                panelFormEmployee.txtCargo.getModel().setSelectedItem(employee.getCargo());
                // preenche o campos referentes ao banco (nome, agencia, conta e salario)
                preencherSalario(employee.getConta_bancaria());
                panelFormEmployee.txtLogradouro.setText(employee.getLogradouro());
                panelFormEmployee.txtNumero.setText(employee.getNumero());
                panelFormEmployee.txtTelefone.setText(employee.getTelefone());
                panelFormEmployee.txtComplemento.setText(employee.getComplemento());
                panelFormEmployee.txtBairro.setText(employee.getBairro());
                panelFormEmployee.txtCep.setText(employee.getCep());
                panelFormEmployee.cbxEstado.getModel().setSelectedItem(employee.getEstado());
                panelFormEmployee.cbxCidade.getModel().setSelectedItem(employee.getCidade());
            }catch (ExceptionDAO e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void preencherSalario(ArrayList<Banco> contaBancaria) {
        ArrayList<Banco> bancos = contaBancaria;
        for(Banco banco : bancos){
            Font font = new Font("Robobo Black", Font.BOLD, 12);
            this.idBanco = banco.getId();
            panelFormEmployee.txtBanco.getModel().setSelectedItem(banco.getNome());
            panelFormEmployee.txtBanco.setForeground(Color.BLUE);
            panelFormEmployee.txtBanco.setFont(font);
            panelFormEmployee.txtAgencia.setText(String.valueOf(banco.getAgencia()));
            panelFormEmployee.txtAgencia.setForeground(Color.BLUE);
            panelFormEmployee.txtAgencia.setFont(font);
            panelFormEmployee.txtConta.setText(String.valueOf(banco.getConta()));
            panelFormEmployee.txtConta.setForeground(Color.BLUE);
            panelFormEmployee.txtConta.setFont(font);
            panelFormEmployee.txtSalario.setText(String.valueOf(banco.getSalario()));
            panelFormEmployee.txtSalario.setForeground(Color.BLUE);
            panelFormEmployee.txtSalario.setFont(font);
        }
    }

    private  void preencherComboListBanco() throws  ExceptionDAO{
        List<ListBancos> lista;
        lista = listBancoDAO.getAll();
        panelFormEmployee.txtBanco.removeAllItems();
        for (ListBancos l: lista){
            panelFormEmployee.txtBanco.addItem(l.getBanco());
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
        if (estado.getId() != null) {
            List<Municipios> municipiosList = this.municipioController.getByCodigoUf(estado.getId());
            panelFormEmployee.cbxCidade.removeAllItems();
            for (Municipios m : municipiosList) {
                panelFormEmployee.cbxCidade.addItem(m.getNome());
            }
        }
    }

    @Override
    public void preencherTabela(String valor) throws ExceptionDAO {
        List<Employees> employeesList;
        var controller = new EmployeeController();
        employeesList = controller.buscaPorValor(valor);
        EmployeeTableModel modelo = new EmployeeTableModel(employeesList);
        panelTable.tabela.setModel(modelo);
        panelTable.tabela.setDefaultRenderer(Object.class, new EmployeeCellRenderer());
        panelTable.tabela.setRowSorter(new TableRowSorter<>(panelTable.tabela.getModel()));
    }

    @Override
    public void salvarNoBD() {
        boolean sucesso;
        // VALIDAÇÕES
        // pega os valores
        var nome = panelFormEmployee.txtNome.getText();
        var cpf = panelFormEmployee.txtCpf.getText().replace(".", "").replace("-", "");
        var celular = panelFormEmployee.txtTelefone.getText().replace("(","").replace(")","").replace("-","");
        var cargo = panelFormEmployee.txtCargo.getSelectedItem().toString();
        var nascimento = panelFormEmployee.txtDtaNasc.getText().toString();
        var logradouro = panelFormEmployee.txtLogradouro.getText();
        var banco = String.valueOf(panelFormEmployee.txtBanco.getSelectedItem());
        var agencia = panelFormEmployee.txtAgencia.getText();
        var conta = panelFormEmployee.txtConta.getText();
        var salario = panelFormEmployee.txtSalario.getText().replace(".","").replace(",",".");

        // valida
        if (nome.isEmpty() || nome.length() <= 5){
            panelFormEmployee.txtNome.putClientProperty("JComponent.outline","warning");
            panelFormEmployee.txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtNome.putClientProperty("JComponent.outline", null);
        }
        if (cpf.strip().isEmpty() || !new CPF(cpf).isCPF()){
            panelFormEmployee.txtCpf.putClientProperty("JComponent.outline","warning");
            panelFormEmployee.txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtCpf.putClientProperty("JComponent.outline", null);
        }
        var validator = new DateValidatorUsingIDateFormat("dd/MM/yyyy");
        if (nascimento.isEmpty() || !validator.isValid(nascimento)){
            panelFormEmployee.txtDtaNasc.putClientProperty("JComponent.outline", "warning");
            panelFormEmployee.txtDtaNasc.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtDtaNasc.putClientProperty("JComponent.outline", null);
        }
        if (panelFormEmployee.txtCargo.getSelectedIndex() == 0){
            panelFormEmployee.txtCargo.putClientProperty("JComponent.outline","warning");
            panelFormEmployee.txtCargo.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtCargo.putClientProperty("JComponent.outline", null);
        }
        if (agencia.isEmpty()){
            panelFormEmployee.txtAgencia.putClientProperty("JComponent.outline", "warning");
            panelFormEmployee.txtAgencia.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtAgencia.putClientProperty("JComponent.outline", null);
        }
        if (conta.isEmpty()){
            panelFormEmployee.txtConta.putClientProperty("JComponent.outline", "warning");
            panelFormEmployee.txtConta.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtConta.putClientProperty("JComponent.outline", null);
        }
        if (salario.isEmpty() ){
            panelFormEmployee.txtSalario.putClientProperty("JComponent.outline", "warning");
            panelFormEmployee.txtSalario.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtSalario.putClientProperty("JComponent.outline", null);
        }
        if (logradouro.isEmpty()){
            panelFormEmployee.txtLogradouro.putClientProperty("JComponent.outline", "warning");
            panelFormEmployee.txtLogradouro.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtLogradouro.putClientProperty("JComponent.outline", null);
        }
        if (celular.isEmpty()){
            panelFormEmployee.txtTelefone.putClientProperty("JComponent.outline","warning");
            panelFormEmployee.txtTelefone.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            return;
        }else{
            panelFormEmployee.txtTelefone.putClientProperty("JComponent.outline", null);
        }
        // ADICIONA AO BD
        try{
            sucesso = controller.adicionaEmploees(
                    this.idRow,
                    nome,
                    cpf,
                    celular,
                    cargo,
                    new Util().formatDateToUs(nascimento),
                    logradouro,
                    panelFormEmployee.txtNumero.getText(),
                    panelFormEmployee.txtComplemento.getText(),
                    panelFormEmployee.txtBairro.getText(),
                    String.valueOf(panelFormEmployee.cbxCidade.getSelectedItem()),
                    String.valueOf(panelFormEmployee.cbxEstado.getSelectedItem()),
                    panelFormEmployee.txtCep.getText().replace(".","").replace("-",""),
                    this.idBanco,
                    banco,
                    agencia,
                    conta,
                    Double.valueOf(salario),
                    this.idRow
            );
            if (sucesso){
                JOptionPane.showMessageDialog(null, "O Registro foi cadastrado/atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.limparCampos();
                this.habilitarCampos(false);
                this.habilitarBotoes(true);
                this.preencherTabela("");
                panelTable.tabela.clearSelection();
                if (this.idRow != null) this.idRow = null;
                if (abas.getSelectedIndex() != 1) abas.setSelectedIndex(1);
            }else{
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (ExceptionDAO e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteDoBD() throws ExceptionDAO {
        if (this.idRow == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um item na tabela.");
        } else {
            if (JOptionPane.showConfirmDialog(this, "Confirme a exclusão do item.", "Confirmação",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                boolean retorno = controller.deleteEmployees(this.idRow);
                if (retorno) {
                    JOptionPane.showMessageDialog(this, "Item deletado com sucesso.");
                    this.limparCampos();
                    this.habilitarCampos(false);
                    this.habilitarBotoes(true);
                    this.preencherTabela("");
                    panelTable.tabela.clearSelection();
                    if (this.idRow != null) this.idRow = null;
                    if (abas.getSelectedIndex() != 1) abas.setSelectedIndex(1);
                } else {
                    JOptionPane.showMessageDialog(this, "Ocorreu algum erro ao tentar excluir o item.");
                    if (abas.getSelectedIndex() != 1) abas.setSelectedIndex(1);
                }
            } else {
                limparCampos();
                habilitarBotoes(true);
                habilitarCampos(false);
                if (this.idRow != null) this.idRow = null;
                if (abas.getSelectedIndex() != 1) abas.setSelectedIndex(1);
            }
        }
    }

    public javax.swing.JDialog getInstance() {
        return this;
    }

    public void start() {
        setVisible(true);
    }

    private Long idBanco;
    private EmployeeController controller;
    private EstadoController estadoController;
    private MunicipioController municipioController;
    private PanelFormEmployee panelFormEmployee;
    private final EstadoDAO estadoDAO = new EstadoDAO();
    private final MunicipioDAO municipioDAO = new MunicipioDAO();
    private final ListBancoDAO listBancoDAO = new ListBancoDAO();
    private Long idRow;

}
