package com.example.lalunaltd.Classes;

public class ItemInOrder {
        private String productId;
        private Integer quantity;
        private Product prod;

    public ItemInOrder() {
    }

    public ItemInOrder(String productId, Product prod) {
        this.productId = productId;
        this.prod=prod;
        this.quantity = 1;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    @Override
    public String toString() {
        return "ItemInOrder{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", prod=" + prod +
                '}';
    }
}
