package FormModel.Fragmentos;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

public class PanelFormEmployee extends javax.swing.JPanel{
    public JTextField txtNome, txtCargo, txtLogradouro, txtNumero, txtComplemento, txtBairro, txtBanco, txtAgencia, txtConta;
    public JFormattedTextField txtCpf, txtTelefone, txtDtaNasc, txtCep, txtSalario;
    public JComboBox cbxCidade, cbxEstado;

    public PanelFormEmployee(){
        FormLayout layout = new FormLayout(
                "150px, 4dlu,100px, 4dlu,100px, 4dlu,100px, 4dlu, 130px",
                "2dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref,"+
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref,"+
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref" //30
        );
        setLayout(layout);


        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();

        builder.border(Borders.DLU4);
        builder.opaque(true);

        builder.addSeparator("Dados do Servidor", cc.xyw(1, 2, 9));

        JLabel labelNome = new JLabel("Nome");
        builder.add(labelNome, cc.xyw(1, 4, 9));
        txtNome = new JTextField();
        builder.add(txtNome, cc.xyw(1, 6, 9));

        JLabel labelCpf = new JLabel("CPF");
        builder.add(labelCpf, cc.xy(1, 8));
        txtCpf = new JFormattedTextField();
        builder.add(txtCpf, cc.xy(1, 10));

        JLabel labelNascimento = new JLabel("Data Nasc.");
        builder.add(labelNascimento, cc.xy(3, 8));
        txtDtaNasc = new JFormattedTextField();
        builder.add(txtDtaNasc, cc.xy(3, 10));

        JLabel lblCargo = new JLabel("Cargo Atual");
        builder.add(lblCargo, cc.xyw(5, 8, 3));
        txtCargo    = new JTextField();
        builder.add(txtCargo, cc.xyw(5, 10, 5));

        builder.addSeparator("Informações Bancárias", cc.xyw(1, 12, 9));

        JLabel labelBanco = new JLabel("Banco");
        builder.add(labelBanco, cc.xy(1, 14));
        txtBanco = new JTextField();
        builder.add(txtBanco, cc.xyw(1, 16, 3));

        JLabel labelAgencia = new JLabel("Agência Bancária");
        builder.add(labelAgencia, cc.xy(5, 14));
        txtAgencia = new JTextField();
        builder.add(txtAgencia, cc.xy(5, 16));

        JLabel lableConta = new JLabel("Conta Corrente");
        builder.add(lableConta, cc.xy(7, 14));
        txtConta = new JTextField();
        builder.add(txtConta, cc.xy(7, 16));

        JLabel labelSalario = new JLabel("Salário (R$)");
        builder.add(labelSalario, cc.xy(9, 14));
        txtSalario = new JFormattedTextField();
        builder.add(txtSalario, cc.xy(9, 16));

        builder.addSeparator("Endereço do Servidor", cc.xyw(1, 18, 9));

        JLabel labelLogradouro = new JLabel("Logradouro (Ex. Rua, Avenida etc");
        builder.add(labelLogradouro, cc.xyw(1, 20, 5));
        txtLogradouro = new JTextField();
        builder.add(txtLogradouro, cc.xyw(1, 22, 5));

        JLabel labelNumero = new JLabel("Número");
        builder.add(labelNumero, cc.xy(7, 20));
        txtNumero = new JTextField();
        builder.add(txtNumero, cc.xy(7, 22));

        JLabel labelTelefone = new JLabel("Telefone");
        builder.add(labelTelefone, cc.xy(9, 20));
        txtTelefone = new JFormattedTextField();
        builder.add(txtTelefone, cc.xy(9, 22));

        JLabel labelComplemento = new JLabel("Complemento");
        builder.add(labelComplemento, cc.xyw(1, 24, 5));
        txtComplemento = new JTextField();
        builder.add(txtComplemento, cc.xyw(1, 26, 5));

        JLabel labelBairro = new JLabel("Bairro");
        builder.add(labelBairro, cc.xyw(7, 24, 3));
        txtBairro = new JTextField();
        builder.add(txtBairro, cc.xyw(7, 26, 3));

        JLabel labelUf = new JLabel("Estado");
        builder.add(labelUf, cc.xy(1, 28));
        cbxEstado = new JComboBox();
        builder.add(cbxEstado, cc.xy(1, 30));

        JLabel labelCidade = new JLabel("Cidade");
        builder.add(labelCidade, cc.xyw(3, 28, 5));
        cbxCidade = new JComboBox();
        builder.add(cbxCidade, cc.xyw(3, 30, 5));

        JLabel labelCep = new JLabel("CEP");
        builder.add(labelCep, cc.xy(9, 28));
        txtCep = new JFormattedTextField();
        builder.add(txtCep, cc.xy(9, 30));
    }

}
