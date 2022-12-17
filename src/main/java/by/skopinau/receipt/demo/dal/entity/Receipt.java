package by.skopinau.receipt.demo.dal.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class Receipt extends BaseEntity {
    @ManyToOne(optional = false)
    private Cashbox cashbox;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Order order;

    public Receipt() {
    }

    public Cashbox getCashbox() {
        return cashbox;
    }

    public void setCashbox(Cashbox cashbox) {
        this.cashbox = cashbox;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
