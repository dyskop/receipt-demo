package by.skopinau.receipt.demo.service.impl

import by.skopinau.receipt.demo.dal.entity.Cashbox
import by.skopinau.receipt.demo.dal.repository.CashboxRepository
import by.skopinau.receipt.demo.service.CashboxService
import spock.lang.Specification

class CashboxServiceImplTest extends Specification {
    private final CashboxRepository repository = Mock()
    private final CashboxService service = new CashboxServiceImpl(repository)
    private static final Cashbox CASHBOX = new Cashbox()

    def "Should save entity #expected and return its optional"(Cashbox expected) {
        when:
        Optional<Cashbox> actual = service.save(expected)

        then:
        1 * repository.save(expected) >> expected
        Optional.of(expected) == actual

        where:
        expected | _
        CASHBOX  | _
    }

    def "Should return a list of all entities"() {
        when:
        List<Cashbox> actual = service.findAll()

        then:
        1 * repository.findAll() >> expected
        expected == actual

        where:
        expected                  | _
        List<Cashbox>.of()        | _
        List<Cashbox>.of(CASHBOX) | _
    }

    def "Should return an optional entity by id #id"(int id) {
        when:
        Optional<Cashbox> actual = service.findById(id)

        then:
        1 * repository.findById(id) >> expected
        expected == actual

        where:
        id                     | expected
        new Random().nextInt() | Optional<Cashbox>.empty()
        Integer.MAX_VALUE      | Optional<Cashbox>.of(CASHBOX)
    }

    def "Should update a found entity by id #id and return its optional"(int id, Cashbox entity) {
        given:
        repository.existsById(id) >> foundById

        when:
        Optional<Cashbox> actual = service.update(id, entity)

        then:
        i * repository.save(entity) >> entity
        expected == actual

        where:
        entity  | id                     | foundById | expected                  | i
        CASHBOX | Integer.MIN_VALUE      | true      | Optional.of(entity)       | 1
        CASHBOX | new Random().nextInt() | false     | Optional<Cashbox>.empty() | 0
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
