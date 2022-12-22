package by.skopinau.receipt.demo.service.impl

import by.skopinau.receipt.demo.dal.entity.DiscountCard
import by.skopinau.receipt.demo.dal.repository.DiscountCardRepository
import by.skopinau.receipt.demo.service.DiscountCardService
import spock.lang.Specification

class DiscountCardServiceImplTest extends Specification {
    private final DiscountCardRepository repository = Mock()
    private final DiscountCardService service = new DiscountCardServiceImpl(repository)
    private static final DiscountCard CARD = new DiscountCard()

    def "Should save entity #expected and return its optional"(DiscountCard expected) {
        when:
        Optional<DiscountCard> actual = service.save(expected)

        then:
        1 * repository.save(expected) >> expected
        Optional.of(expected) == actual

        where:
        expected | _
        CARD     | _
    }

    def "Should return a list of all entities"() {
        when:
        List<DiscountCard> actual = service.findAll()

        then:
        1 * repository.findAll() >> expected
        expected == actual

        where:
        expected                    | _
        List<DiscountCard>.of()     | _
        List<DiscountCard>.of(CARD) | _
    }

    def "Should return an optional entity by id #id"(int id) {
        when:
        Optional<DiscountCard> actual = service.findById(id)

        then:
        1 * repository.findById(id) >> expected
        expected == actual

        where:
        id                     | expected
        new Random().nextInt() | Optional<DiscountCard>.empty()
        Integer.MAX_VALUE      | Optional<DiscountCard>.of(CARD)
    }

    def "Should update a found entity by id #id and return its optional"(int id, DiscountCard entity) {
        given:
        repository.existsById(id) >> foundById

        when:
        Optional<DiscountCard> actual = service.update(id, entity)

        then:
        i * repository.save(entity) >> entity
        expected == actual

        where:
        entity | id                     | foundById | expected                       | i
        CARD   | Integer.MIN_VALUE      | true      | Optional.of(entity)            | 1
        CARD   | new Random().nextInt() | false     | Optional<DiscountCard>.empty() | 0
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
