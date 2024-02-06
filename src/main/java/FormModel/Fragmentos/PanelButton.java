package FormModel.Fragmentos;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PanelButton extends javax.swing.JPanel{
    public javax.swing.JButton btnNovo, btnRemove, btnSalva, btnCancela;

    public PanelButton(){
        btnNovo = new JButton("Novo");
        btnNovo.setIcon(new FlatSVGIcon(getClass().getResource("/images/news_FILL0_wght400_GRAD0_opsz24.svg")));

        btnSalva = new JButton("Salva");
        btnSalva.setIcon(new FlatSVGIcon(getClass().getResource("/images/save_FILL0_wght400_GRAD0_opsz24.svg")));

        btnCancela = new JButton("Cancela");
        btnCancela.setIcon(new FlatSVGIcon(getClass().getResource("/images/cancel_FILL0_wght400_GRAD0_opsz24.svg")));

        btnRemove = new JButton("Deleta");
        btnRemove.setIcon(new FlatSVGIcon(getClass().getResource("/images/delete_FILL0_wght400_GRAD0_opsz24.svg")));

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 100, 5, 5));

        add(btnNovo);
        add(btnSalva);
        add(btnCancela);
        add(btnRemove);

    }

}
