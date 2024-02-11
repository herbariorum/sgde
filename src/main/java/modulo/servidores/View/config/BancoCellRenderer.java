package modulo.servidores.View.config;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class BancoCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelect, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelect, hasFocus, row, column);

        if (row % 2 == 0) {
            setBackground(new Color(255, 255, 255));
            setForeground(Color.BLACK);
        } else {
            setBackground(new Color(220, 220, 220));
            setForeground(Color.BLACK);
        }

        if (isSelect) {
            setBackground(new Color(3, 167, 154));
            setForeground(Color.WHITE);
        }

        if (comp instanceof JLabel){
            ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
        }

        TableColumn hide = table.getColumnModel().getColumn(0); // id
        hide.setMinWidth(0);
        hide.setMaxWidth(0);
        hide.setPreferredWidth(0);

        table.getColumnModel().getColumn(1).setMaxWidth(600);
        table.getColumnModel().getColumn(2).setMaxWidth(600); // Banco
        table.getColumnModel().getColumn(3).setMaxWidth(150); // Agencia
        table.getColumnModel().getColumn(4).setMaxWidth(150); // Conta

        TableColumn hide1 = table.getColumnModel().getColumn(5); // salario
        hide1.setMaxWidth(0);
        hide1.setMinWidth(0);
        hide1.setPreferredWidth(0);

        return this;

    }
}
