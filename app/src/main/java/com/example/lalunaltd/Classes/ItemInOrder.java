package com.example.lalunaltd.Classes;

public class ItemInOrder {
        private String productId;
        private Integer quantity;

    public ItemInOrder(String productId) {
        this.productId = productId;
        this.quantity = 0;
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

}
