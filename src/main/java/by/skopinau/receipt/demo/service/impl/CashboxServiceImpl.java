package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Cashbox;
import by.skopinau.receipt.demo.dal.repository.CashboxRepository;
import by.skopinau.receipt.demo.service.CashboxService;
import org.springframework.stereotype.Service;

@Service
public class CashboxServiceImpl extends BaseService<Cashbox> implements CashboxService {
    protected CashboxServiceImpl(CashboxRepository repository) {
        super(repository);
    }
}
