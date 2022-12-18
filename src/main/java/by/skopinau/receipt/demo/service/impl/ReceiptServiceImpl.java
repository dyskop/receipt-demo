package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Receipt;
import by.skopinau.receipt.demo.dal.repository.ReceiptRepository;
import by.skopinau.receipt.demo.service.ReceiptService;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl extends BaseService<Receipt> implements ReceiptService {
    protected ReceiptServiceImpl(ReceiptRepository repository) {
        super(repository);
    }
}
