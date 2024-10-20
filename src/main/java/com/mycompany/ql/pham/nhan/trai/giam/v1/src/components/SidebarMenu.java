package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class SidebarMenu extends JPanel {

    public SidebarMenu() {
        buildSidebarMenu();
    }

    private void buildSidebarMenu() {
        setBackground(new Color(0, 50, 100));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Logo
        JLabel logo = new JLabel("ADMIN VITE", SwingConstants.CENTER);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(logo);
        // Menu items với icon và text
        add(createMenuItem("Trang Chủ", "/access/icon/dash.png"));
        add(createMenuItem("Quản lý trại giam", "/access/icon/flag.png"));
        add(createMenuItem("Quản lý phạm nhân", "/access/icon/stock.png"));
        add(createMenuItem("Quản lý đơn đăng ký thăm", "/access/icon/dash.png"));
        add(Box.createVerticalStrut(20)); // Khoảng cách giữa các mục
        add(createMenuItem("Thông tin !", "/access/icon/user.png"));
        add(createMenuItem("Đăng Xuất", "/access/icon/setting.png"));
    }

    private JPanel createMenuItem(String text, String iconPath) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(0, 50, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); 
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(iconLabel);
        panel.add(textLabel);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(0, 70, 140));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new Color(0, 50, 100));
            }
        });

        return panel;
    }
}
