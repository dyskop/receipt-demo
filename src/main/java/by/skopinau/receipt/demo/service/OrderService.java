package by.skopinau.receipt.demo.service;

import by.skopinau.receipt.demo.dal.entity.Order;
import by.skopinau.receipt.demo.web.dto.OrderRequest;

import java.util.Optional;

public interface OrderService extends CrudService<Order> {
    Optional<Order> save(OrderRequest dto);
}
