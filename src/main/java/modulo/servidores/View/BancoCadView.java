package modulo.servidores.View;

import FormModel.Fragmentos.PanelTitle;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BancoCadView extends JDialog {
    public PanelTitle panelTitle;
//    public BancoCadView(JFrame parent, boolean modal, String titulo){
//        super(parent, titulo, modal);
    public BancoCadView(){
        super();
        setUndecorated(true);
        setSize(new Dimension(500, 250));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents(){
        Container container = getContentPane();

        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 0.01;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.panelTitle = new PanelTitle();
        this.panelTitle.lblTitle.setText("Cadastro de Dados Bancários");
        panelTitle.btnClose.addActionListener((ActionEvent evt)->{dispose();});
        panelTitle.btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        container.add(panelTitle,gbc);

        JPanel panel = new JPanel();
//        panel.setSize(getWidth(), 250);
        panel.setBackground(Color.RED);
        FormLayout layout = new FormLayout(
                "20px, 150px, 4dlu, 100px, 4dlu, 100px , 20px",
                "20px, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 6dlu, pref"
        );
        panel.setLayout(layout);
        PanelBuilder builder = new PanelBuilder(layout, panel);
        CellConstraints cc = new CellConstraints();

        builder.border(Borders.DLU4);
        builder.opaque(true);

        JLabel label1 = new JLabel("Nome do Banco");
        builder.add(label1, cc.xyw(2, 3, 5));
        JTextField banco = new JTextField();
        builder.add(banco, cc.xyw(4, 3, 3));

        JLabel label2 = new JLabel("Agência Bancária");
        builder.add(label2, cc.xy(2, 5));
        JTextField agencia = new JTextField();
        builder.add(agencia, cc.xy(4, 5));

        JLabel label3 = new JLabel("Conta Corrente");
        builder.add(label3, cc.xy(2, 7));
        JTextField conta = new JTextField();
        builder.add(conta, cc.xy(4, 7));

        JLabel label4 = new JLabel("Salário");
        builder.add(label4, cc.xy(2, 9));
        JTextField salario = new JTextField();
        builder.add(salario, cc.xy(4, 9));

        JButton btnAdd = new JButton("Salva");
        builder.add(btnAdd, cc.xy(4, 11));
        JButton btnCancel = new JButton("Cancela");
        builder.add(btnCancel, cc.xy(6, 11));

        gbc.weighty = 0.99;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        container.add(panel,gbc);
    }
// Apenas para teste
    public static void main(String[] args) {
        BancoCadView v = new BancoCadView();
        v.setVisible(true);
    }
}
