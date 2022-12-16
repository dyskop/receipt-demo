package by.skopinau.receipt.demo.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Promotion extends NamedEntity {
    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false, scale = 2)
    private float percent;

    @ManyToMany
    @JoinTable(name = "promotion_card")
    private Set<DiscountCard> cards = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "promotion_product")
    private Set<Product> products = new HashSet<>();

    public Promotion() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public Set<DiscountCard> getCards() {
        return cards;
    }

    public void setCards(Set<DiscountCard> cards) {
        this.cards = cards;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
