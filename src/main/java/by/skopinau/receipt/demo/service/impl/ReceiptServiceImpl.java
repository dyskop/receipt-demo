package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Receipt;
import by.skopinau.receipt.demo.dal.repository.ReceiptRepository;
import by.skopinau.receipt.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl extends BaseService<Receipt> implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        super(receiptRepository);
    }

    @Override
    @Transactional
    public Optional<List<Receipt>> findByProductsAndCard(List<Integer> productId, Integer cardId) {
        Map<Integer, Long> countedProducts = productId.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Receipt> list = receiptRepository.findByCountedProductsAndCard(countedProducts, cardId);
        return Optional.of(list);
    }
}
