package by.skopinau.receipt.demo.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card")
public class DiscountCard extends NamedEntity {
    @JsonIgnore
    @ManyToMany(mappedBy = "cards", fetch = FetchType.EAGER)
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
