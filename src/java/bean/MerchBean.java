package bean;

public class MerchBean {
    private int merchID;
    private String merchName;
    private String category;
    private double price;
    private int stock;
    private String clubID;
    private byte[] image;

    // Default constructor
    public MerchBean() {
    }

    // Parameterized constructor
    public MerchBean(int merchID, String merchName, String category, double price, int stock, String clubID, byte[] image) {
        this.merchID = merchID;
        this.merchName = merchName;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.clubID = clubID;
        this.image = image;
    }

    // Combined setter
    public void setData(int merchID, String merchName, String category, double price, int stock, String clubID, byte[] image) {
        this.merchID = merchID;
        this.merchName = merchName;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.clubID = clubID;
        this.image = image;
    }

    // Individual getters
    public int getMerchID() {
        return merchID;
    }
    
    public String getMerchName() {
        return merchName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getClubID() {
        return clubID;
    }

    public byte[] getImage() {
        return image;
    }

    // Optional: Individual setters if needed
    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setMerchID(int merchID) {
        this.merchID = merchID;
    }
}
