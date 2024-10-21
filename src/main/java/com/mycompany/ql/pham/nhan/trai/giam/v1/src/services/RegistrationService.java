package com.mycompany.ql.pham.nhan.trai.giam.v1.src.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Registration;

public class RegistrationService {
  private static final String FILE_PATH = "src/main/java/com/mycompany/ql/pham/nhan/trai/giam/v1/src/data/rigistration.json";
    private ArrayList<Registration> registrations;
    private Gson gson;
    private int currentId = 1;

    public RegistrationService() {
        gson = new Gson();
        registrations = loadRegistrations();
        if (!registrations.isEmpty()) {
            currentId = registrations.stream()
                                     .mapToInt(Registration::getId)
                                     .max()
                                     .orElse(0) + 1;
        }
    }

    private ArrayList<Registration> loadRegistrations() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Registration>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Không thể đọc file JSON, tạo danh sách mới.");
            return new ArrayList<>();
        }
    }

    private void saveRegistrations() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(registrations, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thêm đăng ký mới với ngày mặc định là ngày hiện tại nếu không có ngày nhập vào
    public void addRegistration(String name, int uid, String relationship, 
                                String identification, String address, String reason, String date) {
        Registration registration = new Registration(currentId++, name, uid, relationship, 
                                                     identification, address, reason, date);
        registrations.add(registration);
        saveRegistrations();
        System.out.println("Đã thêm: " + registration);
    }

    public boolean updateRegistration(int id, String name, int uid, String relationship, 
                                      String identification, String address, String reason, String date) {
        Optional<Registration> optionalReg = registrations.stream()
                                                          .filter(reg -> reg.getId() == id)
                                                          .findFirst();

        if (optionalReg.isPresent()) {
            Registration reg = optionalReg.get();
            reg.setName(name);
            reg.setUid(uid);
            reg.setRelationship(relationship);
            reg.setIdentification(identification);
            reg.setAddress(address);
            reg.setReason(reason);
            reg.setDate(date);
            saveRegistrations();

            return true;
        } else {
            System.out.println("Không tìm thấy đăng ký với ID: " + id);
            return false;
        }
    }

    public boolean deleteRegistration(int id) {
        boolean removed = registrations.removeIf(reg -> reg.getId() == id);
        if (removed) {
            saveRegistrations();
            System.out.println("Đã xóa đăng ký với ID: " + id);
        } else {
            System.out.println("Không tìm thấy đăng ký với ID: " + id);
        }
        return removed;
    }

    public List<Registration> searchByName(String name) {
        return registrations.stream()
                .filter(reg -> reg.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Registration> searchByDate(String date) {
        return registrations.stream()
                .filter(reg -> reg.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Registration> sortByDate() {
        return registrations.stream()
                .sorted(Comparator.comparing(Registration::getDate))
                .collect(Collectors.toList());
    }

    public List<Registration> getAllRegistrations() {
        return registrations;
    }
    
    public Registration getRegistrationById(int id) {
    return registrations.stream()
            .filter(reg -> reg.getId() == id)
            .findFirst()
            .orElse(null);
}

}
