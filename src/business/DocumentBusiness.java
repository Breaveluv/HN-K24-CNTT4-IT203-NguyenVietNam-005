package business;

import entity.Document;
import java.util.*;
import java.util.stream.Collectors;

public class DocumentBusiness {
    private List<Document> documents = new ArrayList<>();

    // Singleton Design Pattern
    private static DocumentBusiness instance;
    public DocumentBusiness() {}
    public static DocumentBusiness getInstance() {
        if (instance == null) instance = new DocumentBusiness();
        return instance;
    }

    public List<Document> getDocuments() { return documents; }

    // Thêm tài liệu với validate trùng ID
    public void addDocument(Scanner sc) {
        System.out.print("Nhập mã tài liệu mới: ");
        String id = sc.nextLine();

        // Sử dụng Stream/Optional kiểm tra trùng ID
        boolean exists = documents.stream().anyMatch(d -> d.getDocumentID().equals(id));
        if (exists) {
            System.err.println("Lỗi: Mã ID '" + id + "' đã tồn tại!");
            return;
        }

        Document newDoc = new Document();
        newDoc.setDocumentID(id);
        newDoc.inputData(sc);
        documents.add(newDoc);
        System.out.println("Thêm mới thành công!");
    }

    public void updateDocument(Scanner sc) {
        System.out.print("Nhập mã tài liệu cần sửa: ");
        String id = sc.nextLine();

        Optional<Document> docOpt = documents.stream()
                .filter(d -> d.getDocumentID().equals(id))
                .findFirst();

        if (docOpt.isPresent()) {
            Document doc = docOpt.get();
            System.out.println("1. Sửa tên tài liệu");
            System.out.println("2. Sửa dung lượng");
            System.out.println("3. Sửa lượt tải");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 :
                    System.out.print("Tên mới: ");
                    doc.setDocumentName(sc.nextLine());
                    break;
                case 2 :
                    System.out.print("Dung lượng mới: ");
                    doc.setFileSize(Double.parseDouble(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Lượt tải mới: ");
                    doc.setDownloads(Integer.parseInt(sc.nextLine()));
                    break;
            }
        } else {
            System.err.println("Tài liệu không tồn tại trong hệ thống!");
        }
    }

    public void deleteDocument(Scanner sc) {
        System.out.print("Nhập mã cần xóa: ");
        String id = sc.nextLine();
        int oldSize = documents.size();
        documents.removeIf(d -> d.getDocumentID().equals(id));

        if (documents.size() == oldSize) {
            System.err.println("Lỗi: Mã không tồn tại, không có gì để xóa!");
        } else {
            System.out.println("Xóa thành công!");
        }
    }

    public void findDocument(Scanner sc) {
        System.out.print("Nhập tên cần tìm: ");
        String keyword = sc.nextLine().toLowerCase();
        List<Document> results = documents.stream()
                .filter(d -> d.getDocumentName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.err.println("Không tìm thấy tài liệu nào!");
        } else {
            System.out.println("Tìm thấy " + results.size() + " tài liệu:");
            results.forEach(Document::displayData);
        }
    }

    public void sortAndFilter(boolean isSort) {
        if (isSort) {
            documents.sort((d1, d2) -> Integer.compare(d2.getDownloads(), d1.getDownloads()));
            System.out.println("Đã sắp xếp giảm dần theo lượt tải!");
        } else {
            System.out.println("Danh sách tài liệu phổ biến (>= 1000 lượt tải):");
            documents.stream()
                    .filter(d -> d.getDownloads() >= 1000)
                    .forEach(Document::displayData);
        }
    }
}