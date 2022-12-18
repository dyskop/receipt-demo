package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Promotion;
import by.skopinau.receipt.demo.dal.repository.PromotionRepository;
import by.skopinau.receipt.demo.service.PromotionService;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl extends BaseService<Promotion> implements PromotionService {
    protected PromotionServiceImpl(PromotionRepository repository) {
        super(repository);
    }
}
