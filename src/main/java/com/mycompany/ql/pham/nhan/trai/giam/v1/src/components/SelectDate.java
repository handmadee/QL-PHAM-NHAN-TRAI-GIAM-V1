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
        dateSettings.setAllowEmptyDates(false); 

        // Khởi tạo DatePicker
        datePicker = new DatePicker(dateSettings);

        // Bố cục cho component
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(datePicker);
    }

    
    public LocalDate getSelectedDate() {
        return datePicker.getDate();
    }

 
    public void setDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date); // Chuyển chuỗi sang LocalDate
            datePicker.setDate(localDate); // Thiết lập ngày cho DatePicker
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Ngày không hợp lệ. Định dạng phải là yyyy-MM-dd.", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


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

      
        JButton btnSetDate = new JButton("Thiết Lập Ngày");
        btnSetDate.addActionListener(e -> {
            String inputDate = JOptionPane.showInputDialog(
                frame, "Nhập ngày (yyyy-MM-dd):", "Thiết Lập Ngày", 
                JOptionPane.QUESTION_MESSAGE);
            if (inputDate != null && !inputDate.trim().isEmpty()) {
                selectDate.setDate(inputDate); // Gọi phương thức setDate
            }
        });


        frame.setLayout(new BorderLayout());
        frame.add(selectDate, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnShowDate);
        buttonPanel.add(btnSetDate);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
