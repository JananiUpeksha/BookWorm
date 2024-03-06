package org.example.dto;

public class BranchesDto {
    private String branchName;

    private String location;

    private String branchAdmin;

    public BranchesDto() {
    }

    public BranchesDto(String branchName, String location, String branchAdmin) {
        this.branchName = branchName;
        this.location = location;
        this.branchAdmin = branchAdmin;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranchAdmin() {
        return branchAdmin;
    }

    public void setBranchAdmin(String branchAdmin) {
        this.branchAdmin = branchAdmin;
    }

    @Override
    public String toString() {
        return "BranchesDto{" +
                "branchName='" + branchName + '\'' +
                ", location='" + location + '\'' +
                ", branchAdmin='" + branchAdmin + '\'' +
                '}';
    }
}
