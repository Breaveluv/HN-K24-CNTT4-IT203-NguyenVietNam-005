package entity;

import java.util.Scanner;

public class Document {
    private String documentID;
    private String documentName;
    private double fileSize;
    private int downloads;

    // Constructor không tham số
    public Document() {}

    // Constructor đầy đủ tham số
    public Document(String documentID, String documentName, double fileSize, int downloads) {
        this.documentID = documentID;
        this.documentName = documentName;
        this.fileSize = fileSize;
        this.downloads = downloads;
    }

    // Getters và Setters
    public String getDocumentID() {
        return documentID;
    }
    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }
    public String getDocumentName() {
        return documentName;
    }
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
    public double getFileSize() {
        return fileSize;
    }
    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }
    public int getDownloads() {
        return downloads;
    }
    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public void inputData(Scanner sc) {
        System.out.print("Nhập tên tài liệu: ");
        this.documentName = sc.nextLine();

        while (true) {
            try {
                System.out.print("Nhập dung lượng file (MB): ");
                this.fileSize = Double.parseDouble(sc.nextLine());
                if (this.fileSize > 0) break;
                System.err.println("Dung lượng phải lớn hơn 0!");
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số thực!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập số lượt tải xuống: ");
                this.downloads = Integer.parseInt(sc.nextLine());
                if (this.downloads >= 0) break;
                System.err.println("Lượt tải không được âm!");
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số nguyên!");
            }
        }
    }

    public void displayData() {
        System.out.printf("| %-10s | %-25s | %-10.2f | %-10d |\n",
                documentID, documentName, fileSize, downloads);
    }
}