package by.skopinau.receipt.demo.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card")
public class DiscountCard extends NamedEntity {
    @ManyToMany(mappedBy = "cards")
    private Set<Promotion> promotions = new HashSet<>();

    public DiscountCard() {
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }
}
