package by.skopinau.receipt.demo.service.impl

import by.skopinau.receipt.demo.dal.entity.Order
import by.skopinau.receipt.demo.dal.repository.OrderRepository
import by.skopinau.receipt.demo.service.OrderService
import spock.lang.Specification

class OrderServiceImplTest extends Specification {
    private final OrderRepository repository = Mock()
    private final OrderService service = new OrderServiceImpl(repository)
    private static final Order ORDER = new Order()

    def "Should save entity #expected and return its optional"(Order expected) {
        when:
        Optional<Order> actual = service.save(expected)

        then:
        1 * repository.save(expected) >> expected
        Optional.of(expected) == actual

        where:
        expected | _
        ORDER    | _
    }

    def "Should return a list of all entities"() {
        when:
        List<Order> actual = service.findAll()

        then:
        1 * repository.findAll() >> expected
        expected == actual

        where:
        expected              | _
        List<Order>.of()      | _
        List<Order>.of(ORDER) | _
    }

    def "Should return an optional entity by id #id"(int id) {
        when:
        Optional<Order> actual = service.findById(id)

        then:
        1 * repository.findById(id) >> expected
        expected == actual

        where:
        id                     | expected
        new Random().nextInt() | Optional<Order>.empty()
        Integer.MAX_VALUE      | Optional<Order>.of(ORDER)
    }

    def "Should update a found entity by id #id and return its optional"(int id, Order entity) {
        given:
        repository.existsById(id) >> foundById

        when:
        Optional<Order> actual = service.update(id, entity)

        then:
        i * repository.save(entity) >> entity
        expected == actual

        where:
        entity | id                     | foundById | expected                | i
        ORDER  | Integer.MIN_VALUE      | true      | Optional.of(entity)     | 1
        ORDER  | new Random().nextInt() | false     | Optional<Order>.empty() | 0
    }

    def "Should delete an entity by id #id and return true if exists"(int id) {
        given:
        repository.existsById(id) >> expected

        when:
        boolean actual = service.deleteById(id)

        then:
        i * repository.deleteById(id)
        expected == actual

        where:
        id                     | expected | i
        new Random().nextInt() | true     | 1
        Long.MIN_VALUE         | false    | 0
        Long.MAX_VALUE         | true     | 1
    }
}
