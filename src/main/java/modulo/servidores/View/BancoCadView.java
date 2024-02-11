package modulo.servidores.View;

import FormModel.Fragmentos.PanelButton;
import FormModel.Fragmentos.PanelTitle;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import modulo.servidores.Controller.BancoController;

import modulo.servidores.DAOImpl.EmployeeDAO;
import modulo.servidores.DAOImpl.ListBancoDAO;
import Database.Dao.ExceptionDAO;
import modulo.servidores.Entity.Banco;
import modulo.servidores.Entity.ListBancos;
import modulo.servidores.View.config.BancoCellRenderer;
import modulo.servidores.View.config.BancoTableModel;
import util.ComboBoxList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;


public class BancoCadView extends JDialog {

    public BancoCadView(JFrame parent, boolean modal, String titulo) throws ExceptionDAO {
        super(parent, titulo, modal);
        setUndecorated(true);
        setSize(new Dimension(750, 450));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        actionCancela();
    }

    public BancoCadView(JDialog parent, boolean modal, String titulo) throws ExceptionDAO {
        super(parent, titulo, modal);
        setUndecorated(true);
        setSize(new Dimension(750, 450));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        actionCancela();
    }

    private void initComponents() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        bancoController = new BancoController();
        /*
         *   BARRA DE TITULO
         */
        this.panelTitle = new PanelTitle();
        this.panelTitle.lblTitle.setText("Cadastro de Dados Bancários");
        panelTitle.btnClose.addActionListener((ActionEvent evt) -> {
            dispose();
        });
        panelTitle.btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(panelTitle, gbc);

        /*
         *   FORMULÁRIO
         */
        this.panelButton = new PanelButton();
        gbc.weighty = 0.01;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(panelButton, gbc);
//        implementar botões
        panelButton.btnCancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    actionCancela();
                } catch (ExceptionDAO e) {
                    throw new RuntimeException(e);
                }
            }
        });
        panelButton.btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actionNovo();
            }
        });

        panelButton.btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                salvaNoBD();
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
        /*
         *   FORMULÁRIO
         */
        JPanel panelForm = new JPanel();
