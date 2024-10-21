package com.mycompany.ql.pham.nhan.trai.giam.v1.src.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Prison;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.Prisoner;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrisonerService {
    private static final String FILE_PATH = "src/main/java/com/mycompany/ql/pham/nhan/trai/giam/v1/src/data/prison.json";
    private List<Prison> prisons;
    private Gson gson;

    public PrisonerService() {
        gson = new Gson();
        prisons = loadPrisons(); // Tải danh sách trại giam từ file JSON
    }

    // Đọc danh sách trại giam từ file JSON
    private List<Prison> loadPrisons() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Prison>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Không thể đọc file JSON, tạo danh sách mới.");
            return new ArrayList<>(); // Trả về danh sách trại giam rỗng nếu không đọc được file
        }
    }

    // Lưu danh sách trại giam vào file JSON
    private void savePrisons() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(prisons, writer);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file JSON: " + e.getMessage());
        }
    }

    // Thêm phạm nhân vào một trại giam
    public void addPrisonerToPrison(int prisonId, Prisoner prisoner) {
        Optional<Prison> optionalPrison = findPrisonById(prisonId);
        if (optionalPrison.isPresent()) {
            optionalPrison.get().getPrisoners().add(prisoner);
            savePrisons();
            System.out.println("Đã thêm phạm nhân vào trại giam với ID: " + prisonId);
        } else {
            System.out.println("Không tìm thấy trại giam với ID: " + prisonId);
        }
    }

    // Lấy tất cả phạm nhân từ tất cả các trại
    public List<Prisoner> getAllPrisoners() {
        return prisons.stream()
                .flatMap(prison -> prison.getPrisoners().stream())
                .collect(Collectors.toList());
    }

    // Cập nhật phạm nhân theo ID (bất kể trại giam)
    public boolean updatePrisonerById(int prisonerId, String newName, String newDate, String newCrime, String newStatus) {
        for (Prison prison : prisons) {
            for (Prisoner prisoner : prison.getPrisoners()) {
                if (prisoner.getId() == prisonerId) {
                    prisoner.setName(newName);
                    prisoner.setDate(newDate);
                    prisoner.setCrime(newCrime);
                    prisoner.setStatus(newStatus);
                    savePrisons();
                    System.out.println("Đã cập nhật phạm nhân với ID: " + prisonerId);
                    return true;
                }
            }
        }
        System.out.println("Không tìm thấy phạm nhân với ID: " + prisonerId);
        return false;
    }

    // Xóa phạm nhân theo ID
    public boolean deletePrisonerById(int prisonerId) {
        for (Prison prison : prisons) {
            boolean removed = prison.getPrisoners()
                    .removeIf(prisoner -> prisoner.getId() == prisonerId);
            if (removed) {
                savePrisons();
                System.out.println("Đã xóa phạm nhân với ID: " + prisonerId);
                return true;
            }
        }
        System.out.println("Không tìm thấy phạm nhân với ID: " + prisonerId);
        return false;
    }
    
    public boolean findPrisonerById(int prisonerId) {
    for (Prison prison : prisons) {
        for (Prisoner prisoner : prison.getPrisoners()) {
            if (prisoner.getId() == prisonerId) {
                System.out.println("Tìm thấy phạm nhân với ID: " + prisonerId);
                return true;
            }
        }
    }
    System.out.println("Không tìm thấy phạm nhân với ID: " + prisonerId);
    return false;
}


    // Tìm kiếm phạm nhân theo tên hoặc tội danh trong tất cả các trại
    public List<Prisoner> searchPrisonersByNameOrCrime(String query) {
        return prisons.stream()
                .flatMap(prison -> prison.getPrisoners().stream())
                .filter(prisoner -> prisoner.getName().toLowerCase().contains(query.toLowerCase()) ||
                                    prisoner.getCrime().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Tìm kiếm trại giam theo ID
    public Optional<Prison> findPrisonById(int id) {
        return prisons.stream()
                .filter(prison -> prison.getId() == id)
                .findFirst();
    }

    // Lấy danh sách phạm nhân theo ID trại giam
    public List<Prisoner> getPrisonersByPrisonId(int prisonId) {
        return findPrisonById(prisonId)
                .map(Prison::getPrisoners)
                .orElse(new ArrayList<>());
    }

    // Lấy tổng số phạm nhân trong tất cả các trại giam
    public long getTotalPrisoners() {
        return prisons.stream()
                .flatMap(prison -> prison.getPrisoners().stream())
                .count();
    }

    // Lấy số lượng phạm nhân trong một trại giam cụ thể
    public long getPrisonerCountByPrisonId(int prisonId) {
        return findPrisonById(prisonId)
                .map(prison -> prison.getPrisoners().size())
                .orElse(0);
    }

    // Cập nhật thông tin trại giam theo ID
    public boolean updatePrison(int id, Prison updatedPrison) {
        Optional<Prison> optionalPrison = findPrisonById(id);
        if (optionalPrison.isPresent()) {
            Prison prison = optionalPrison.get();
            prison.setNamePrison(updatedPrison.getNamePrison());
            prison.setLocation(updatedPrison.getLocation());
            prison.setCapacity(updatedPrison.getCapacity());
            savePrisons();
            System.out.println("Đã cập nhật trại giam với ID: " + id);
            return true;
        } else {
            System.out.println("Không tìm thấy trại giam với ID: " + id);
            return false;
        }
    }

    // Lấy ID của trại giam dựa trên tên
  public Integer getPrisonIdByName(String name) {
    return prisons.stream()
            .filter(prison -> prison.getNamePrison().equalsIgnoreCase(name))
            .map(Prison::getId)
            .findFirst()
            .orElse(null);
  }
  
  public List<Prisoner> searchPrisonersByName(String name) {
    return prisons.stream()
            .flatMap(prison -> prison.getPrisoners().stream())
            .filter(prisoner -> prisoner.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
}
  
  
}
