package by.skopinau.receipt.demo.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product extends NamedEntity {
    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "products")
    private Set<Promotion> promotions = new HashSet<>();

    public Product() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }
}