//        panelForm.setBackground(Color.RED);
        FormLayout layout = new FormLayout(
                "20px, 150px, 4dlu, 100px, 4dlu, 100px, 4dlu , 100px, 20px",

                "20px, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 6dlu, pref"
        );
        panelForm.setLayout(layout);

        PanelBuilder builder = new PanelBuilder(layout, panelForm);
        CellConstraints cc = new CellConstraints();

        builder.border(Borders.DLU4);
        builder.opaque(true);

        JLabel label0 = new JLabel("Nome do Servidor");
        builder.add(label0, cc.xy(2, 3));
        this.nomeServidor = new JComboBox();
        builder.add(nomeServidor, cc.xyw(4, 3, 5));

        JLabel label1 = new JLabel("Nome do Banco");
        builder.add(label1, cc.xy(2, 5));
        this.nomeBanco = new JComboBox();
        builder.add(nomeBanco, cc.xyw(4, 5, 5));

        JLabel label2 = new JLabel("Agência Bancária");
        builder.add(label2, cc.xy(2, 7));
        agencia = new JTextField();
        builder.add(agencia, cc.xy(4, 7));
        agencia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero){
                    e.consume();
                }
            }
        });

        JLabel label3 = new JLabel("Conta Corrente");
        builder.add(label3, cc.xy(2, 9));
        conta = new JTextField();
        builder.add(conta, cc.xy(4, 9));
        conta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero){
                    e.consume();
                }
            }
        });

        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        JLabel label4 = new JLabel("Salário");
        builder.add(label4, cc.xy(2, 11));
        salario = new JFormattedTextField(format);
        builder.add(salario, cc.xy(4, 11));

        /*
         *   TABELA
         */
        panelTable = new JPanel(new BorderLayout());
        panelTable.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtLocalizar = new JTextField();
        txtLocalizar.setHorizontalAlignment(JTextField.LEFT);
        txtLocalizar.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon(getClass().getResource("/images/search_FILL0_wght400_GRAD0_opsz24.svg")));
        txtLocalizar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Localizar");
        txtLocalizar.setPreferredSize(new Dimension(txtLocalizar.getWidth(), 24));
        txtLocalizar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    preencheTabela(txtLocalizar.getText());
                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        tabelaBanco = new JTable();
        tabelaBanco.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    preencherFormulario();
                    habilitaCampos(true);
                    habilitaBotoes(false);
                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        tabelaBanco.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane = new JScrollPane();

        scrollPane.setViewportView(tabelaBanco);

        panelTable.add(txtLocalizar, BorderLayout.NORTH);
        panelTable.add(scrollPane, BorderLayout.CENTER);


        try {
            this.preencheTabela("");
            preencherComboEmployee();
            preencherComboListBanco();
        } catch (ExceptionDAO e) {
            throw new RuntimeException(e);
        }
        /*
         *   JTABBLEDPANE
         */
        this.abas = new JTabbedPane(JTabbedPane.TOP);
        this.abas.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        this.abas.addTab("Formulário", panelForm);
        this.abas.addTab("Listagem", panelTable);
        this.abas.setSelectedIndex(1);
        this.abas.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane pane = (JTabbedPane) changeEvent.getSource();
                if (pane.getSelectedIndex() == 1){
                    try {
                        actionCancela();
                    } catch (ExceptionDAO e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        gbc.weighty = 0.98;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        container.add(abas, gbc);
    }

    private void salvaNoBD() {
        boolean sucesso;
        String nomeDoBanco = nomeBanco.getModel().getSelectedItem().toString();
        String agenciaNumber = String.valueOf(agencia.getText());
        String contaNumber = conta.getText();
        Double salarioValor = Double.valueOf(salario.getText().replace(".","").replace(",","."));
        ComboBoxList employeeList = (ComboBoxList) this.nomeServidor.getSelectedItem();
        try {
            sucesso = bancoController.save(this.idRow, nomeDoBanco, agenciaNumber, contaNumber, salarioValor, Objects.requireNonNull(employeeList).getId());
            if (sucesso){
                JOptionPane.showMessageDialog(null, "O Registro foi cadastrado/atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                actionCancela();
            }else{
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (ExceptionDAO e){
            JOptionPane.showMessageDialog(this, "Ocorreu o seguinte erro ao salvar/atualizar \n"+e.getMessage());
        }
    }

    private void deleteDoBD() throws ExceptionDAO {
        boolean sucesso;
        if (this.idRow == null){
            JOptionPane.showMessageDialog(null, "Por favor, selecione um registro na tabela primeiro.");
        }else{
            if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                BancoController controller = new BancoController();
                sucesso = controller.deleteConta(this.idRow);
                if (sucesso){
                    JOptionPane.showMessageDialog(null, "O Registro foi removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    actionCancela();
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
               actionCancela();
            }
        }
    }
    private void actionNovo(){
        limpaCampos();
        habilitaBotoes(false);
        habilitaCampos(true);
        if (abas.getSelectedIndex() != 0) abas.setSelectedIndex(0);
    }
    private void actionCancela() throws ExceptionDAO {
        limpaCampos();
        habilitaBotoes(true);
        habilitaCampos(false);
        this.preencheTabela("");
        if (this.idRow != null) this.idRow = null;
        if (abas.getSelectedIndex() != 1) abas.setSelectedIndex(1);
    }
    private void habilitaCampos(boolean valor){
        nomeServidor.setEnabled(valor);
        nomeBanco.setEnabled(valor);
        agencia.setEnabled(valor);
        conta.setEnabled(valor);
        salario.setEnabled(valor);
    }

    private void habilitaBotoes(boolean valor){
        panelButton.btnNovo.setEnabled(valor);
        panelButton.btnCancela.setEnabled(!valor);
        panelButton.btnSalva.setEnabled(!valor);
    }
    private void limpaCampos(){
        nomeServidor.setSelectedIndex(0);
        nomeBanco.setSelectedIndex(0);
        agencia.setText(null);
        conta.setText(null);
        salario.setText(null);
    }

    private void preencherComboEmployee() throws ExceptionDAO {
        employeeDAO.comboBoxEmployee();
        nomeServidor.removeAllItems();
        for (ComboBoxList c: employeeDAO.getList()){
            nomeServidor.addItem(c);
        }
    }

    private  void preencherComboListBanco() throws  ExceptionDAO{
        List<ListBancos> lista;
        lista = listBancoDAO.getAll();
        nomeBanco.removeAllItems();
        for (ListBancos l: lista){
            nomeBanco.addItem(l.getBanco());
        }
    }
    private void preencherFormulario() throws ExceptionDAO {
        int row = tabelaBanco.getSelectedRow();
        if (row != -1){
            Long id = Long.valueOf(tabelaBanco.getValueAt(row, 0).toString());
            var banco = new Banco();
            try {
                banco = bancoController.buscarPorId(id);
                this.idRow = banco.getId();
                for (ComboBoxList a : employeeDAO.getList()){
                    a.setSelectedId(employeeDAO.getList(), String.valueOf(banco.getEmployee_id()), nomeServidor);
                }
                this.nomeBanco.getModel().setSelectedItem(banco.getNome());
                agencia.setText(String.valueOf(banco.getAgencia()));
                conta.setText(String.valueOf(banco.getConta()));
                salario.setText(String.valueOf(banco.getSalario()).replace(".",","));
                abas.setSelectedIndex(0);
            } catch (ExceptionDAO e){
                e.printStackTrace();
            }
        }
    }

    private void preencheTabela(String valor) throws ExceptionDAO {
        List<Banco> lista;
        bancoController = new BancoController();
        lista = bancoController.getByValue(valor);
        modelo = new BancoTableModel(lista);
        tabelaBanco.setModel(modelo);
        tabelaBanco.setDefaultRenderer(Object.class, new BancoCellRenderer());
        tabelaBanco.setRowSorter(new TableRowSorter<>(tabelaBanco.getModel()));
    }

    public void start() {
        setVisible(true);
    }

    public PanelTitle panelTitle;
    private PanelButton panelButton;
    private JPanel panelTable;
    public JTable tabelaBanco;
    public JTextField txtLocalizar;
    private JScrollPane scrollPane;
    public JTabbedPane abas;
    private BancoTableModel modelo;
    private BancoController bancoController;
    private JTextField agencia, conta;
    private JFormattedTextField salario;
    private Long idRow;
    private JComboBox nomeServidor, nomeBanco;
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final ListBancoDAO listBancoDAO = new ListBancoDAO();
}


