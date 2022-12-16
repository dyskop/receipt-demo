package by.skopinau.receipt.demo.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cashbox extends BaseEntity {
    @OneToMany(mappedBy = "cashbox", orphanRemoval = true)
    private Set<Receipt> receipts = new HashSet<>();

    public Cashbox() {
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }
}

