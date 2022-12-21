package by.skopinau.receipt.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
    @JsonProperty
    private Set<ItemRequest> items;

    @JsonProperty
    private int card;

    @JsonProperty
    private int cashbox;

    public OrderRequest(Set<ItemRequest> items, int card, int cashbox) {
        this.items = items;
        this.card = card;
        this.cashbox = cashbox;
    }

    public Set<ItemRequest> getItems() {
        return items;
    }

    public int getCard() {
        return card;
    }

    public int getCashbox() {
        return cashbox;
    }
}
