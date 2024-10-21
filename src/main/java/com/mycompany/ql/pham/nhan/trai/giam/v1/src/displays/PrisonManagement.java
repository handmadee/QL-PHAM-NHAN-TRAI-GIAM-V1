package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;
import javax.swing.*;
import java.awt.*;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.Input;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomButton;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomSearchComponent;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomTable;

public class PrisonManagement extends JPanel {
    private JTextField txtTenTrai, txtViTri, txtSucChua;
    private JButton btnThem, btnXoa, btnChinhSua;
    private JTable table;

    public PrisonManagement() {
        setLayout(new BorderLayout());
        // Panel thông tin trại giam
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.WEST);
        // Table danh sách trại giam
//        String[] columnNames = {"UID", "Tên Trại", "Khu Vực", "Sức Chứa"};
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        Input inputHelper = new Input();
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
        panel.add(inputHelper.createInputPanel("Tên Trại Giam :", txtTenTrai,70,25,30,200));
        panel.add(inputHelper.createInputPanel("Vị Trí :", txtViTri,70,25,30,200));
        panel.add(inputHelper.createInputPanel("Sức chứa :", txtSucChua,70,25,30,200));
        panel.add(Box.createVerticalStrut(10));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnChinhSua);
        panel.add(buttonPanel);
        return panel;
    }

    private JPanel createLabelAndTextField(String labelText, JTextField textField) {
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
    CustomSearchComponent search = new CustomSearchComponent("search.....");
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
    topPanel.add(lblTableTitle);
    topPanel.add(search);
    String[] columnNames = {"UID", "Tên Trại", "Khu Vực", "Sức Chứa"};
    Object[][] data = {};
    CustomTable table = new CustomTable(columnNames, 600, 300);
    panel.add(topPanel, BorderLayout.NORTH);
    panel.add(new JScrollPane(table), BorderLayout.CENTER);
    return panel;
}

}