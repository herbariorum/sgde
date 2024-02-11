package FormModel.Fragmentos;

import com.formdev.flatlaf.FlatClientProperties;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import modulo.servidores.Entity.Estados;
import util.Util;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PanelFormEmployee extends javax.swing.JPanel{
    public JTextField txtNome, txtLogradouro, txtNumero, txtComplemento, txtBairro, txtAgencia, txtConta;
    public JFormattedTextField txtCpf, txtTelefone, txtDtaNasc, txtCep, txtSalario;
    public JComboBox txtBanco, txtCargo;
    public JComboBox cbxCidade;
    public JComboBox<Object> cbxEstado;
    public PanelFormEmployee(){
        FormLayout layout = new FormLayout(
                "150px, 4dlu,100px, 4dlu,100px, 4dlu,100px, 4dlu, 120px, 4dlu, 130px",
                "2dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref,"+
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref,"+
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref" //30
        );
        setLayout(layout);


        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();

        builder.border(Borders.DLU4);
        builder.opaque(true);

        builder.addSeparator("Dados do Servidor", cc.xyw(1, 2, 11));

        JLabel labelNome = new JLabel("Nome");
        builder.add(labelNome, cc.xyw(1, 4, 11));
        txtNome = new JTextField();
        txtNome.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (txtNome.getText().strip().length() <= 5){
                    txtNome.putClientProperty("JComponent.outline", "warning");
                    txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
                } else {
                    txtNome.putClientProperty("JComponent.outline", null);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (txtNome.getText().strip().isEmpty() || txtNome.getText().strip().length() <= 5){
                    txtNome.putClientProperty("JComponent.outline", "warning");
                    txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
                    txtNome.requestFocus();
                } else {
                    txtNome.putClientProperty("JComponent.outline", null);
                }
            }
        });
        builder.add(txtNome, cc.xyw(1, 6, 11));

        JLabel labelCpf = new JLabel("CPF");
        builder.add(labelCpf, cc.xy(1, 8));
        txtCpf = new JFormattedTextField(new Util().createFormatter("###.###.###-##"));
        builder.add(txtCpf, cc.xy(1, 10));

        JLabel labelNascimento = new JLabel("Data Nasc.");
        builder.add(labelNascimento, cc.xy(3, 8));
        txtDtaNasc = new JFormattedTextField(new Util().createFormatter("##/##/####"));
        builder.add(txtDtaNasc, cc.xy(3, 10));

        JLabel lblCargo = new JLabel("Cargo Atual");
        builder.add(lblCargo, cc.xyw(5, 8, 5));
        txtCargo    = new JComboBox(cargosList);
        txtCargo.setSelectedIndex(0);
        builder.add(txtCargo, cc.xyw(5, 10, 7));

        builder.addSeparator("Informações Bancárias", cc.xyw(1, 12, 11));

        JLabel labelBanco = new JLabel("Banco");
        builder.add(labelBanco, cc.xy(1, 14));
        txtBanco = new JComboBox();
        builder.add(txtBanco, cc.xyw(1, 16, 5));

        JLabel labelAgencia = new JLabel("Agência");
        builder.add(labelAgencia, cc.xy(7, 14));
        txtAgencia = new JTextField();
        builder.add(txtAgencia, cc.xy(7, 16));
        txtAgencia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero){
                    e.consume();
                }
            }
        });

        JLabel lableConta = new JLabel("Conta");
        builder.add(lableConta, cc.xy(9, 14));
        txtConta = new JTextField();
        builder.add(txtConta, cc.xy(9, 16));
        txtConta.addKeyListener(new KeyAdapter() {
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
        JLabel labelSalario = new JLabel("Salário (R$)");
        builder.add(labelSalario, cc.xy(11, 14));
        txtSalario = new JFormattedTextField(format);
        builder.add(txtSalario, cc.xy(11, 16));

        builder.addSeparator("Endereço do Servidor", cc.xyw(1, 18, 9));

        JLabel labelLogradouro = new JLabel("Logradouro (Ex. Rua, Avenida etc)");
        builder.add(labelLogradouro, cc.xyw(1, 20, 7));
        txtLogradouro = new JTextField();
        builder.add(txtLogradouro, cc.xyw(1, 22, 7));

        JLabel labelNumero = new JLabel("Número");
        builder.add(labelNumero, cc.xy(9, 20));
        txtNumero = new JTextField();
        builder.add(txtNumero, cc.xy(9, 22));

        JLabel labelTelefone = new JLabel("Telefone");
        builder.add(labelTelefone, cc.xy(11, 20));
        txtTelefone = new JFormattedTextField(new Util().createFormatter("(##)#####-####"));
        builder.add(txtTelefone, cc.xy(11, 22));

        JLabel labelComplemento = new JLabel("Complemento");
        builder.add(labelComplemento, cc.xyw(1, 24, 5));
        txtComplemento = new JTextField();
        builder.add(txtComplemento, cc.xyw(1, 26, 5));

        JLabel labelBairro = new JLabel("Bairro");
        builder.add(labelBairro, cc.xyw(7, 24, 5));
        txtBairro = new JTextField();
        builder.add(txtBairro, cc.xyw(7, 26, 5));

        JLabel labelUf = new JLabel("Estado");
        builder.add(labelUf, cc.xy(1, 28));
        cbxEstado = new JComboBox<>();
        builder.add(cbxEstado, cc.xy(1, 30));

        JLabel labelCidade = new JLabel("Cidade");
        builder.add(labelCidade, cc.xyw(3, 28, 7));
        cbxCidade = new JComboBox();
        builder.add(cbxCidade, cc.xyw(3, 30, 7));

        JLabel labelCep = new JLabel("CEP");
        builder.add(labelCep, cc.xy(11, 28));
        txtCep = new JFormattedTextField(new Util().createFormatter("##.###-###"));
        builder.add(txtCep, cc.xy(11, 30));
    }

    private String[] cargosList = {"<--Selecione uma opção-->", "Diretor", "Secretaria", "Coordenador", "Professor"};
}
