package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Registration;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.RegistrationService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.PrisonerService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.utils.Validator;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.*;

public class RegistrationPanel extends JPanel {
    private JTextField txtName, txtUID, txtCCCD, txtAddress, txtReason;
    private CustomDropdown txtRelationship;
    private DefaultTableModel tableModel;
    private JTable table;
    private RegistrationService service;
    private PrisonerService servicePrisoner;

    public RegistrationPanel() {
        service = new RegistrationService();
        servicePrisoner = new PrisonerService();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.NORTH);

        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        loadTableData();
        setupTableSelectionListener();
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Input inputHelper = new Input();

        JLabel lblTitle = new JLabel("THÔNG TIN PHIẾU ĐĂNG KÝ");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtName = new JTextField(20);
        txtUID = new JTextField(20);
        txtCCCD = new JTextField(20);
        txtAddress = new JTextField(20);
        txtReason = new JTextField(20);

        JPanel panelDropDown = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDropDown.setPreferredSize(new Dimension(300, 50));
        panelDropDown.add(new JLabel("Quan hệ với nhân thân:"));
        String[] items = {"Trong gia đình", "Họ hàng - làng xóm", "Người lạ"};
        txtRelationship = new CustomDropdown(items);
        panelDropDown.add(txtRelationship);

        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add(inputHelper.createInputPanel("Họ và tên", txtName));
        panel.add(inputHelper.createInputPanel("UID Phạm nhân", txtUID));
        panel.add(panelDropDown);
        panel.add(inputHelper.createInputPanel("CCCD/CMND", txtCCCD));
        panel.add(inputHelper.createInputPanel("Địa chỉ", txtAddress));
        panel.add(inputHelper.createInputPanel("Lý do", txtReason));

        CustomButton btnAdd = new CustomButton("Thêm");
        CustomButton btnEdit = new CustomButton("Sửa");
        CustomButton btnRemove = new CustomButton("Xoá");

        btnAdd.addActionListener(e -> addRegistration());
        btnEdit.addActionListener(e -> editRegistration());
        btnRemove.addActionListener(e -> removeRegistration());

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
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(lblTableTitle, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void setupTableSelectionListener() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    int id = (int) tableModel.getValueAt(selectedRow, 0); // Lấy ID từ cột đầu tiên
                    loadRegistrationToForm(id);
                }
            }
        });
    }

    private void loadRegistrationToForm(int id) {
        Registration reg = service.getRegistrationById(id);
        int uid = reg.getUid();
        String uidString = String.valueOf(uid);
        if (reg != null) {
            txtName.setText(reg.getName());
            txtUID.setText(uidString);
            txtCCCD.setText(reg.getIdentification());
            txtAddress.setText(reg.getAddress());
            txtReason.setText(reg.getReason());
            txtRelationship.setSelectedItem(reg.getRelationship());
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu đăng ký với ID: " + id, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        List<Registration> registrations = service.getAllRegistrations();
        tableModel.setRowCount(0);
        for (Registration reg : registrations) {
            tableModel.addRow(new Object[]{reg.getId(), reg.getName(), reg.getRelationship(), reg.getIdentification()});
        }
    }

    private void addRegistration() {
        try {
            validateForm();

            String name = txtName.getText();
            String uid = txtUID.getText();
            String relationship = (String) txtRelationship.getSelectedItem();
            String cccd = txtCCCD.getText();
            String address = txtAddress.getText();
            String reason = txtReason.getText();
            String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // find 
            if(!servicePrisoner.findPrisonerById(Integer.parseInt(uid))){
               throw new IllegalArgumentException("Phạm nhân không tồn tại trong hệ thống");   
            }
            service.addRegistration(name, Integer.parseInt(uid), relationship, cccd, address, reason, formattedDate);
            loadTableData();
            clearInputs();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editRegistration() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                throw new IllegalArgumentException("Vui lòng chọn một dòng để sửa!");
            }

            validateForm();

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String name = txtName.getText();
            String uid = txtUID.getText();
            String relationship = (String) txtRelationship.getSelectedItem();
            String cccd = txtCCCD.getText();
            String address = txtAddress.getText();
            String reason = txtReason.getText();
            String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            service.updateRegistration(id, name, Integer.parseInt(uid), relationship, cccd, address, reason, formattedDate);
            loadTableData();
            clearInputs();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateForm() {
        Validator.validateNotEmpty(txtName.getText(), "Họ và tên");
        Validator.validateNotEmpty(txtUID.getText(), "UID");
        Validator.validateCCCD(txtCCCD.getText());
        Validator.validateNotEmpty(txtAddress.getText(), "Địa chỉ");
        Validator.validateNotEmpty(txtReason.getText(), "Lý do");
    }

    private void removeRegistration() {
        if (confirmAction("Bạn có chắc chắn muốn xoá thông tin này không?")) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                service.deleteRegistration(id);
                loadTableData();
            }
        }
    }

    private boolean confirmAction(String message) {
        int result = JOptionPane.showConfirmDialog(this, message, "Xác nhận", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    private void clearInputs() {
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
