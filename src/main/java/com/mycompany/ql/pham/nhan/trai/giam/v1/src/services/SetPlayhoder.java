/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ql.pham.nhan.trai.giam.v1.src.services;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class SetPlayhoder {
         public static void setPlaceholder(JTextField textField, String placeholder) {
        // Đặt màu placeholder ban đầu nếu JTextField trống
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        // Lắng nghe sự kiện focus để xử lý placeholder
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText(""); 
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder); 
                    textField.setForeground(Color.GRAY); 
                }
            }
        });
    }

}
