package bean;

public class ClubBean {

    private int clubID;
    private String clubName;
    private int adminID;
    private String adminName;


    public ClubBean() {
        this.clubID = 0;
        this.clubName = "";
        this.adminID = 0;

    }

    public ClubBean(int clubID, String clubName, int adminID,String adminName) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.adminID = adminID;         
        this.adminName = adminName;
    }

    
    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
}
