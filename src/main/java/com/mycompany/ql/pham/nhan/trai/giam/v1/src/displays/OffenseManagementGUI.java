package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;

import javax.swing.*;
import java.awt.*;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.Input;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomButton;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomSearchComponent;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomTable;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.SelectDate;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.CustomDropdown;
public class OffenseManagementGUI extends JPanel {
    private JTextField txtUID, txtTen, txtNgaySinh, txtToiDanh, txtTrangThai;
    private JButton btnThem, btnXoa, btnChinhSua, btnDate;
    private JTable table;
    private SelectDate selectDate;
   

    public OffenseManagementGUI() {
        setLayout(new BorderLayout());
        // Panel thông tin phạm nhân
        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.WEST);

        // Panel danh sách phạm nhân
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

        JLabel lblTitle = new JLabel("THÔNG TIN PHẠM NHÂN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        
        // Tạo các trường nhập liệu
        txtUID = new JTextField(10);
        txtTen = new JTextField(10);
        txtNgaySinh = new JTextField(10);
        txtToiDanh = new JTextField(10);
        txtTrangThai = new JTextField(10);

        // Nút thao tác
        btnThem = new CustomButton("Thêm");
        btnXoa = new CustomButton("Xoá");
        btnChinhSua = new CustomButton("Sửa");
         
          // Select date
       selectDate = new SelectDate();  
        JPanel panelDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDate.setPreferredSize(new Dimension(300, 50));
        panelDate.add(new JLabel("Ngày sinh:"));
        panelDate.add(selectDate);
        
        // Dropdown 
         JPanel panelDropDown = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDropDown.setPreferredSize(new Dimension(300, 50));
        panelDropDown.add(new JLabel("Trạng thái :"));
        String[] item = {"Đang chấp hành án", "Hoàn thành chấp hành án", "Đã mất"};
        CustomDropdown txtStatus = new CustomDropdown(item);
        panelDropDown.add(txtStatus);
        
       
        // Thêm các thành phần vào panel
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(15));
        panel.add(inputHelper.createInputPanel("UID:", txtUID,70,25,30,200));
        panel.add(inputHelper.createInputPanel("Tên:", txtTen,70,25,30,200));
        panel.add(panelDate);
        panel.add(inputHelper.createInputPanel("Tội Danh:", txtToiDanh,70,25,30,200));
        panel.add(panelDropDown);
        panel.add(Box.createVerticalStrut(10));
        
        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnChinhSua);

        panel.add(buttonPanel);
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tiêu đề bảng
        JLabel lblTableTitle = new JLabel("DANH SÁCH PHẠM NHÂN", SwingConstants.CENTER);
        lblTableTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Ô tìm kiếm
        CustomSearchComponent search = new CustomSearchComponent("search.....");

        // Panel trên cùng chứa tiêu đề và tìm kiếm
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(lblTableTitle);
        topPanel.add(search);


        String[] columnNames = {"UID", "Tên", "Ngày Sinh", "Tội Danh", "Trạng Thái"};
        Object[][] data = {};
        CustomTable table = new CustomTable(columnNames, 600, 300);

        // Thêm các thành phần vào panel chính
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
}
