package by.skopinau.receipt.demo.dal.repository.impl;

import by.skopinau.receipt.demo.dal.entity.Receipt;
import by.skopinau.receipt.demo.dal.repository.ReceiptCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReceiptCriteriaRepositoryImpl implements ReceiptCriteriaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Receipt> findByCountedProductsAndCard(Map<Integer, Long> products, Integer cardId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> query = builder.createQuery(Receipt.class);

        Root<Receipt> receiptRoot = query.from(Receipt.class);
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<Integer, Long> product : products.entrySet()) {
            Predicate productId = builder.equal(receiptRoot
                    .join("order")
                    .join("items")
                    .join("product")
                    .get("id"), product.getKey());
            Predicate quantity = builder.equal(receiptRoot
                    .join("order")
                    .join("items")
                    .get("quantity"), product.getValue());

            predicates.add(builder.and(productId, quantity));
        }

        Predicate card = builder.equal(receiptRoot
                .join("order")
                .join("card")
                .get("id"), cardId);
        Predicate itemsAmount = builder.equal(builder.size(receiptRoot
                .join("order")
                .get("items")), products.size());

        query.where(builder.and(predicates.toArray(Predicate[]::new)), card, itemsAmount);

        return em.createQuery(query).getResultList();
    }
}
