package com.mycompany.ql.pham.nhan.trai.giam.v1.src.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Prison;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrisonService {
    private static final String FILE_PATH = "src/main/java/com/mycompany/ql/pham/nhan/trai/giam/v1/src/data/prison.json";
    private List<Prison> prisons;
    private Gson gson;

    public PrisonService() {
        gson = new Gson();
        prisons = loadPrisons();
    }

    // Đọc danh sách trại giam từ file JSON
    private List<Prison> loadPrisons() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Prison>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Không thể đọc file JSON, tạo danh sách mới.");
            return new ArrayList<>();
        }
    }

    // Lưu danh sách trại giam vào file JSON
    private void savePrisons() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(prisons, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thêm trại giam mới
    public void addPrison(Prison prison) {
        prisons.add(prison);
        savePrisons();
        System.out.println("Đã thêm trại giam: " + prison);
    }

    // Xóa trại giam theo ID
    public boolean deletePrison(int id) {
        boolean removed = prisons.removeIf(prison -> prison.getId() == id);
        if (removed) {
            savePrisons();
            System.out.println("Đã xóa trại giam với ID: " + id);
        }
        return removed;
    }

    // Tìm kiếm trại giam theo tên
    public List<Prison> searchByName(String name) {
        return prisons.stream()
                .filter(prison -> prison.getNamePrison().contains(name))
                .toList();
    }

    // Tìm kiếm trại giam theo ID
    public Optional<Prison> searchById(int id) {
        return prisons.stream()
                .filter(prison -> prison.getId() == id)
                .findFirst();
    }

    // Cập nhật thông tin trại giam theo ID
    public boolean updatePrison(int id, String newName, String newLocation, int newCapacity) {
        Optional<Prison> optionalPrison = searchById(id);
        if (optionalPrison.isPresent()) {
            Prison prison = optionalPrison.get();
            prison.setNamePrison(newName);
            prison.setLocation(newLocation);
            prison.setCapacity(newCapacity);
            savePrisons();
            System.out.println("Đã cập nhật trại giam với ID: " + id);
            return true;
        } else {
            System.out.println("Không tìm thấy trại giam với ID: " + id);
            return false;
        }
    }

    // Lấy tổng số trại giam
    public int getTotalPrisons() {
        return prisons.size();
    }

    // Lấy số lượng tù nhân trong một trại giam theo ID
    public int getPrisonerCountInPrison(int id) {
        Optional<Prison> optionalPrison = searchById(id);
        if (optionalPrison.isPresent()) {
            return optionalPrison.get().getPrisoners().size();
        } else {
            System.out.println("Không tìm thấy trại giam với ID: " + id);
            return 0;
        }
    }

    // Lấy danh sách tất cả trại giam
    public List<Prison> getAllPrisons() {
        return new ArrayList<>(prisons);
    }
}
