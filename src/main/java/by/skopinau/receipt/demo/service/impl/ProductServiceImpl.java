package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Product;
import by.skopinau.receipt.demo.dal.repository.ProductRepository;
import by.skopinau.receipt.demo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseService<Product> implements ProductService {
    protected ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }
}
