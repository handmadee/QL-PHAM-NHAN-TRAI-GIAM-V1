package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Prison;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.PrisonService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomButton;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomTable;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.utils.Validator;
import java.util.ArrayList;

public class PrisonManagement extends JPanel {
    private JTextField txtTenTrai, txtViTri, txtSucChua;
    private JButton btnThem, btnXoa, btnChinhSua;
    private CustomTable table;
    private PrisonService prisonService;

    public PrisonManagement() {
        prisonService = new PrisonService();
        setLayout(new BorderLayout());

        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.WEST);

        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        initButtonActions();
        refreshTable();
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 0));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel lblTitle = new JLabel("THÔNG TIN TRẠI GIAM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtTenTrai = new JTextField(10);
        txtViTri = new JTextField(10);
        txtSucChua = new JTextField(10);
        btnThem = new CustomButton("Thêm");
        btnXoa = new CustomButton("Xoá");
        btnChinhSua = new CustomButton("Sửa");

        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(15));
        panel.add(createInputPanel("Tên Trại Giam:", txtTenTrai));
        panel.add(createInputPanel("Vị Trí:", txtViTri));
        panel.add(createInputPanel("Sức Chứa:", txtSucChua));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnChinhSua);
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createInputPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lblTableTitle = new JLabel("DANH SÁCH TRẠI GIAM", SwingConstants.CENTER);
        lblTableTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        String[] columnNames = {"ID", "Tên Trại", "Khu Vực", "Sức Chứa"};
        table = new CustomTable(columnNames, 600, 300);

        table.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedRowData();
            }
        });

        panel.add(lblTableTitle, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private void initButtonActions() {
        btnThem.addActionListener(e -> {
            try {
                validateInputs();
                String name = txtTenTrai.getText();
                String location = txtViTri.getText();
                int capacity = Integer.parseInt(txtSucChua.getText());

                prisonService.addPrison(new Prison(name, location, capacity));
                refreshTable();
                JOptionPane.showMessageDialog(this, "Đã thêm trại giam thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

      btnXoa.addActionListener(e -> {
    int selectedRow = table.getSelectedRowIndex();
    if (selectedRow != -1) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa trại giam này không?",
            "Xác nhận Xóa",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.OK_OPTION) {
            int id = (int) table.getValueAt(selectedRow, 0);
            prisonService.deletePrison(id);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Đã xóa trại giam thành công!");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
    }
});

   btnChinhSua.addActionListener(e -> {
    try {
        validateInputs();
        int selectedRow = table.getSelectedRowIndex();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn cập nhật thông tin trại giam này không?",
                "Xác nhận Cập nhật",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirm == JOptionPane.OK_OPTION) {
                int id = (int) table.getValueAt(selectedRow, 0);
                String newName = txtTenTrai.getText();
                String newLocation = txtViTri.getText();
                int newCapacity = Integer.parseInt(txtSucChua.getText());

                prisonService.updatePrison(id, newName, newLocation, newCapacity);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Đã cập nhật trại giam thành công!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để chỉnh sửa.");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
});
    }

    private void refreshTable() {
        try {
            List<Prison> prisons = prisonService.getAllPrisons();
            List<Object[]> data = new ArrayList<>();

            for (Prison prison : prisons) {
                Object[] row = {
                    prison.getId(),
                    prison.getNamePrison(),
                    prison.getLocation(),
                    prison.getCapacity()
                };
                data.add(row);
            }
            table.setData(data);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displaySelectedRowData() {
        int selectedRow = table.getSelectedRowIndex();
        if (selectedRow != -1) {
            txtTenTrai.setText((String) table.getValueAt(selectedRow, 1));
            txtViTri.setText((String) table.getValueAt(selectedRow, 2));
            txtSucChua.setText(table.getValueAt(selectedRow, 3).toString());
        }
    }

    private void validateInputs() {
        Validator.validateNotEmpty(txtTenTrai.getText(), "Tên trại giam");
        Validator.validateNotEmpty(txtViTri.getText(), "Vị trí");
        Validator.validatePositiveNumber(Integer.parseInt(txtSucChua.getText()), "Sức chứa");
    }
}
