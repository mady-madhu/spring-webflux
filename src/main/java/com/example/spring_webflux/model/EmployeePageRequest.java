package com.example.spring_webflux.model;


public class EmployeePageRequest {

    private int page;  // Page number (0-based)
    private int size;  // Page size (number of records per page)
    private String sortField;  // Field to sort by (e.g., "name", "role")
    private String sortDirection;  // "asc" or "desc"

    // Getters and setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
