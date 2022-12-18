package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.DiscountCard;
import by.skopinau.receipt.demo.dal.repository.DiscountCardRepository;
import by.skopinau.receipt.demo.service.DiscountCardService;
import org.springframework.stereotype.Service;

@Service
public class DiscountCardServiceImpl extends BaseService<DiscountCard>
        implements DiscountCardService {
    protected DiscountCardServiceImpl(DiscountCardRepository repository) {
        super(repository);
    }
}
