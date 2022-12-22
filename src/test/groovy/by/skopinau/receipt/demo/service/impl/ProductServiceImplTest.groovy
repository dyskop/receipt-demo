package by.skopinau.receipt.demo.service.impl

import by.skopinau.receipt.demo.dal.entity.Product
import by.skopinau.receipt.demo.dal.repository.ProductRepository
import by.skopinau.receipt.demo.service.ProductService
import spock.lang.Specification

class ProductServiceImplTest extends Specification {
    private final ProductRepository repository = Mock()
    private final ProductService service = new ProductServiceImpl(repository)
    private static final Product PRODUCT = new Product()

    def "Should save entity #expected and return its optional"(Product expected) {
        when:
        Optional<Product> actual = service.save(expected)

        then:
        1 * repository.save(expected) >> expected
        Optional.of(expected) == actual

        where:
        expected | _
        PRODUCT  | _
    }

    def "Should return a list of all entities"() {
        when:
        List<Product> actual = service.findAll()

        then:
        1 * repository.findAll() >> expected
        expected == actual

        where:
        expected                  | _
        List<Product>.of()        | _
        List<Product>.of(PRODUCT) | _
    }

    def "Should return an optional entity by id #id"(int id) {
        when:
        Optional<Product> actual = service.findById(id)

        then:
        1 * repository.findById(id) >> expected
        expected == actual

        where:
        id                     | expected
        new Random().nextInt() | Optional<Product>.empty()
        Integer.MAX_VALUE      | Optional<Product>.of(PRODUCT)
    }

    def "Should update a found entity by id #id and return its optional"(int id, Product entity) {
        given:
        repository.existsById(id) >> foundById

        when:
        Optional<Product> actual = service.update(id, entity)

        then:
        i * repository.save(entity) >> entity
        expected == actual

        where:
        entity  | id                     | foundById | expected                  | i
        PRODUCT | Integer.MIN_VALUE      | true      | Optional.of(entity)       | 1
        PRODUCT | new Random().nextInt() | false     | Optional<Product>.empty() | 0
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
