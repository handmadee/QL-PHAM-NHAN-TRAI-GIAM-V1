package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private final int radius = 25; 

    public InfoPanel(String title, String value, Color bgColor) {
        setLayout(new GridBagLayout());
        setOpaque(false); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("Arial", Font.BOLD, 18));
        lblValue.setForeground(Color.WHITE);
        add(lblTitle, gbc);
        gbc.gridy = 1;
        add(lblValue, gbc);

        setPreferredSize(new Dimension(300, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 50)); 
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, radius, radius);
        g2.setColor(new Color(97, 173, 120));
        g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, radius, radius);

        g2.dispose(); 
    }
}
