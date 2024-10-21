package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;
import javax.swing.*;
import java.awt.*;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.SidebarMenu;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays.HomeGUI;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays.AboutViteGUI;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays.DashBoardGUI;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays.OffenseManagementGUI;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.utils.RefreshablePanel;


public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    public MainFrame() {
        setTitle("Quản lý phạm nhân");
        setSize(1300, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // SidebarMenu (Menu bên trái)
        SidebarMenu sidebarMenu = new SidebarMenu(this);
        add(sidebarMenu, BorderLayout.WEST);
        // Content panel chứa các màn hình, sử dụng CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);
//         HomeGUI homeGUI = new HomeGUI();
//        contentPanel.add(homeGUI.getContentPanel(), "Home");
        contentPanel.add(new DashBoardGUI(), "Home");
        contentPanel.add(new AboutViteGUI(), "Infor");
        contentPanel.add(new PrisonManagement(), "PrisonManagement");
        contentPanel.add(new OffenseManagementGUI(), "OffenseManagementGUI");
        contentPanel.add(new  RegistrationPanel(), "RegistrationManageGUI");
        cardLayout.show(contentPanel, "Home");
    }

    public void switchScreen(String screenName) {
        cardLayout.show(contentPanel, screenName);
       for (Component component : contentPanel.getComponents()) {
        if (component.isVisible() && component instanceof RefreshablePanel) {
            ((RefreshablePanel) component).refreshData();
        }
    }
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
