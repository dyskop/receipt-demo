package by.skopinau.receipt.demo.dal.repository;

import by.skopinau.receipt.demo.dal.entity.Receipt;

import java.util.List;
import java.util.Map;

public interface ReceiptCriteriaRepository {
    List<Receipt> findByCountedProductsAndCard(Map<Integer, Long> products, Integer cardId);
}
