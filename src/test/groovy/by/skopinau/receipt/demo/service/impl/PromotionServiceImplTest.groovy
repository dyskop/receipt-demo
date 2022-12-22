package by.skopinau.receipt.demo.service.impl

import by.skopinau.receipt.demo.dal.entity.Promotion
import by.skopinau.receipt.demo.dal.repository.PromotionRepository
import by.skopinau.receipt.demo.service.PromotionService
import spock.lang.Specification

class PromotionServiceImplTest extends Specification {
    private final PromotionRepository repository = Mock()
    private final PromotionService service = new PromotionServiceImpl(repository)
    private static final Promotion PROMOTION = new Promotion()

    def "Should save entity #expected and return its optional"(Promotion expected) {
        when:
        Optional<Promotion> actual = service.save(expected)

        then:
        1 * repository.save(expected) >> expected
        Optional.of(expected) == actual

        where:
        expected  | _
        PROMOTION | _
    }

    def "Should return a list of all entities"() {
        when:
        List<Promotion> actual = service.findAll()

        then:
        1 * repository.findAll() >> expected
        expected == actual

        where:
        expected                      | _
        List<Promotion>.of()          | _
        List<Promotion>.of(PROMOTION) | _
    }

    def "Should return an optional entity by id #id"(int id) {
        when:
        Optional<Promotion> actual = service.findById(id)

        then:
        1 * repository.findById(id) >> expected
        expected == actual

        where:
        id                     | expected
        new Random().nextInt() | Optional<Promotion>.empty()
        Integer.MAX_VALUE      | Optional<Promotion>.of(PROMOTION)
    }

    def "Should update a found entity by id #id and return its optional"(int id, Promotion entity) {
        given:
        repository.existsById(id) >> foundById

        when:
        Optional<Promotion> actual = service.update(id, entity)

        then:
        i * repository.save(entity) >> entity
        expected == actual

        where:
        entity    | id                     | foundById | expected                    | i
        PROMOTION | Integer.MIN_VALUE      | true      | Optional.of(entity)         | 1
        PROMOTION | new Random().nextInt() | false     | Optional<Promotion>.empty() | 0
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
