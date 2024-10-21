/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ql.pham.nhan.trai.giam.v1.src.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class Input{
        public JPanel createInputPanel(String labelText, JTextField textField) {
        return createInputPanel(labelText, textField, 140, 25,50,260);
    }
       public JPanel createInputPanel(String labelText, JTextField textField, int Lbwidth , int Lbheight,
               int txtHeight, int txtWidth
               ){
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        textField.setPreferredSize(new Dimension(txtWidth, txtHeight));
        JLabel label = new JLabel(labelText + ":");
        label.setPreferredSize(new Dimension(Lbwidth, Lbheight));
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
       }
}
