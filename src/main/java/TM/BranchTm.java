package TM;

public class BranchTm {
    private String branchName;

    private String location;

    private String branchAdmin;

    public BranchTm() {
    }

    public BranchTm(String branchName, String location, String branchAdmin) {
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
        return "BranchTm{" +
                "branchName='" + branchName + '\'' +
                ", location='" + location + '\'' +
                ", branchAdmin='" + branchAdmin + '\'' +
                '}';
    }
}
