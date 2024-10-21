package com.mycompany.ql.pham.nhan.trai.giam.v1.src.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.ql.pham.nhan.trai.giam.v1.models.Account;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AccountService {
    private static final String FILE_PATH = "src/main/java/com/mycompany/ql/pham/nhan/trai/giam/v1/src/data/accounts.json";  
    private ArrayList<Account> accounts;
    private Gson gson;


    // Constructor
    public AccountService() {
        gson = new Gson();
        accounts = loadAccounts();  
    }

    // Đọc danh sách tài khoản từ file JSON
    private ArrayList<Account> loadAccounts() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Account>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Không thể đọc file JSON, tạo danh sách mới.");
            return new ArrayList<>();
        }
    }

    // Lưu danh sách tài khoản vào file JSON
    private void saveAccounts() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra đăng nhập
    public boolean login(String username, String password) {
        return accounts.stream()
                .anyMatch(acc -> acc.getUsername().equals(username) && acc.getPassword().equals(password));
    }

    // Thêm tài khoản mới
    public void addAccount(String username, String password, int role) {
        Account newAccount = new Account(username, password, role);
        accounts.add(newAccount);
        saveAccounts();
        System.out.println("Đã thêm tài khoản: " + newAccount);
    }

    // Xóa tài khoản theo ID
    public boolean deleteAccount(int id) {
        boolean removed = accounts.removeIf(acc -> acc.getId() == id);
        if (removed) {
            saveAccounts();
            System.out.println("Đã xóa tài khoản với ID: " + id);
        } else {
            System.out.println("Không tìm thấy tài khoản với ID: " + id);
        }
        return removed;
    }

    // Lấy danh sách tất cả tài khoản
    public ArrayList<Account> getAllAccounts() {
        return accounts;
    }
}
