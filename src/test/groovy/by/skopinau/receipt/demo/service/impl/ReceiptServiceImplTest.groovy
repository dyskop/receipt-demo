package by.skopinau.receipt.demo.service.impl

import by.skopinau.receipt.demo.dal.entity.Receipt
import by.skopinau.receipt.demo.dal.repository.ReceiptRepository
import by.skopinau.receipt.demo.service.ReceiptService
import spock.lang.Specification

class ReceiptServiceImplTest extends Specification {
    private final ReceiptRepository repository = Mock()
    private final ReceiptService service = new ReceiptServiceImpl(repository)
    private static final Receipt RECEIPT = new Receipt()

    def "Should save entity #expected and return its optional"(Receipt expected) {
        when:
        Optional<Receipt> actual = service.save(expected)

        then:
        1 * repository.save(expected) >> expected
        Optional.of(expected) == actual

        where:
        expected | _
        RECEIPT  | _
    }

    def "Should return a list of all entities"() {
        when:
        List<Receipt> actual = service.findAll()

        then:
        1 * repository.findAll() >> expected
        expected == actual

        where:
        expected                  | _
        List<Receipt>.of()        | _
        List<Receipt>.of(RECEIPT) | _
    }

    def "Should return an optional entity by id #id"(int id) {
        when:
        Optional<Receipt> actual = service.findById(id)

        then:
        1 * repository.findById(id) >> expected
        expected == actual

        where:
        id                     | expected
        new Random().nextInt() | Optional<Receipt>.empty()
        Integer.MAX_VALUE      | Optional<Receipt>.of(RECEIPT)
    }

    def "Should update a found entity by id #id and return its optional"(int id, Receipt entity) {
        given:
        repository.existsById(id) >> foundById

        when:
        Optional<Receipt> actual = service.update(id, entity)

        then:
        i * repository.save(entity) >> entity
        expected == actual

        where:
        entity  | id                     | foundById | expected                  | i
        RECEIPT | Integer.MIN_VALUE      | true      | Optional.of(entity)       | 1
        RECEIPT | new Random().nextInt() | false     | Optional<Receipt>.empty() | 0
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
