package FormModel.Fragmentos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTitle extends JPanel {

    public javax.swing.JButton btnClose;
    public javax.swing.JLabel lblTitle;

    public PanelTitle(){
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.BorderLayout());
        setBackground(Color.BLACK);

        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 14));
        add(lblTitle, BorderLayout.CENTER);


        btnClose = new JButton();
        btnClose.setText("x");

        btnClose.setForeground(Color.GRAY);
        btnClose.setBorderPainted(false);
        btnClose.setFocusPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.putClientProperty("JButton.buttonType", "roundRect");
        add(btnClose, BorderLayout.LINE_END);

        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setContentAreaFilled(true);
                btnClose.setBackground(Color.RED);
                btnClose.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setContentAreaFilled(false);
                btnClose.setForeground(Color.GRAY);
            }
        });



    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Color color1 = new Color(87, 52, 71);
        Color color2 = Color.black;
        Graphics2D graphics2D = (Graphics2D) g;
        GradientPaint p = new GradientPaint(0, 0, color1, getWidth(), 0, color2);
        graphics2D.setPaint(p);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }


}
