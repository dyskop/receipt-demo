package by.skopinau.receipt.demo.service.impl

import by.skopinau.receipt.demo.dal.entity.Item
import by.skopinau.receipt.demo.dal.repository.ItemRepository
import by.skopinau.receipt.demo.service.ItemService
import spock.lang.Specification

class ItemServiceImplTest extends Specification {
    private final ItemRepository repository = Mock()
    private final ItemService service = new ItemServiceImpl(repository)
    private static final Item ITEM = new Item()

    def "Should save entity #expected and return its optional"(Item expected) {
        when:
        Optional<Item> actual = service.save(expected)

        then:
        1 * repository.save(expected) >> expected
        Optional.of(expected) == actual

        where:
        expected | _
        ITEM     | _
    }

    def "Should return a list of all entities"() {
        when:
        List<Item> actual = service.findAll()

        then:
        1 * repository.findAll() >> expected
        expected == actual

        where:
        expected            | _
        List<Item>.of()     | _
        List<Item>.of(ITEM) | _
    }

    def "Should return an optional entity by id #id"(int id) {
        when:
        Optional<Item> actual = service.findById(id)

        then:
        1 * repository.findById(id) >> expected
        expected == actual

        where:
        id                     | expected
        new Random().nextInt() | Optional<Item>.empty()
        Integer.MAX_VALUE      | Optional<Item>.of(ITEM)
    }

    def "Should update a found entity by id #id and return its optional"(int id, Item entity) {
        given:
        repository.existsById(id) >> foundById

        when:
        Optional<Item> actual = service.update(id, entity)

        then:
        i * repository.save(entity) >> entity
        expected == actual

        where:
        entity | id                     | foundById | expected               | i
        ITEM   | Integer.MIN_VALUE      | true      | Optional.of(entity)    | 1
        ITEM   | new Random().nextInt() | false     | Optional<Item>.empty() | 0
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
