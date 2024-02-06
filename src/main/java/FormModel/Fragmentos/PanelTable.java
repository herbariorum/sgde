package FormModel.Fragmentos;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PanelTable extends javax.swing.JPanel{

    public JTable tabela;
    private JScrollPane scrollPane;
    public JTextField txtLocalizar;
    public PanelTable(){
        txtLocalizar = new JTextField();
        scrollPane = new JScrollPane();
        tabela = new JTable();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        txtLocalizar.setHorizontalAlignment(JTextField.LEFT);
        txtLocalizar.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon(getClass().getResource("/images/search_FILL0_wght400_GRAD0_opsz24.svg")));
        txtLocalizar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Localizar");
        txtLocalizar.setPreferredSize(new Dimension(txtLocalizar.getWidth(), 24));

        add(txtLocalizar);

        tabela.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 5, 5, 5));
        scrollPane.setViewportView(tabela);
        add(scrollPane);

    }
}
