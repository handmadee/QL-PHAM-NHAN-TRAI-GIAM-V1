package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomSearchComponent extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    static String iconPath = "/access/icon/search.png";
    public CustomSearchComponent(String placeholderText) {
        setLayout(new BorderLayout(10, 0));
        setPreferredSize(new Dimension(350, 45));
        setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(200, 200, 200));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g);
            }
            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        roundedPanel.setLayout(new BorderLayout(10, 0));
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundedPanel.setPreferredSize(new Dimension(330, 35));


        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPlaceholder(searchField, placeholderText);


        searchButton = new JButton();
        searchButton.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setOpaque(false);
        searchButton.setPreferredSize(new Dimension(40, 35));

        
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(new Color(72, 181, 255));
                searchButton.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setOpaque(false);
            }
        });


        roundedPanel.add(searchField, BorderLayout.CENTER);
        roundedPanel.add(searchButton, BorderLayout.EAST);

     
        add(roundedPanel, BorderLayout.CENTER);

        
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 2)
        ));
    }

    
    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }


    public String getSearchText() {
        return searchField.getText().trim();
    }

 
    public void addSearchAction(ActionListener action) {
        searchButton.addActionListener(action);
    }
}
