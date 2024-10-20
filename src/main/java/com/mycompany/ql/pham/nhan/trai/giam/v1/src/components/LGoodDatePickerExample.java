/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.FlowLayout;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author admin
 */
public class LGoodDatePickerExample extends JFrame {

    public LGoodDatePickerExample() {
        // Cấu hình cho DatePicker
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy");
        dateSettings.setAllowEmptyDates(false); // Không cho phép bỏ trống ngày

        // Khởi tạo DatePicker
        DatePicker datePicker = new DatePicker(dateSettings);

        // Thêm DatePicker vào JFrame
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Chọn ngày:"));
        this.add(datePicker);

        // Nút để in ngày được chọn
        JButton btnPrintDate = new JButton("In Ngày");
        btnPrintDate.addActionListener(e -> {
            LocalDate selectedDate = datePicker.getDate();
            if (selectedDate != null) {
                System.out.println("Ngày được chọn: " + selectedDate);
            } else {
                System.out.println("Chưa chọn ngày.");
            }
        });

        this.add(btnPrintDate);

        // Thiết lập JFrame
        this.setTitle("Ví dụ LGoodDatePicker");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LGoodDatePickerExample::new);
    }
}