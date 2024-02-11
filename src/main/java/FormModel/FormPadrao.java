package FormModel;

import FormModel.Fragmentos.PanelButton;
import FormModel.Fragmentos.PanelFormEmployee;
import FormModel.Fragmentos.PanelTable;
import FormModel.Fragmentos.PanelTitle;
import Database.Dao.ExceptionDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

abstract public class FormPadrao extends javax.swing.JDialog{
    abstract public void limparCampos();
    abstract public void habilitarCampos(boolean valor);
    abstract public void habilitarBotoes(boolean valor);
    abstract public void preencherFormulario() throws ExceptionDAO;
    abstract public void preencherTabela(String valor) throws ExceptionDAO;
    abstract public void salvarNoBD();
    abstract public void deleteDoBD() throws ExceptionDAO;

    public PanelTitle panelTitle;
    public PanelFormEmployee panelForm;
    public PanelButton panelButton;
    public PanelTable panelTable;
    private String titulo;
    public JPanel formulario;
    public JTabbedPane abas ;

    public FormPadrao(JFrame parent, boolean modal, String titulo){
        super(parent, modal);
        Container container = getContentPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.titulo = titulo;


        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        this.panelTable = new PanelTable();

        panelTitle = new PanelTitle();
        panelTitle.lblTitle.setText(this.titulo);
        panelTitle.btnClose.addActionListener((ActionEvent evt)->{dispose();});
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        container.add(panelTitle, gbc);

        panelButton = new PanelButton();
        gbc.weighty = 0.01;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        container.add(panelButton, gbc);

        gbc.weighty = 0.98;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(Abas(), gbc);

        setSize(new Dimension(800, 650));
        setLocationRelativeTo(null);
    }

    private JTabbedPane Abas(){
        this.abas = new JTabbedPane(JTabbedPane.TOP);
        this.abas.setBorder(BorderFactory.createEmptyBorder(5, 10, 15 , 10));
        this.formulario = new JPanel();
        this.abas.addTab("Formulário", this.formulario);
        this.abas.addTab("Listagem" ,this.panelTable);
        this.abas.setSelectedIndex(1);

        return abas;
    }
    private javax.swing.JDialog getInstance(){ return this; };
    private void start(){ setVisible(true);};
}
