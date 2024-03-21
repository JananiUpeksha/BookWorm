package TM;

public class UserTm {
    private String email;
    private String name;

    private String password;
    private String branches;

    public UserTm() {
    }

    public UserTm(String email, String name, String password, String branches) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.branches = branches;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranches() {
        return branches;
    }

    public void setBranches(String branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "UserTm{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", branches='" + branches + '\'' +
                '}';
    }

    public String getBranch() {
        return branches;
    }
}
