package by.skopinau.receipt.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemRequest {
    @JsonProperty
    private int product;

    @JsonProperty
    private int quantity;

    public ItemRequest(int product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
