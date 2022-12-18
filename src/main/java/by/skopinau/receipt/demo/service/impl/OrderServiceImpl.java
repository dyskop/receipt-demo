package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Order;
import by.skopinau.receipt.demo.dal.repository.OrderRepository;
import by.skopinau.receipt.demo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseService<Order> implements OrderService {
    protected OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }
}
