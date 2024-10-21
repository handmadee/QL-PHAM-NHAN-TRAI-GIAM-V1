package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays.MainFrame;

public class SidebarMenu extends JPanel {
    private MainFrame mainFrame;
    
//    public  SidebarMenu(){
//                buildSidebarMenu();
//    }
    public SidebarMenu(MainFrame frame) {
        this.mainFrame = frame;
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

        // Thêm các mục menu với sự kiện click
        add(createMenuItem("Trang Chủ", "/access/icon/dash.png", "Home"));
        add(createMenuItem("Quản lý trại giam", "/access/icon/flag.png", "PrisonManagement"));
        add(createMenuItem("Quản lý phạm nhân", "/access/icon/stock.png", "OffenseManagementGUI"));
        add(createMenuItem("Quản lý phiếu đăng ký", "/access/icon/stock.png", "RegistrationManageGUI"));
        add(Box.createVerticalStrut(20)); // Khoảng cách giữa các mục
        add(createMenuItem("Thông tin !", "/access/icon/user.png", "Infor"));
        add(createMenuItem("Đăng Xuất", "/access/icon/setting.png", "Trang Chủ"));
    }

    private JPanel createMenuItem(String text, String iconPath, String screenName) {
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

        // Xử lý sự kiện click vào menu item
        panel.addMouseListener(new MouseAdapter() {
             @Override
            public void mouseClicked(MouseEvent e) {
                if (mainFrame != null) {
                    mainFrame.switchScreen(screenName); 
                } else {
                    System.err.println("MainFrame is null!");  
                }
            }

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
