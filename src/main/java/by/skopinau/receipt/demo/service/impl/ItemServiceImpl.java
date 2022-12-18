package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Item;
import by.skopinau.receipt.demo.dal.repository.ItemRepository;
import by.skopinau.receipt.demo.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends BaseService<Item> implements ItemService {
    protected ItemServiceImpl(ItemRepository repository) {
        super(repository);
    }
}
