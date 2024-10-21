package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomDropdown extends JPanel {

    private JComboBox<String> comboBox;

    public CustomDropdown(String[] items) {
        setLayout(new BorderLayout());
        setOpaque(false); 
        comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(200, 35));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setForeground(Color.BLACK);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        comboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                comboBox.setBorder(BorderFactory.createLineBorder(new Color(72, 181, 255), 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            }
        });

        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(new Color(200, 200, 200));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundedPanel.setPreferredSize(new Dimension(220, 45));

        // Thêm JComboBox vào panel bo góc
        roundedPanel.add(comboBox, BorderLayout.CENTER);
        add(roundedPanel, BorderLayout.CENTER);
    }

    public String getSelectedItem() {
        return (String) comboBox.getSelectedItem();
    }

    public void setSelectedItem(String item) {
        comboBox.setSelectedItem(item);
    }

    public void addItem(String item) {
        comboBox.addItem(item);
    }

    public void removeItem(String item) {
        comboBox.removeItem(item);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
