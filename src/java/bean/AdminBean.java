package bean;

public class AdminBean {
    private int adminID;
    private String name;
    private String username;
    private String password;

    // Default constructor
    public AdminBean() {
    }

    // Parameterized constructor
    public AdminBean(int adminID, String name, String username, String password) {
        this.adminID = adminID;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
