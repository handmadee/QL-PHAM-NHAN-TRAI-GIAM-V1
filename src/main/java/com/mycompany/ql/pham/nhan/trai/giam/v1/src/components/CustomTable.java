package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomTable extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public CustomTable(String[] columnNames, int width, int height) {
        setLayout(new BorderLayout());
        // Tạo model cho bảng với các cột truyền vào
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Thêm bảng vào JScrollPane để có thanh cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Thiết lập kích thước ưa thích cho bảng
        setPreferredSize(new Dimension(width, height));
    }

    // Phương thức để thêm hàng mới vào bảng
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    // Phương thức để xóa hàng dựa vào chỉ số
    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < tableModel.getRowCount()) {
            tableModel.removeRow(rowIndex);
        }
    }

    // Phương thức lấy bảng (nếu cần tương tác trực tiếp)
    public JTable getTable() {
        return table;
    }

    // Phương thức lấy model của bảng (nếu cần)
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
