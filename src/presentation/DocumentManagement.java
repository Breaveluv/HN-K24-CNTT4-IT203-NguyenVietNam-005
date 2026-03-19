package presentation;

import business.DocumentBusiness;
import entity.Document;
import java.util.Scanner;

public class DocumentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DocumentBusiness business = DocumentBusiness.getInstance();
        int choice;

        do {
            System.out.println("\n*****************QUẢN LÝ TÀI LIỆU SỐ*****************");
            System.out.println("1. Hiển thị danh sách toàn bộ tài liệu");
            System.out.println("2. Thêm mới tài liệu");
            System.out.println("3. Cập nhật thông tin theo mã tài liệu");
            System.out.println("4. Xóa tài liệu theo mã tài liệu");
            System.out.println("5. Tìm kiếm tài liệu theo tên");
            System.out.println("6. Lọc danh sách tài liệu phổ biến (>= 1000)");
            System.out.println("7. Sắp xếp danh sách giảm dần theo lượt tải");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = 0;
            }

            switch (choice) {
                case 1 :
                    System.out.printf("| %-10s | %-25s | %-10s | %-10s |\n", "ID", "Tên", "Size(MB)", "Downloads");
                    business.getDocuments().forEach(Document::displayData);
                break;
                case 2 :
                    String subChoice;
                    do {
                        business.addDocument(sc);
                        System.out.print("Bạn có muốn tiếp tục thêm không? (Y/N): ");
                        subChoice = sc.nextLine();
                    } while (subChoice.equalsIgnoreCase("Y"));
                break;
                case 3 :
                    business.updateDocument(sc);
                    break;
                case 4 : business.deleteDocument(sc);
                break;
                case 5 : business.findDocument(sc);
                break;
                case 6: business.sortAndFilter(false);
                break;
                case 7: business.sortAndFilter(true);
                break;
                case 8:
                    System.out.println("Chương trình kết thúc!");
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-8!");
                    break;
            }
        } while (choice != 8);
    }
}