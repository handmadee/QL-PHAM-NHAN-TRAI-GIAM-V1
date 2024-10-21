package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;

import javax.swing.*;
import java.awt.*;

public class AboutViteGUI extends JPanel {
    public AboutViteGUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel lblTitle = new JLabel("GIỚI THIỆU VỀ PHẦN MỀM VITE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(33, 67, 123));
        lblTitle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),  
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        add(lblTitle, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createTitledBorder("Giới thiệu"));
        
        JTextArea textArea = new JTextArea(
                "VITE là phần mềm quản lý trại giam và phạm nhân\n" +
                "+ Cung cấp các chức năng hiệu quả để theo dõi thông tin phạm nhân\n" +
                "+ Chức năng quản lý trại giam và đăng ký thăm nuôi\n" +
                "+ Phần mềm giúp tối ưu hóa quy trình và nâng cao hiệu quả quản lý"
        );
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));  // Bo viền cho text area
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        contentPanel.add(textArea);

        add(contentPanel, BorderLayout.CENTER);
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));
        infoPanel.add(createLabel("Version:", true));
        JTextField txtVersion = createTextField("v1.0");
        infoPanel.add(txtVersion);
        infoPanel.add(createLabel("Release date:", true));
        JTextField txtReleaseDate = createTextField("19/10/2024");
        infoPanel.add(txtReleaseDate);
        infoPanel.add(createLabel("Implementer:", true));
        JTextField txtImplementer = createTextField("Duy Hưng & Đăng Vũ");
        infoPanel.add(txtImplementer);

        add(infoPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text, boolean bold) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        label.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, 14));
        return label;
    }


    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));  
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Giới Thiệu Về Phần Mềm VITE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new AboutViteGUI());
        frame.setVisible(true);
    }
}
