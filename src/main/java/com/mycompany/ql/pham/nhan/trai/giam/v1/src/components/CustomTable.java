package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomTable extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public CustomTable(String[] columnNames, int width, int height) {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(width, height));
    }

    // Thêm một hàng mới vào bảng
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    // Xóa hàng theo chỉ số
    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < tableModel.getRowCount()) {
            tableModel.removeRow(rowIndex);
        }
    }

    // Lấy bảng (JTable) để tương tác trực tiếp nếu cần
    public JTable getTable() {
        return table;
    }

    // Lấy model của bảng để truy cập dữ liệu
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    // Lấy dữ liệu của hàng đã chọn
    public Object[] getSelectedRowData() {
        int selectedRow = table.getSelectedRow();  
        if (selectedRow == -1) {
            return null;  
        }

        int columnCount = table.getColumnCount();
        Object[] rowData = new Object[columnCount];
        for (int i = 0; i < columnCount; i++) {
            rowData[i] = table.getValueAt(selectedRow, i);
        }
        return rowData;
    }

    // Lấy chỉ số của hàng đã chọn
    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    // Lấy giá trị tại hàng và cột cụ thể
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < tableModel.getRowCount() &&
            columnIndex >= 0 && columnIndex < tableModel.getColumnCount()) {
            return table.getValueAt(rowIndex, columnIndex);
        } else {
            throw new IndexOutOfBoundsException("Chỉ số hàng hoặc cột không hợp lệ.");
        }
    }

    // Phương thức để xóa tất cả dữ liệu trong bảng
    public void clearTable() {
        tableModel.setRowCount(0);
    }

    // Phương thức để set dữ liệu mới vào bảng
    public void setData(List<Object[]> data) {
        clearTable(); // Xóa dữ liệu cũ
        for (Object[] row : data) {
            addRow(row); // Thêm từng hàng mới
        }
    }
}
