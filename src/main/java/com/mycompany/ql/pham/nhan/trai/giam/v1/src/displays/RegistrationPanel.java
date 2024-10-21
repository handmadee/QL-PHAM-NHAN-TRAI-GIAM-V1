package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.Input;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomButton;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomDropdown;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomTable;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomSearchComponent;

public class RegistrationPanel extends JPanel {
    private JTextField txtName, txtUID, txtCCCD, txtAddress, txtReason;
    private JTable table;
    private DefaultTableModel tableModel;
 
    public RegistrationPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        // Panel thông tin đăng ký
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.NORTH);
        // Search 
       
         
        // Bảng danh sách đăng ký
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Input inputHelper = new Input();
        JLabel lblTitle = new JLabel("THÔNG TIN PHIẾU ĐĂNG KÍ");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtName = new JTextField(20);
        txtUID = new JTextField(20);
        txtCCCD = new JTextField(20);
        txtAddress = new JTextField(20);
        txtReason = new JTextField(20);
        // lIST 
        JPanel panelDropDown = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDropDown.setPreferredSize(new Dimension(300, 50));
        panelDropDown.add(new JLabel("Quan hệ với nhân thân :"));
        String[] item = {"Trong gia đình", "Họ hàng - làng xóm", "Người lạ"};
        CustomDropdown txtRelationship = new CustomDropdown(item);
        panelDropDown.add(txtRelationship);
        
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add( inputHelper.createInputPanel("Họ và tên", txtName));
        panel.add(inputHelper.createInputPanel("UID Phạm nhân", txtUID));
        panel.add(panelDropDown);
        panel.add(inputHelper.createInputPanel("CCCD/CMND", txtCCCD));
        panel.add(inputHelper.createInputPanel("Địa chỉ", txtAddress));
        panel.add(inputHelper.createInputPanel("Lý do", txtReason));
        CustomButton btnAdd = new CustomButton("Thêm");
        CustomButton btnEdit = new CustomButton("Sửa");
        CustomButton btnRemove = new CustomButton("Xoá");
        btnAdd.addActionListener(e -> addRow());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnRemove);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonPanel);
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblTableTitle = new JLabel("DANH SÁCH PHIẾU ĐĂNG KÝ", SwingConstants.CENTER);
        lblTableTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        String[] columnNames = {"ID", "Tên", "Quan Hệ NT", "CCCD"};
        CustomTable customTable = new CustomTable(columnNames,600,300);
        JScrollPane scrollPane = new JScrollPane(customTable);
         CustomSearchComponent searchComponent = new CustomSearchComponent("Nhập để tìm kiếm...");
        panel.add(lblTableTitle, BorderLayout.NORTH);
        panel.add(searchComponent, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void addRow() {
        String name = txtName.getText();
        String uid = txtUID.getText();
        String cccd = txtCCCD.getText();
        tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, name, cccd});
        txtName.setText("");
        txtUID.setText("");
        txtCCCD.setText("");
        txtAddress.setText("");
        txtReason.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản Lý Phiếu Đăng Ký");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new RegistrationPanel());
        frame.setVisible(true);
    }
}
