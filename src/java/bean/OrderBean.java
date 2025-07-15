package bean;

import java.util.Date;

public class OrderBean {

    private int orderID;
    private int merchID;
    private int customerID;
    private double totalPrice;
    private Date orderDate;
    private MerchBean merch;

    // Default constructor
    public OrderBean() {
    }

    // Parameterized constructor
    public OrderBean(int orderID, int merchID, int customerID, double totalPrice, Date orderDate) {
        this.orderID = orderID;
        this.merchID = merchID;
        this.customerID = customerID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getMerchID() {
        return merchID;
    }

    public void setMerchID(int merchID) {
        this.merchID = merchID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public MerchBean getMerch() {
        return merch;
    }

    public void setMerch(MerchBean merch) {
        this.merch = merch;
    }
}