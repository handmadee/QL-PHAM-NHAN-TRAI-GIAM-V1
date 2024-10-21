package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Prisoner;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Prison;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.PrisonerService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.PrisonService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.*;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.utils.Validator;

public class OffenseManagementGUI extends JPanel {
    private JTextField txtTen, txtToiDanh;
    private CustomDropdown txtPrison; 
    private CustomDropdown txtStatus;
    private SelectDate selectDate; 
    private CustomTable table; 
    private JButton btnThem, btnXoa, btnChinhSua; 
    private PrisonerService prisonerService; 
    private PrisonService prisonService;

    public OffenseManagementGUI() {
        prisonerService = new PrisonerService();
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

        JLabel lblTitle = new JLabel("THÔNG TIN PHẠM NHÂN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtTen = new JTextField(10);
        txtToiDanh = new JTextField(10);
        
        // Tạo dropdown cho trại giam
        List<Prison> prisons = prisonService.getAllPrisons();
        String[] prisonNames = prisons.stream().map(Prison::getNamePrison).toArray(String[]::new);
        txtPrison = new CustomDropdown(prisonNames);

        // Tạo dropdown cho trạng thái
        String[] statusItems = {"Đang chấp hành án", "Hoàn thành chấp hành án", "Đã mất"};
        txtStatus = new CustomDropdown(statusItems);

        selectDate = new SelectDate(); 

        btnThem = new CustomButton("Thêm");
        btnXoa = new CustomButton("Xoá");
        btnChinhSua = new CustomButton("Sửa");
        
        JPanel panelTrai = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTrai.add(new JLabel("Trại Giam:"));
        panelTrai.add(txtPrison);
        
        
        JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelStatus.add(new JLabel("Trạng Thái:"));
        panelStatus.add(txtStatus);
        
        
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(15));
        panel.add(panelTrai);
        panel.add(createInputPanel("Tên:", txtTen));
        panel.add(createDatePanel());
        panel.add(createInputPanel("Tội Danh:", txtToiDanh));
       panel.add(panelStatus);
        panel.add(Box.createVerticalStrut(10));

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

    private JPanel createDatePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Ngày Sinh:"));
        panel.add(selectDate);
        return panel;
    }



    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel lblTableTitle = new JLabel("DANH SÁCH PHẠM NHÂN", SwingConstants.CENTER);
        lblTableTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        CustomSearchComponent search = new CustomSearchComponent("Tìm kiếm theo tên hoặc tội danh");
        search.addSearchAction(query -> searchPrisoners(search.getSearchText()));

        String[] columnNames = {"UID", "Tên", "Ngày Sinh", "Tội Danh", "Trạng Thái"};
        table = new CustomTable(columnNames, 600, 300);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(lblTableTitle);
        topPanel.add(search);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        table.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedRowData();
            }
        });

        return panel;
    }

    private void initButtonActions() {
        btnThem.addActionListener(e -> {
            try {
                validateInputs();
                int selectedPrisonId = prisonerService.getPrisonIdByName(txtPrison.getSelectedItem().toString()); 
                Prisoner prisoner = new Prisoner(
                    txtTen.getText(),
                    selectDate.getSelectedDate().toString(),
                    txtToiDanh.getText(),
                    txtStatus.getSelectedItem().toString()
                );
                prisonerService.addPrisonerToPrison(selectedPrisonId, prisoner);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Đã thêm phạm nhân thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRowIndex();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(
                    this, "Bạn có chắc chắn muốn xóa phạm nhân này không?", 
                    "Xác nhận Xóa", JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    prisonerService.deletePrisonerById(id);
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Đã xóa phạm nhân thành công!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
            }
        });

        btnChinhSua.addActionListener(e -> {
            int selectedRow = table.getSelectedRowIndex();
            if (selectedRow != -1) {
                try {
                    validateInputs();
                    int id = (int) table.getValueAt(selectedRow, 0);
                    prisonerService.updatePrisonerById(id,
                        txtTen.getText().toString(),
                        selectDate.getSelectedDate().toString(),
                        txtToiDanh.getText().toString(),
                        txtStatus.getSelectedItem().toString() 
                    );
                 
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Đã cập nhật phạm nhân thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để chỉnh sửa.");
            }
        });
    }

    private void refreshTable() {
        List<Prisoner> prisoners = prisonerService.getAllPrisoners();
        List<Object[]> data = new ArrayList<>();
        for (Prisoner p : prisoners) {
            data.add(new Object[]{p.getId(), p.getName(), p.getDate(), p.getCrime(), p.getStatus()});
        }
        table.setData(data);
    }

    private void displaySelectedRowData() {
        int selectedRow = table.getSelectedRowIndex();
        if (selectedRow != -1) {
            txtTen.setText(table.getValueAt(selectedRow, 1).toString());
            selectDate.setDate(table.getValueAt(selectedRow, 2).toString());
            txtToiDanh.setText(table.getValueAt(selectedRow, 3).toString());
            txtStatus.setSelectedItem(table.getValueAt(selectedRow, 4).toString());
        }
    }

    private void searchPrisoners(String query) {
        List<Prisoner> result = prisonerService.searchPrisonersByNameOrCrime(query);
        List<Object[]> data = new ArrayList<>();
        for (Prisoner p : result) {
            data.add(new Object[]{p.getId(), p.getName(), p.getDate(), p.getCrime(), p.getStatus()});
        }
        table.setData(data);
    }

    private void validateInputs() {
        Validator.validateNotEmpty(txtTen.getText(), "Tên");
        Validator.validateNotEmpty(txtToiDanh.getText(), "Tội Danh");
    }
    
    
    
}
