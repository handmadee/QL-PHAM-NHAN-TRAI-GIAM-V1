/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ql.pham.nhan.trai.giam.v1.src.utils;

import javax.swing.JPasswordField;

/**
 *
 * @author admin
 */
public class Validator {
    // Hàm kiểm tra chuỗi không được để trống
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " không được để trống.");
        }
    }
    
    public static void validateUsername(String username) {
    if (username == null || username.trim().isEmpty()) {
        throw new IllegalArgumentException("Tên đăng nhập không được để trống.");
    }
    if (username.length() < 6) {
        throw new IllegalArgumentException("Tên đăng nhập phải có ít nhất 6 ký tự.");
    }
}


    // Hàm kiểm tra độ dài tối thiểu của chuỗi
    public static void validateMinLength(String value, int minLength, String fieldName) {
        if (value.length() < minLength) {
            throw new IllegalArgumentException(fieldName + " phải có ít nhất " + minLength + " ký tự.");
        }
    }

    // Hàm kiểm tra định dạng email
    public static void validateEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email không hợp lệ.");
        }
    }
    
    public static void validatePhoneNumber(String phoneNumber) {
    if (!phoneNumber.matches("0\\d{9}")) {
        throw new IllegalArgumentException("Số điện thoại phải có 10 chữ số và bắt đầu bằng 0.");
    }
 }
    public static void validateCCCD(String cccd) {
    if (!cccd.matches("\\d{12}")) {
        throw new IllegalArgumentException("CCCD phải bao gồm đúng 12 chữ số.");
    }
}
    
    public static void validatePositiveNumber(int number, String fieldName) {
    if (number <= 0) {
        throw new IllegalArgumentException(fieldName + " phải lớn hơn 0.");
    }
}

 


    
}
