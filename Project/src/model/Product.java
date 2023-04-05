package model;

import java.io.Serializable;
import java.util.List;

public class Product implements IGeneric, Serializable {
    private int productId;
    private String productName;
    private String descriptions;
    private float price;
    private int quantity;
    private String category;
    private boolean productStatus;
    private LoginAccount loginAccount;
    private int count;

    public Product() {
    }

    public Product(int productId, String productName, String descriptions, float price, int quantity, String category) {
        this.productId = productId;
        this.productName = productName;
        this.descriptions = descriptions;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.productStatus = this.quantity>0?true:false;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public LoginAccount getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(LoginAccount loginAccount) {
        this.loginAccount = loginAccount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void displayData() {
        System.out.println("ID: " + getProductId() + "\n"
                + "Product's Name: " + getProductName() + "\n"
                + "Descriptions: " + getDescriptions() + "\n"
                + "Price: " + getPrice() + "\n"
                + "Category: " + getCategory()+ "\n"
                + "Quantity: " + getQuantity()+ "\n"
                + "Status: " + (isProductStatus()?"Stock":"Unstuck") + "\n"
                + "--------------------"
        );
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + productId + '\'' +
                "productName='" + productName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public void inputData() {

    }
}
