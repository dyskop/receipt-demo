package by.skopinau.receipt.demo.service.impl;

import by.skopinau.receipt.demo.dal.entity.Cashbox;
import by.skopinau.receipt.demo.dal.entity.DiscountCard;
import by.skopinau.receipt.demo.dal.entity.Item;
import by.skopinau.receipt.demo.dal.entity.Order;
import by.skopinau.receipt.demo.dal.entity.Product;
import by.skopinau.receipt.demo.dal.entity.Promotion;
import by.skopinau.receipt.demo.dal.entity.Receipt;
import by.skopinau.receipt.demo.dal.repository.CashboxRepository;
import by.skopinau.receipt.demo.dal.repository.DiscountCardRepository;
import by.skopinau.receipt.demo.dal.repository.ItemRepository;
import by.skopinau.receipt.demo.dal.repository.OrderRepository;
import by.skopinau.receipt.demo.dal.repository.ProductRepository;
import by.skopinau.receipt.demo.dal.repository.PromotionRepository;
import by.skopinau.receipt.demo.dal.repository.ReceiptRepository;
import by.skopinau.receipt.demo.service.OrderService;
import by.skopinau.receipt.demo.web.dto.ItemRequest;
import by.skopinau.receipt.demo.web.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames="application")
public class OrderServiceImpl extends BaseService<Order> implements OrderService {
    private static final String RECEIPT_INFO = "DREAM MARKET";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DiscountCardRepository cardRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private CashboxRepository cashboxRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    protected OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public Optional<Order> save(OrderRequest dto) {
        Order order = new Order();
        Optional<DiscountCard> card = cardRepository.findById(dto.getCard());
        card.ifPresent(order::setCard);
        Order savedOrder = orderRepository.save(order);

        Cashbox cashbox = cashboxRepository.findById(dto.getCashbox()).orElseThrow();
        Receipt receipt = new Receipt(cashbox, RECEIPT_INFO, LocalDateTime.now(), order);
        receiptRepository.save(receipt);

        Set<Item> items = new HashSet<>();
        Set<ItemRequest> itemRequests = dto.getItems();

        itemRequests.forEach(i -> {
            Item item = new Item();
            item.setProduct(productRepository.findById(i.getProduct()).orElseThrow());
            item.setQuantity(i.getQuantity());
            item.setOrder(savedOrder);
            items.add(item);
            itemRepository.save(item);
        });

        savedOrder.setItems(items);
        savedOrder.setTotal(calculateTotal(items, card));

        return Optional.of(savedOrder);
    }

    // todo: decompose
    private BigDecimal calculateTotal(Set<Item> items, Optional<DiscountCard> card) {
        BigDecimal total = new BigDecimal(0);

        Map<Promotion, Set<Product>> promos = new HashMap<>();

        if (card.isPresent()) {
            promos = card.get().getPromotions().stream()
                    .filter(Promotion::isActive)
                    .collect(Collectors.toMap(promotion -> promotion, Promotion::getProducts));
        }

        int counter = 0;
        BigDecimal temp = new BigDecimal(0);
        float percent = promotionRepository.findById(1).orElseThrow().getPercent();

        for (Item item : items) {
            Product product = item.getProduct();
            boolean isPromoted = false;

            for (Map.Entry<Promotion, Set<Product>> promo : promos.entrySet()) {
                if (promo.getKey().getId() == 1 && promo.getValue().contains(product)) {
                    counter += item.getQuantity();
                    temp = temp.add(
                            product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                    isPromoted = true;
                    break;
                }
            }

            for (Map.Entry<Promotion, Set<Product>> promo : promos.entrySet()) {
                if ((promo.getKey().getId() == 2 || promo.getKey().getId() == 3)
                        && promo.getValue().contains(product)) {
                    total = total.add(
                            product.getPrice()
                                    .multiply(BigDecimal.valueOf(item.getQuantity()))
                                    .multiply(BigDecimal.valueOf(1 - promo.getKey().getPercent() / 100)));
                    isPromoted = true;
                    break;
                }
            }

            if (!isPromoted) {
                total = total.add(
                        product.getPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity())));
            }
        }

        if (counter >= 5) {
            total = total.add(temp.multiply(BigDecimal.valueOf(1 - percent / 100)));
        } else {
            total = total.add(temp);
        }

        return total;
    }
}
