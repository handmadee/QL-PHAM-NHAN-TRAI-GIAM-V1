package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class SelectDate extends JPanel {

    private DatePicker datePicker; // DatePicker để chọn ngày

    public SelectDate() {
        // Cấu hình cho DatePicker
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy");
        dateSettings.setAllowEmptyDates(false); // Không cho phép bỏ trống ngày

        // Khởi tạo DatePicker
        datePicker = new DatePicker(dateSettings);

        // Bố cục cho component
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(datePicker);
    }

    // Phương thức lấy ngày đã chọn
    public LocalDate getSelectedDate() {
        return datePicker.getDate();
    }

    // Kiểm tra hoạt động của SelectDate như một component
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test SelectDate Component");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null); // Căn giữa màn hình

        SelectDate selectDate = new SelectDate(); // Khởi tạo component SelectDate

        // Nút để hiển thị ngày đã chọn
        JButton btnShowDate = new JButton("In Ngày");
        btnShowDate.addActionListener(e -> {
            LocalDate selectedDate = selectDate.getSelectedDate();
            if (selectedDate != null) {
                JOptionPane.showMessageDialog(frame, 
                    "Ngày được chọn: " + selectedDate, 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, 
                    "Chưa chọn ngày.", 
                    "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Bố cục giao diện thử nghiệm
        frame.setLayout(new BorderLayout());
        frame.add(selectDate, BorderLayout.NORTH);
        frame.add(btnShowDate, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
