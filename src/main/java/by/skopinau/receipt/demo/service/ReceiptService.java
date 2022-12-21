package by.skopinau.receipt.demo.service;

import by.skopinau.receipt.demo.dal.entity.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptService extends CrudService<Receipt> {
    Optional<List<Receipt>> findByProductsAndCard(List<Integer> productId, Integer cardId);
}
